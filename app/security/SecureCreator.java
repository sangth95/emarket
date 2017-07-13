package security;

import models.Role;
import models.User;
import org.springframework.util.CollectionUtils;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import services.UserService;

import javax.inject.Inject;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class SecureCreator implements play.http.ActionCreator {
    private UserService userService;

    @Inject
    public SecureCreator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Action createAction(Http.Request request, Method actionMethod) {
        return new Action.Simple() {
            @Override
            public CompletionStage<Result> call(Http.Context ctx) {
                RestrictByRole r = actionMethod.getAnnotation(RestrictByRole.class);
                if (null != r) {
                    List<String> roles = Arrays.asList(r.value());
                    User user = userService.getUser(getUsername(ctx));
                    List<String> userRoles = getRoles(ctx);

                    if (CollectionUtils.containsAny(roles, userRoles)) {
                        ctx.args.put("user", user);
                        return delegate.call(ctx);
                    }

                    return CompletableFuture.completedFuture(unauthorized("Unauthorized"));

                }
                return delegate.call(ctx);
            }
        };
    }

    private String getUsername(Http.Context ctx) {
        String token = ctx.session().get("token");
        if (null == token) {
            return null;
        }
        return JWTHandler.getUsernameBaseOnToken(token);
    }

    private List<String> getRoles(Http.Context ctx) {
        String token = ctx.session().get("token");
        if (null == token) {
            return null;
        }
        return JWTHandler.getRolesBaseOnToken(token);
    }
}
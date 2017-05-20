package security;

import models.Role;
import org.springframework.util.CollectionUtils;
import play.db.jpa.Transactional;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import services.UserService;
import models.User;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class SecuredAction extends Action<RestrictByRole> {
    private UserService userService;
    @Inject
    public SecuredAction(UserService userService) {
        this.userService = userService;
    }

    public CompletionStage<Result> call(Http.Context ctx)  {
        List<String> roles = Arrays.asList(configuration.value());
        User user = userService.getUser(getUsername(ctx));
        List<String> userRoles = Optional.ofNullable(user)
                                .map(User::getRoles)
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(Role::getRoleName)
                                .collect(Collectors.toList());

        if (CollectionUtils.containsAny(roles, userRoles)) {
            ctx.args.put("user", user);
            return delegate.call(ctx);
        }

        return CompletableFuture.completedFuture(unauthorized("unauthorized"));
    }

    private String getUsername(Http.Context ctx) {
        return ctx.session().get("username");
    }
}
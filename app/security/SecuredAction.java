package security;

import models.Role;
import play.api.mvc.Session;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import services.UserService;
import models.User;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class SecuredAction extends Action<RestrictByRole> {
    private UserService userService;
    @Inject
    public SecuredAction(UserService userService) {
        this.userService = userService;
    }

    public CompletionStage<Result> call(Http.Context ctx)  {
        List<String> roles = Arrays.asList(configuration.value());
        User user = userService.getUser(getUsername(ctx));
        String userRole = Optional.ofNullable(user)
                                .map(User::getRole)
                                .map(Role::getRoleName)
                                .orElse("");

        if (roles.contains(userRole)) {
            ctx.args.put("user", user);
            return delegate.call(ctx);
        }

        return CompletableFuture.completedFuture(unauthorized("unauthorized"));
    }

    private String getUsername(Http.Context ctx) {
        return ctx.session().get("username");
    }
}
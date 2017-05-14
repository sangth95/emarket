package controllers;

import models.User;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import security.RestrictByRole;
import services.UserService;

import javax.inject.Inject;

/**
 * Created by An on 5/14/2017.
 */
public class AdminController extends Controller {
    private UserService userService;

    @Inject
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @RestrictByRole({"admin", "manager"})
    public Result adminPage() {
        User user = (User) ctx().args.get("user");
        return ok(user.getRole().getRoleName());
    }
}

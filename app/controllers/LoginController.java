package controllers;

import models.LoginInformation;
import models.Role;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import security.JWTHandler;
import services.UserService;
import views.html.*;

import javax.inject.Inject;
import java.util.stream.Collectors;

/**
 * Created by An on 5/14/2017.
 */
public class LoginController extends Controller{
    private UserService userService;
    private FormFactory formFactory;

    @Inject
    public LoginController(UserService userService, FormFactory formFactory) {
        this.userService = userService;
        this.formFactory = formFactory;
    }

    public Result loginPage() {
        removeUserFromSession();
        Form<LoginInformation> loginInformationForm = formFactory.form(LoginInformation.class);
        return ok(login.render("Login", loginInformationForm));
    }

    @Transactional
    public Result login() {
        removeUserFromSession();
        Form<LoginInformation> loginInformationForm = formFactory.form(LoginInformation.class).bindFromRequest();
        if (loginInformationForm.hasErrors()) {
            return loginPage();
        }
        LoginInformation loginInformation = loginInformationForm.get();
        User user = userService.getUser(loginInformation.username, loginInformation.password);
        if (null != user) {
            session().put("token", JWTHandler.createToken(user.getUsername(), user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList())));
            return Results.redirect(routes.AdminProductController.admin_ViewAllProduct());
        } else {
            flash("loginFail", "Email or password is incorrect!");
            return loginPage();
        }
    }

    public Result logout() {
        removeUserFromSession();
        return Results.redirect(routes.HomeController.index());
    }

    private void removeUserFromSession() {
        session().remove("token");
    }
}

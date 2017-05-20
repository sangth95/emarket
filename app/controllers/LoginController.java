package controllers;

import models.LoginInformation;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.UserService;
import views.html.*;

import javax.inject.Inject;

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
        Form<LoginInformation> loginInformationForm = formFactory.form(LoginInformation.class);
        return ok(login.render("Login", loginInformationForm));
    }

    @Transactional
    public Result login() {
        session().remove("username");
        Form<LoginInformation> loginInformationForm = formFactory.form(LoginInformation.class).bindFromRequest();
        if (loginInformationForm.hasErrors()) {
            return badRequest("bad request");
        }
        LoginInformation loginInformation = loginInformationForm.get();
        if (null != userService.getUser(loginInformation.username, loginInformation.password)) {
            session().put("username", loginInformation.username);
            return Results.redirect(routes.HomeController.index());
        } else {
            return unauthorized("unauthorized");
        }
    }


}

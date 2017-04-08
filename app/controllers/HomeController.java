package controllers;

import models.Product;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.*;

import services.EmarketDataService;
import services.ServiceFactory;
import views.html.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {

    private EmarketDataService emarketDataService;

    private final FormFactory formFactory;

    @Inject
    public HomeController(ServiceFactory serviceFactory, FormFactory formFactory) {
        emarketDataService = serviceFactory.getEmarketDataService();
        this.formFactory = formFactory;
    }

    @Transactional
    public Result index() {
        List<Product> productList = emarketDataService.getProducts();
        Product[] products = productList.toArray(new Product[productList.size()]);
        return ok(index.render("Bootshop", products));
    }

    /**
     * get cart
     *
     * @return
     */
    public Result guest_ViewCart() {
        return ok(product_summary.render("cart"));
    }

    //getSpecialOffer
    public Result getSpecialOffer() {
        return ok(special_offer.render("special offer"));
    }

    //getDelivery
    public Result guest_ViewDelivery() {
        return ok(normal.render("view delivery"));
    }

    //getContact
    public Result getContact() {
        return ok(contact.render("contact"));
    }
}

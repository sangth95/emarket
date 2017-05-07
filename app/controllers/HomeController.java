package controllers;

import models.Product;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.*;

import services.ProductService;
import views.html.*;

import javax.inject.Inject;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {

    private ProductService productService;
    private FormFactory formFactory;

    @Inject
    public HomeController(ProductService productService, FormFactory formFactory) {
        this.productService = productService;
        this.formFactory = formFactory;
    }

    @Transactional
    public Result index() {
        List<Product> productList = productService.getProducts();
        Product[] products = productList.toArray(new Product[productList.size()]);
        return ok(index.render("Bootshop", products));
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

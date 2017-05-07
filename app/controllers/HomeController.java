package controllers;

import models.ShoppingCartDetail;
import models.Product;
import models.ShoppingCart;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.*;

import services.EmarketDataService;
import services.ServiceFactory;
import views.html.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

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


    @Transactional
    public Result guest_addToCart(String cart_id, String item_id, String title) {
        CompletionStage<ShoppingCart> promiseOfShoppingCart = CompletableFuture.supplyAsync(() -> CartController.addToCart(Integer.parseInt(item_id)));
        return index();
    }
}

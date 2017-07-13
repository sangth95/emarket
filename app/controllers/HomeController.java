package controllers;

import models.Product;
import models.ShoppingCart;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.*;

import services.CartService;
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

    private CartService cartService;

    private final FormFactory formFactory;

    @Inject
    public HomeController(ProductService productService, CartService cartService, FormFactory formFactory) {
        this.productService = productService;
        this.cartService = cartService;
        this.formFactory = formFactory;
    }

    @Transactional
    public Result index() {
        initData();
        List<Product> productList = productService.getProducts();
        Product[] products = productList.toArray(new Product[productList.size()]);
        return ok(index.render("Bootshop", products));
    }

    //getSpecialOffer
    public Result getSpecialOffer() {
        return ok(special_offer.render("Special offer"));
    }

    //getDelivery
    public Result guest_ViewDelivery() {
        return ok(normal.render("View delivery"));
    }

    //getContact
    public Result getContact() {
        return ok(contact.render("Contact"));
    }



    //init data
    private void initData() {
        String currentCartIdStr = session("currentCartId");
        System.out.println("id: " + currentCartIdStr);
        if(null == currentCartIdStr) {
            ShoppingCart shoppingCart = new ShoppingCart();
            cartService.saveShoppingCart(shoppingCart);
            session("currentCartId", String.valueOf(shoppingCart.getId()));
        }

    }
}

package controllers;

import models.Product;
import models.ShoppingCart;
import models.ShoppingCartDetail;
import play.Routes;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.CartService;
import services.ProductService;
import views.html.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


/**
 * Created by HongSang on 4/24/2017.
 */
@Singleton
public class CartController extends Controller{
    private CartService cartService;
    private ProductService productService;
    private FormFactory formFactory;

    @Inject
    public CartController(CartService cartService, ProductService productService, FormFactory formFactory) {
        this.cartService = cartService;
        this.productService = productService;
        this.formFactory = formFactory;
    }


    public Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
                Routes.javascriptRouter("jsRoutes",
                        routes.javascript.CartController.guest_addToCart())
        );
    }


    /**
     * view shopping cart
     * @return
     */
    @Transactional
    public Result guest_ViewCart() {
        ShoppingCart currentShoppingCart = cartService.getShoppingCart("001");
        List<ShoppingCartDetail> shoppingCartDetailList = cartService.getShoppingCartDetail("001");
        return ok(shopping_cart.render("cart", currentShoppingCart, shoppingCartDetailList.toArray(new ShoppingCartDetail[shoppingCartDetailList.size()])));
    }


    @Transactional
    public Result guest_addToCart(String item_id) {
        addToCart(Integer.parseInt(item_id));
        return ok();
    }


    @Transactional
    public Result guest_RemoveFromCart(String cart_id, String item_id) {
        ShoppingCartDetail shoppingCartDetail = cartService.getShoppingCartDetail(cart_id, item_id);
        removeFromCart(shoppingCartDetail);
        return guest_ViewCart();
    }



    @Transactional
    public ShoppingCart addToCart(int product_id) {
        Product product = productService.getProduct(product_id);
        cartService.addItemToCart("001", product);
        return cartService.getShoppingCart("001");
    }

    @Transactional
    public void removeFromCart(ShoppingCartDetail shoppingCartDetail) {
        cartService.removeItemFromCart("001", shoppingCartDetail);
    }
}

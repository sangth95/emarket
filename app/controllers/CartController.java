package controllers;

import models.ShoppingCart;
import models.ShoppingCartDetail;
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

    /**
     * view shopping cart
     * @return
     */
    @Transactional
    public Result guest_ViewCart() {
        addToCart();
        ShoppingCart currentShoppingCart = cartService.getShoppingCart("001");
        List<ShoppingCartDetail> shoppingCartDetail = cartService.getShoppingCartDetail("001");
        return ok(shopping_cart.render("cart", currentShoppingCart, shoppingCartDetail.toArray(new ShoppingCartDetail[shoppingCartDetail.size()])));
    }

    @Transactional
    public void addToCart() {
        cartService.addItemToCart("001", productService.getProduct(10));
    }

}

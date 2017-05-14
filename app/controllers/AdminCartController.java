package controllers;

import models.ShoppingCart;
import models.ShoppingCartDetail;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.CartService;
import services.ProductService;
import views.html.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by HongSang on 5/13/2017.
 */
public class AdminCartController extends Controller {
    private CartService cartService;
    private ProductService productService;

    @Inject
    public AdminCartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @Transactional
    public Result admin_getAllShoppingCart() {
        return ok(admin_shoppingcartlist.render(
           "get all shopping cart",
                "" ,
                cartService.getAllShoppingCart().toArray(new ShoppingCart[cartService.getAllShoppingCart().size()])
        ));
    }

    public Result admin_ViewShoppingCartDetailOfCart(int cart_id) {
        return ok(admin_shoppingcartdetail.render(
                "get shopping cart detail",
                cartService.getShoppingCart(cart_id),
                cartService.getShoppingCartDetail(cart_id).toArray(new ShoppingCartDetail[cartService.getShoppingCartDetail(cart_id).size()])
        ));
    }

}

package controllers;

import controllers.*;
import controllers.routes;
import models.Product;
import models.ShoppingCart;
import models.ShoppingCartDetail;
import play.Routes;
import play.api.mvc.Call;
import play.core.j.HttpExecutionContext;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.EmarketDataService;
import services.ServiceFactory;
import views.html.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


/**
 * Created by HongSang on 4/24/2017.
 */
@Singleton
public class CartController extends Controller{
    private static EmarketDataService emarketDataService;
    private FormFactory formFactory;

    @Inject
    play.libs.concurrent.HttpExecutionContext ec;


    @Inject
    public CartController(ServiceFactory serviceFactory, FormFactory formFactory) {
        this.emarketDataService = serviceFactory.getEmarketDataService();
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
        ShoppingCart currentShoppingCart = emarketDataService.getShoppingCart("001");
        List<ShoppingCartDetail> shoppingCartDetailList = emarketDataService.getShoppingCartDetail("001");
        return ok(shopping_cart.render("cart", currentShoppingCart, shoppingCartDetailList.toArray(new ShoppingCartDetail[shoppingCartDetailList.size()])));
    }


    @Transactional
    public Result guest_addToCart(String item_id) {
        CartController.addToCart(Integer.parseInt(item_id));
        return ok();
    }


    @Transactional
    public Result guest_RemoveFromCart(String cart_id, String item_id) {
        ShoppingCartDetail shoppingCartDetail = emarketDataService.getShoppingCartDetail(cart_id, item_id);
        removeFromCart(shoppingCartDetail);
        return guest_ViewCart();
    }



    @Transactional
    public static ShoppingCart addToCart(int product_id) {
        Product product = emarketDataService.getProduct(product_id);
        emarketDataService.addItemToCart("001", product);
        return emarketDataService.getShoppingCart("001");
    }

    @Transactional
    public void removeFromCart(ShoppingCartDetail shoppingCartDetail) {
        emarketDataService.removeItemFromCart("001", shoppingCartDetail);
    }
}

package controllers;

import models.ShoppingCart;
import models.ShoppingCartDetail;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.EmarketDataService;
import services.ServiceFactory;
import views.html.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by HongSang on 4/24/2017.
 */
@Singleton
public class CartController extends Controller{
    private EmarketDataService emarketDataService;
    private FormFactory formFactory;

    @Inject
    public CartController(ServiceFactory serviceFactory, FormFactory formFactory) {
        this.emarketDataService = serviceFactory.getEmarketDataService();
        this.formFactory = formFactory;
    }

    /**
     * view shopping cart
     * @return
     */
    @Transactional
    public Result guest_ViewCart() {
        addToCart();
        ShoppingCart currentShoppingCart = emarketDataService.getShoppingCart("001");
        List<ShoppingCartDetail> shoppingCartDetail = emarketDataService.getShoppingCartDetail("001");
        return ok(shopping_cart.render("cart", currentShoppingCart, shoppingCartDetail.toArray(new ShoppingCartDetail[shoppingCartDetail.size()])));
    }

    @Transactional
    public void addToCart() {
        emarketDataService.addItemToCart("001", emarketDataService.getProduct(10));
    }

}

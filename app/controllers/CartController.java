package controllers;

import models.Product;
import models.ShoppingCart;
import models.ShoppingCartDetail;
import play.Routes;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.CartService;
import services.ProductService;
import views.html.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.SocketPermission;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
        int currentCartIdStr = Integer.parseInt(session("currentCartId"));
        ShoppingCart currentShoppingCart = cartService.getShoppingCart(currentCartIdStr);
        List<ShoppingCartDetail> shoppingCartDetailList = cartService.getShoppingCartDetail(currentCartIdStr);
        return ok(shopping_cart.render("cart", currentShoppingCart, shoppingCartDetailList.toArray(new ShoppingCartDetail[shoppingCartDetailList.size()])));
    }


    @Transactional
    public Result guest_addToCart(String item_id) {
        addToCart(Integer.parseInt(item_id));
        return ok();
    }


    @Transactional
    public Result guest_RemoveFromCart(int cart_id, String item_id) {
        removeFromCart(cart_id, item_id);
        return guest_ViewCart();
    }



    @Transactional
    public ShoppingCart addToCart(int product_id) {
        int currentCartIdStr = Integer.parseInt(session("currentCartId"));
        Product product = productService.getProduct(product_id);
        cartService.addItemToCart(currentCartIdStr, product);
        return cartService.getShoppingCart(currentCartIdStr);
    }

    @Transactional
    public Result completeOder() {
        int currentCartIdStr = Integer.parseInt(session("currentCartId"));
        ShoppingCart currentCart = cartService.getShoppingCart(currentCartIdStr);

        DynamicForm form = formFactory.form().bindFromRequest();
        Http.MultipartFormData formData = request().body().asMultipartFormData();

        String inputName = form.get("inputName");
        String inputEmail = form.get("inputEmail");
        String inputAddress = form.get("inputAddress");
        String inputPhoneNumber = form.get("inputPhoneNumber");

        if (   inputName.isEmpty()
                || inputEmail.isEmpty()
                || inputAddress.isEmpty()
                || inputPhoneNumber.isEmpty()) {
            flash("info_err", "You need to fill in all infomation!");
            return guest_ViewCart();
        }
        else {
            currentCart.setUserId(inputEmail);
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            currentCart.setDate(timeStamp);

            //complete
            currentCart.setComplete(1);

            cartService.updateShoppingCart(currentCart);

            return ok(order_result.render("order result", inputName, inputAddress, inputPhoneNumber, currentCart));
        }
    }

    @Transactional
    public void removeFromCart(int cart_id, String item_id) {
        cartService.removeItemFromCart(cart_id, item_id);
    }
}

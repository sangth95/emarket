package controllers;

import dao.ShoppingCartDetail;
import models.Product;
import models.ShoppingCart;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.*;

import services.EmarketDataService;
import services.ServiceFactory;
import views.html.*;

import javax.inject.Inject;
import javax.inject.Singleton;

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
        //return ok(demo.render("Emarket title", "Welcome to emarket"));
       Product product = emarketDataService.getProduct(1);
       return ok(index.render("blah blah", emarketDataService.getProduct(1)));
    }


    //get product detail
    public Result getProductDetail(Integer id) {
        Product product = emarketDataService.getProduct(id);
        return ok(product_detail.render("product detail",product));
    }

    /**
     * get cart
     * @return
     */
    @Transactional
    public Result guest_ViewCart() {
        ShoppingCart currentShoppingCart = emarketDataService.getShoppingCart("001");
        ShoppingCartDetail shoppingCartDetail = emarketDataService.getShoppingCartDetail("001");
        return ok(product_summary.render("cart", currentShoppingCart));
    }

    //getSpecialOffer
    public Result getSpecialOffer() {
        return ok(special_offer.render("special offer"));
    }

    //getDelivery
    public Result guest_ViewDelivery(){
      return ok(normal.render("view delivery"));
    }

    //getContact
    public Result getContact() {
      return ok(contact.render("contact"));
    }
}

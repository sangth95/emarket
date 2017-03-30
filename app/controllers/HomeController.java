package controllers;

import models.Product;
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

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

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
    public Result guest_ViewCart() {
        return ok(product_summary.render("cart"));
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

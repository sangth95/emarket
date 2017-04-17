package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.*;

import services.EmarketDataService;
import services.ServiceFactory;
import views.html.*;

import  models.Product;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.text.html.HTML;
import java.util.List;


@Singleton
public class ProductController extends Controller {

    private EmarketDataService emarketDataService;

    private final FormFactory formFactory;

    @Inject
    public ProductController(ServiceFactory serviceFactory, FormFactory formFactory) {
        emarketDataService = serviceFactory.getEmarketDataService();
        this.formFactory = formFactory;
    }

    /**
     * get product by id
     * @param id
     * @return
     */
    @Transactional
    public Result product(Integer id) {
        Product product = emarketDataService.getProduct(id);
        return ok(product_detail.render(product.getName(), product));
    }

    //get product list
    @Transactional
    public Result getProducts() {
        List<Product> productList = emarketDataService.getProducts();
        Product[] products = productList.toArray(new Product[productList.size()]);
        return ok(product.render("nothing", products));
    }

    /**
     * get product list by type
     * @param type
     * @return
     */
    @Transactional
    public Result guest_ViewProductListByType(String type) {
        List<Product> productList = emarketDataService.getProducts();
        Product[] products = productList.toArray(new Product[productList.size()]);
        return ok(product.render("get product list by type", products));
    }

    /**
     * search product by key
     * @param key
     * @return
     */
    @Transactional
    public Result guest_SearchProduct(String key) {
        List<Product> productList = emarketDataService.getProducts();
        Product[] products = productList.toArray(new Product[productList.size()]);
        return ok(product.render("search product by key", products));
    }

    /**
     * post method
     * @return
     */
    @Transactional
    public Result searchProductByForm() {
        DynamicForm form = formFactory.form().bindFromRequest();
        String key = form.get("srchFld");
        return guest_SearchProduct(key);
    }
}
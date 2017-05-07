package controllers;

import Helper.Sort;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.*;


import services.ProductService;
import views.html.*;

import  models.Product;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


public class ProductController extends Controller {

    private ProductService productService;

    private final FormFactory formFactory;

    @Inject
    public ProductController(ProductService productService, FormFactory formFactory) {
        this.productService = productService;
        this.formFactory = formFactory;
    }

    /**
     * get product by id
     * @param id
     * @return
     */
    @Transactional
    public Result product(Integer id) {
        Product product = productService.getProduct(id);
        return ok(product_detail.render(product.getName(), product));
    }

    //get product list
    @Transactional
    public Result getProducts() {
        List<Product> productList = productService.getProducts();
        Product[] products = productList.toArray(new Product[productList.size()]);
        return ok(product.render("nothing", "", "", products));
    }

    /**
     * get product list by category
     * @param categoryName
     * @return
     */
    @Transactional
    public Result guest_ViewProductListByCategory(String categoryName, String sortType) {
        List<Product> productList = productService.getProducts("get by category", categoryName);
        Product[] products = productList.toArray(new Product[productList.size()]);

        if (sortType.equals("Product name A - Z")) {
            Sort.sortByName(products, 0);
        } else if (sortType.equals("Product name Z - A")) {
            Sort.sortByName(products, 1);
        } else if (sortType.equals("Price Highest first")) {
            Sort.sortByPrice(products, 1);
        } else if (sortType.equals("Price Lowest first")) {
            Sort.sortByPrice(products, 0);
        }

        return ok(product.render("get product list by category", categoryName, sortType, products));
    }

    /**
     * search product by key
     * @param key
     * @return
     */
    @Transactional
    public Result guest_SearchProduct(String key, String sortType) {
        List<Product> productList = productService.getProducts("get all",key);
        Product[] products = productList.toArray(new Product[productList.size()]);

        if (sortType.equals("Product name A - Z")) {
            Sort.sortByName(products, 0);
        } else if (sortType.equals("Product name Z - A")) {
            Sort.sortByName(products, 1);
        } else if (sortType.equals("Price Highest first")) {
            Sort.sortByPrice(products, 1);
        } else if (sortType.equals("Price Lowest first")) {
            Sort.sortByPrice(products, 0);
        }
        return ok(product.render("search product by key", key, sortType, products));
    }

    /**
     * post method
     * @return
     */
    @Transactional
    public Result searchProductByForm() {
        DynamicForm form = formFactory.form().bindFromRequest();
        String key = form.get("srchFld");
        return guest_SearchProduct(key, "");
    }

    /**
     * sort product
     * @return
     */
    @Transactional
    public Result sortProduct(String title, String categoryName) {
        DynamicForm form = formFactory.form().bindFromRequest();
        String sortType = form.get("sortType");
        if (title.equals("get product list by category"))
            return guest_ViewProductListByCategory(categoryName, sortType);
        else {
            return guest_SearchProduct(categoryName, sortType);
        }
    }

}
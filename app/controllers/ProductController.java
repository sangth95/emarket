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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Singleton
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
    public Result guest_ViewProductListByCategory(String categoryName, String sortType, String price) {
        List<Product> productList = productService.getProducts("get by category", categoryName);
        Product[] products = productList.toArray(new Product[productList.size()]);

        if (sortType != null && sortType.equals("Product name A - Z")) {
            Sort.sortByName(products, 0);
        } else if (sortType != null && sortType.equals("Product name Z - A")) {
            Sort.sortByName(products, 1);
        } else if (sortType != null && sortType.equals("Price Highest first")) {
            Sort.sortByPrice(products, 1);
        } else if (sortType != null && sortType.equals("Price Lowest first")) {
            Sort.sortByPrice(products, 0);
        }

        Integer from = 0;
        Integer to = Integer.MAX_VALUE;
        if (null != price) {
            switch (price) {
                case "Less than 3000000 đ":
                    to = 2999999;
                    break;
                case "3000000 - 5000000 đ":
                    from = 3000000;
                    to = 4999999;
                    break;
                case "More than 5000000 đ":
                    from = 5000000;
                    break;
            }
        }

        List<Product> pl = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() >= from && product.getPrice() <= to) {
                pl.add(product);
            }

        }


        return ok(product.render("get product list by category", categoryName, sortType, pl.toArray(new Product[pl.size()])));
    }

    /**
     * search product by key
     * @param key
     * @return
     */
    @Transactional
    public Result guest_SearchProduct(String key, String sortType, String price) {
        List<Product> productList = productService.getProducts("get all",key);
        Product[] products = productList.toArray(new Product[productList.size()]);

        if (sortType != null && sortType.equals("Product name A - Z")) {
            Sort.sortByName(products, 0);
        } else if (sortType != null && sortType.equals("Product name Z - A")) {
            Sort.sortByName(products, 1);
        } else if (sortType != null && sortType.equals("Price Highest first")) {
            Sort.sortByPrice(products, 1);
        } else if (sortType != null && sortType.equals("Price Lowest first")) {
            Sort.sortByPrice(products, 0);
        }

        Integer from = 0;
        Integer to = Integer.MAX_VALUE;
        if (null != price) {
            switch (price) {
                case "Less than 3000000 đ":
                    to = 2999999;
                    break;
                case "3000000 - 5000000 đ":
                    from = 3000000;
                    to = 4999999;
                    break;
                case "More than 5000000 đ":
                    from = 5000000;
                    break;
            }
        }

        List<Product> pl = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() >= from && product.getPrice() <= to) {
                pl.add(product);
            }

        }

        return ok(product.render("search product by key", key, sortType, pl.toArray(new Product[pl.size()])));
    }

    /**
     * post method
     * @return
     */
    public Result searchProductByForm() {
        DynamicForm form = formFactory.form().bindFromRequest();
        String key = form.get("srchFld");
        return redirect(routes.ProductController.guest_SearchProduct(key, "", ""));
    }

    /**
     * sort product
     * @return
     */
    @Transactional
    public Result sortProduct(String title, String categoryName) {
        DynamicForm form = formFactory.form().bindFromRequest();
        String sortType = form.get("sortType");
        String price = form.get("priceBox");
        if (title.equals("get product list by category"))
            return guest_ViewProductListByCategory(categoryName, sortType, price);
        else {
            return guest_SearchProduct(categoryName, sortType, price);
        }
    }

}
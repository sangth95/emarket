package controllers;

import Helper.Sort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Product;
import play.api.libs.json.jackson.JacksonJson;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.ProductService;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by An on 5/23/2017.
 */
public class ProductServiceController extends Controller {
    private ProductService productService;

    @Inject
    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    @Transactional
    public CompletionStage<Result> getProducts() throws JsonProcessingException {
        return CompletableFuture.supplyAsync(() ->  productService.getProducts())
                .thenApply(productList -> ok(Json.toJson(productList)));
    }
    @Transactional
    public CompletionStage<Result> getProduct(Integer id) {
        return CompletableFuture.supplyAsync(() ->  productService.getProduct(id))
                .thenApply(product -> ok(Json.toJson(product)));
    }

    public Result getProductByCategory(String categoryName, String sortType) {
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

        return ok(Json.toJson(products));
    }

    @Transactional
    public CompletionStage<Result> searchProducts(String key) {
        return CompletableFuture.supplyAsync(() -> {
            List<Product> productList = productService.searchProduct(key);
            if (productList.size() > 5)
                productList = productList.subList(0, 6);
            return productList.toArray(new Product[productList.size()]);
        }).thenApply(products -> ok(Json.toJson(products)));
    }
}

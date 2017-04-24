package services;

import models.ShoppingCartDetail;
import models.Product;
import models.ShoppingCart;

import java.util.List;

/**
 * Created by An on 2/20/2017.
 */
public interface EmarketDataService {

    /**
     * PRODUCT
     */
    List<Product> getProducts();
    Product getProduct(Integer id);
    List<Product> getProducts(String behavior, String key);


    /**
     * SHOPPING CART
     */
    ShoppingCart getShoppingCart(String id);
    List<ShoppingCartDetail> getShoppingCartDetail(String id);
    void addItemToCart(String cartID, Product product);
}

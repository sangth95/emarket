package services;

import dao.ShoppingCartDetail;
import models.Product;
import models.ShoppingCart;

import java.util.List;

/**
 * Created by An on 2/20/2017.
 */
public interface EmarketDataService {
    List<Product> getProducts();
    Product getProduct(Integer id);
    ShoppingCart getShoppingCart(String id);
    ShoppingCartDetail getShoppingCartDetail(String id);
}

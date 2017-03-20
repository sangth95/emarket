package services;

import models.Product;

import java.util.List;

/**
 * Created by An on 2/20/2017.
 */
public interface EmarketDataService {
    List<Product> getProducts();
    Product getProduct(Integer id);
}

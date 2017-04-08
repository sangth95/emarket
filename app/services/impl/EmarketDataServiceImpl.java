package services.impl;

import dao.ProductDao;
import models.Product;
import services.EmarketDataService;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by An on 3/1/2017.
 */
@Singleton
public class EmarketDataServiceImpl implements EmarketDataService {
    private ProductDao productDao;
    @Inject
    public EmarketDataServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getProductList();
    }

    @Override
    public Product getProduct(Integer id) {
        return productDao.get(id);
    }
}

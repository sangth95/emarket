package dao;

import com.typesafe.config.ConfigException;
import models.Product;


import javax.inject.Singleton;

import java.util.List;

import static play.db.jpa.JPA.em;

/**
 * Created by An on 3/1/2017.
 */
@Singleton
public class ProductDao {
    public Product get(Integer id) {
        return em().find(Product.class, id);
    }

    public List<Product> getProductList() {
        return em().createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}
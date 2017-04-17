package dao;

import com.typesafe.config.ConfigException;
import models.Product;


import javax.inject.Singleton;
import javax.persistence.Query;

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
        List<Product> products = em().createNamedQuery("Product.getAll", Product.class).getResultList();
        return products;
    }

    public  List<Product> getProductList(String behavior, String keyword) {
        Query query;
        if(behavior.equals("get all")) {
            query = em().createNamedQuery("Product.getByText", Product.class)
                    .setParameter("keyword", "%" + keyword + "%");
        } else if (behavior.equals("get by category")) {
            query = em().createNamedQuery("Product.getByCategory", Product.class)
                    .setParameter("category", keyword);
        } else {
            query = em().createNamedQuery("Product.getAll", Product.class);
        }
        List<Product> productList = query.getResultList();
        return productList;
    }
}
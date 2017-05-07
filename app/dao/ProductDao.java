package dao;

import com.typesafe.config.ConfigException;
import models.Product;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;


import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;
import java.util.function.Function;


/**
 * Created by An on 3/1/2017.
 */
@Singleton
public class ProductDao {

    private JPAApi jpaApi;

    @Inject
    public ProductDao(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Product get(Integer id) {
        return jpaApi.withTransaction(new Function<EntityManager, Product>() {
            @Override
            public Product apply(EntityManager entityManager) {
                return jpaApi.em().find(Product.class, id);
            }
        });
    }

    public List<Product> getProductList() {
        List<Product> products = jpaApi.em().createNamedQuery("Product.getAll", Product.class).getResultList();
        return products;
    }

    public  List<Product> getProductList(String behavior, String keyword) {
        Query query;
        if(behavior.equals("get all")) {
            query = jpaApi.em().createNamedQuery("Product.getByText", Product.class)
                    .setParameter("keyword", "%" + keyword + "%");
        } else if (behavior.equals("get by category")) {
            query = jpaApi.em().createNamedQuery("Product.getByCategory", Product.class)
                    .setParameter("category", keyword);
        } else {
            query = jpaApi.em().createNamedQuery("Product.getAll", Product.class);
        }
        List<Product> productList = query.getResultList();
        return productList;
    }
}
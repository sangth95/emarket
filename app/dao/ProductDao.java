package dao;

import com.typesafe.config.ConfigException;
import models.Product;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import java.util.ArrayList;
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
        return null;
    }
}

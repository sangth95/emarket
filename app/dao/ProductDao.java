package dao;

import com.typesafe.config.ConfigException;
import models.Product;
import models.ShoppingCartDetail;
import org.hibernate.validator.internal.util.IgnoreJava6Requirement;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;


import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


/**
 * Created by An on 3/1/2017.
 */
@Singleton
public class ProductDao {

    private JPAApi jpaApi;
    private ShoppingCartDao shoppingCartDao;

    @Inject
    public ProductDao(JPAApi jpaApi, ShoppingCartDao shoppingCartDao) {
        this.jpaApi = jpaApi;
        this.shoppingCartDao = shoppingCartDao;
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

    public boolean addProduct(Product product)
    {
        jpaApi.em().persist(product);
        return true;
    }

    public boolean updateProduct(int product_id, Product tmp_product) {
        Product product = jpaApi.em().find(Product.class, product_id);

        product.setName(tmp_product.getName());
        product.setPrice(tmp_product.getPrice());
        product.setManufacturer(tmp_product.getManufacturer());
        product.setShortDescription(tmp_product.getShortDescription());
        product.setInformationDetail(tmp_product.getInformationDetail());
        product.setDescription(tmp_product.getDescription());
        product.setCategory(tmp_product.getCategory());
        product.setWarranty(tmp_product.getWarranty());
        product.setAvatar(tmp_product.getAvatar());
        product.setPictures(tmp_product.getPictures());

        return true;
    }

    public boolean removeProduct(int id) {
        Product product = jpaApi.em().find(Product.class, id);
        ArrayList<ShoppingCartDetail> shoppingCartDetailArrayList = (ArrayList<ShoppingCartDetail>) shoppingCartDao.getShoppingCartDetailByProductId(id);

        if(jpaApi.em().contains(product)){
            for(ShoppingCartDetail shoppingCartDetail : shoppingCartDetailArrayList) {
                jpaApi.em().remove(shoppingCartDetail);
            }
            jpaApi.em().remove(jpaApi.em().merge(product));
            return true;
        }
        return false;
    }


    public List<Product> getProductList() {
        return jpaApi.withTransaction(() -> jpaApi.em().createNamedQuery("Product.getAll", Product.class).getResultList());
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

    public List<Product> search(String key) {
        Query query = jpaApi.em().createNamedQuery("Product.getByText", Product.class)
                .setParameter("keyword", "%" + key + "%");
        List<Product> productList = query.getResultList();
        return productList;
    }
}
package dao;

import models.Product;
import models.ShoppingCart;
import models.ShoppingCartDetail;
import play.db.jpa.JPAApi;
import sun.rmi.runtime.Log;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;
import java.util.function.Function;

import static  play.db.jpa.JPA.em;

/**
 * Created by HongSang on 4/6/2017.
 */

@Singleton
public class ShoppingCartDao {
    private  final JPAApi jpaApi;

    @Inject
    public ShoppingCartDao(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    public ShoppingCart getShoppingCart(String id) {
        return jpaApi.withTransaction(new Function<EntityManager, ShoppingCart>() {
            @Override
            public ShoppingCart apply(EntityManager entityManager) {
                return jpaApi.em().find(ShoppingCart.class, id);
            }
        });
    }

    public List<ShoppingCartDetail> getShoppingCartDetail(String cart_id) {
        return jpaApi.withTransaction(new Function<EntityManager, List<ShoppingCartDetail>>() {
            @Override
            public List<ShoppingCartDetail> apply(EntityManager entityManager) {
                Query query = jpaApi.em().createNamedQuery("ShoppingCartDetail.getByCartId", ShoppingCartDetail.class)
                        .setParameter("cart_id", cart_id);

                List<ShoppingCartDetail> shoppingCartDetailList = query.getResultList();
                return shoppingCartDetailList;
            }
        });
    }

    public ShoppingCartDetail getShoppingCartDetail(String cart_id, String item_id) {
        Query query = em().createNamedQuery("ShoppingCartDetail.getByCartIdAndItemId", ShoppingCartDetail.class)
                .setParameter("cart_id", cart_id)
                .setParameter("item_id", Integer.parseInt(item_id));
        return (ShoppingCartDetail) query.getResultList().get(0);
    }

    public void addItemToCart(String cartID, Product product) {
        EntityManager entityManager = jpaApi.em("default");
        try {
            ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail(
                    cartID,
                    product,
                    1,
                    product.getPrice()
            );
            entityManager.getTransaction().begin();
            entityManager.persist(shoppingCartDetail);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }

    public void removeFromCart(String cartID, ShoppingCartDetail shoppingCartDetail) {
        EntityManager entityManager = jpaApi.em("default");
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(shoppingCartDetail));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}

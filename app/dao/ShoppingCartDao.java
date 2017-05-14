package dao;

import models.Product;
import models.ShoppingCart;
import models.ShoppingCartDetail;
import play.db.jpa.JPAApi;
import sun.rmi.runtime.Log;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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


    public void saveShoppingCart(ShoppingCart cart) {
        jpaApi.em().persist(cart);
    }

    public ShoppingCart getShoppingCart(int id) {
        return jpaApi.withTransaction(new Function<EntityManager, ShoppingCart>() {
            @Override
            public ShoppingCart apply(EntityManager entityManager) {
                return jpaApi.em().find(ShoppingCart.class, id);
            }
        });
    }

    public List<ShoppingCart> getAllShoppingCart() {
        return jpaApi.em().createNamedQuery("shoppingCart.getAll", ShoppingCart.class).getResultList();
    }

    public List<ShoppingCartDetail> getShoppingCartDetail(int cart_id) {
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

    public ShoppingCartDetail getShoppingCartDetail(int cart_id, String item_id) {
        Query query = em().createNamedQuery("ShoppingCartDetail.getByCartIdAndItemId", ShoppingCartDetail.class)
                .setParameter("cart_id", cart_id)
                .setParameter("item_id", Integer.parseInt(item_id));
        return (ShoppingCartDetail) query.getResultList().get(0);
    }

    public List<ShoppingCartDetail> getShoppingCartDetailByProductId(int product_id) {
        Query query = jpaApi.em().createNamedQuery("ShoppingCartDetail.getByProductId", ShoppingCartDetail.class);
        query.setParameter("productid", product_id);
        return query.getResultList();
    }

    public void addItemToCart(int cartID, Product product) {
        EntityManager entityManager = jpaApi.em("default");
        try {
            ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail(
                    cartID,
                    product,
                    1,
                    product.getPrice()
            );
            entityManager.getTransaction().begin();
            System.out.println("shopping cart id: " + shoppingCartDetail.getId());
            entityManager.persist(shoppingCartDetail);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void updateShoppingCart(ShoppingCart cart) {
        ShoppingCart shoppingCart = jpaApi.em().find(ShoppingCart.class, cart.getId());
        shoppingCart.setUserId(cart.getUserId());
        shoppingCart.setDate(cart.getDate());
    }

    public void removeFromCart(int cart_id, String item_id) {
        EntityManager entityManager = jpaApi.em("default");
        try {
            ShoppingCartDetail shoppingCartDetail = getShoppingCartDetail(cart_id, item_id);
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

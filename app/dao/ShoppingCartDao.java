package dao;

import models.Product;
import models.ShoppingCart;
import models.ShoppingCartDetail;

import javax.inject.Singleton;
import javax.persistence.Query;

import java.util.List;

import static  play.db.jpa.JPA.em;

/**
 * Created by HongSang on 4/6/2017.
 */

@Singleton
public class ShoppingCartDao {
    public ShoppingCart getShoppingCart(String id) {
        return em().find(ShoppingCart.class, id);
    }

    public List<ShoppingCartDetail> getShoppingCartDetail(String cart_id) {
        Query query = em().createNamedQuery("ShoppingCartDetail.getByCartId", ShoppingCartDetail.class)
                .setParameter("cart_id", cart_id);

        List<ShoppingCartDetail> shoppingCartDetailList = query.getResultList();
        return shoppingCartDetailList;
    }

    public void addItemToCart(String cartID, Product product) {
        em().getTransaction().begin();
        ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail(
                cartID,
                product,
                1,
                product.getPrice()
        );
        em().persist(shoppingCartDetail);
        em().getTransaction().commit();
    }
}

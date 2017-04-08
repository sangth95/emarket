package dao;

import models.ShoppingCart;

import javax.inject.Singleton;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import static  play.db.jpa.JPA.em;

/**
 * Created by HongSang on 4/6/2017.
 */

@Singleton
public class ShoppingCartDao {
    public ShoppingCart getShoppingCart(String id) {
        return em().find(ShoppingCart.class, id);
    }

    public ShoppingCartDetail getShoppingCartDetail() {
        return null;
    }
}

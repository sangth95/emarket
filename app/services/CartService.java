package services;

import dao.ShoppingCartDao;
import models.Product;
import models.ShoppingCart;
import models.ShoppingCartDetail;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by An on 5/7/2017.
 */
@Singleton
public class CartService {

    private ShoppingCartDao shoppingCartDao;

    @Inject
    public CartService(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    public ShoppingCart getShoppingCart(String id) {
        return shoppingCartDao.getShoppingCart(id);
    }

    public List<ShoppingCartDetail> getShoppingCartDetail(String id) {
        return shoppingCartDao.getShoppingCartDetail(id);
    }

    public void addItemToCart(String cartID, Product product) {
        shoppingCartDao.addItemToCart(cartID, product);
    }

    public ShoppingCartDetail getShoppingCartDetail(String cart_id, String item_id) {
        return shoppingCartDao.getShoppingCartDetail(cart_id, item_id);
    }

    public void removeItemFromCart(String cartID, ShoppingCartDetail shoppingCartDetail) {
        shoppingCartDao.removeFromCart(cartID, shoppingCartDetail);
    }
}
package services;

import dao.CartDao;
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

    private CartDao shoppingCartDao;

    @Inject
    public CartService(CartDao shoppingCart) {
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
}
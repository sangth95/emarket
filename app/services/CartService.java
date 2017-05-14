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

    public void saveShoppingCart(ShoppingCart cart) {
        shoppingCartDao.saveShoppingCart(cart);
    }

    public List<ShoppingCart> getAllShoppingCart() {
        return shoppingCartDao.getAllShoppingCart();
    }

    public ShoppingCart getShoppingCart(int id) {
        return shoppingCartDao.getShoppingCart(id);
    }

    public List<ShoppingCartDetail> getShoppingCartDetail(int id) {
        return shoppingCartDao.getShoppingCartDetail(id);
    }

    public void addItemToCart(int cartID, Product product) {
        shoppingCartDao.addItemToCart(cartID, product);
    }

    public ShoppingCartDetail getShoppingCartDetail(int cart_id, String item_id) {
        return shoppingCartDao.getShoppingCartDetail(cart_id, item_id);
    }

    public void removeItemFromCart(int cart_id, String item_id) {
        shoppingCartDao.removeFromCart(cart_id, item_id);
    }

    public List<ShoppingCartDetail> getShoppingCartDetailByProductId(int product_id) {
        return shoppingCartDao.getShoppingCartDetailByProductId(product_id);
    }

    public void updateShoppingCart(ShoppingCart cart) {
        shoppingCartDao.updateShoppingCart(cart);
    }
}
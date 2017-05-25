package controllers;

import models.ShoppingCart;
import models.ShoppingCartDetail;
import play.db.jpa.Transactional;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.mvc.Controller;
import play.mvc.Result;
import services.CartService;
import services.ProductService;
import views.html.*;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by HongSang on 5/13/2017.
 */
public class AdminCartController extends Controller {
    private CartService cartService;
    private ProductService productService;

    @Inject
    public AdminCartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @Transactional
    public Result admin_getAllShoppingCart() {
        return ok(admin_shoppingcartlist.render(
           "get all shopping cart",
                "" ,
                cartService.getAllShoppingCart().toArray(new ShoppingCart[cartService.getAllShoppingCart().size()])
        ));
    }

    public Result admin_ViewShoppingCartDetailOfCart(int cart_id) {
        return ok(admin_shoppingcartdetail.render(
                "get shopping cart detail",
                cartService.getShoppingCart(cart_id),
                cartService.getShoppingCartDetail(cart_id).toArray(new ShoppingCartDetail[cartService.getShoppingCartDetail(cart_id).size()])
        ));
    }

    @Inject
    MailerClient mailerClient;

    @Transactional
    public Result admin_ProcessOrder(int id) {
        ArrayList<ShoppingCartDetail> shoppingCartDetailArrayList = (ArrayList<ShoppingCartDetail>) cartService.getShoppingCartDetail(id);
        ShoppingCart cart = cartService.getShoppingCart(id);
        String htmlBody = "<table border='1' cellpadding='0' cellspacing='0'>\n" +
                "      <thead>\n" +
                "        <tr>\n" +
                "          <th>Product</th>\n" +
                "          <th>Description</th>\n" +
                "          <th>Price</th>\n" +
                "          <th>Discount</th>\n" +
                "          <th>Tax</th>\n" +
                "          <th>Total</th>\n" +
                "        </tr>\n" +
                "      </thead>\n" +
                "      <tbody>";

        for(ShoppingCartDetail shoppingCartDetail : shoppingCartDetailArrayList) {
            htmlBody += "<tr>\n" +
                    "            <td>" + shoppingCartDetail.getItem().getName() + "</td>\n" +
                    "            <td>" + shoppingCartDetail.getItem().get50CharsOfDescription() + "</td>\n" +
                    "            <td>" + shoppingCartDetail.getPrice() + "</td> <!-- price -->\n" +
                    "            <td>$0.00</td> <!-- discount -->\n" +
                    "            <td>$0.00</td> <!-- tax -->\n" +
                    "            <td>" + shoppingCartDetail.getPrice() + "</td> <!-- total -->\n" +
                    "          </tr>";
        }

        htmlBody += "<tr>\n" +
                "          <td colspan=\"5\" style=\"text-align:right\">Total Price:\t</td>\n" +
                "          <td> @cart.getTotalPrice</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td colspan=\"5\" style=\"text-align:right\">Total Discount:\t</td>\n" +
                "          <td> $0.00</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td colspan=\"5\" style=\"text-align:right\">Total Tax:\t</td>\n" +
                "          <td> $0.00</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td colspan=\"5\" style=\"text-align:right\"><strong>TOTAL =</strong></td>\n" +
                "          <td class=\"label label-important\" style=\"display:block\">\n" +
                "            <strong>\n" + cart.getTotalPrice() +
                "            </strong>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>";


        Email email = new Email()
                .setSubject("Confirm your order")
                .setFrom("E-Shop <from@email.com>")
                .addCc("truonghongsanglk95@gmail.com")
                .addTo("adamtruonglk@gmail.com")
                .setBodyHtml(htmlBody);
        mailerClient.send(email);
        //mark done
        cart.setComplete(2);
        cartService.updateShoppingCart(cart);
        return admin_getAllShoppingCart();
    }

}

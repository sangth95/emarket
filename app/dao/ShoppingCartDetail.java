package dao;

import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.Constraint;

/**
 * Created by HongSang on 4/6/2017.
 */

@Entity
@Table(name = "shopping_cart_detail")
public class ShoppingCartDetail {
    @Id
    @Constraints.Required
    @JoinColumn(name = "shopping_cart", referencedColumnName = "id")
    @Column(name = "id")
    private String id;


    @Column(name = "item_id")
    private String itemId;

    @Column(name = "quatity")
    private int quatity;

    @Column(name = "price")
    private int price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

package models;


import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by HongSang on 4/6/2017.
 */

@Entity
@Table(name = "shopping_cart_detail")

@NamedQueries({

        @NamedQuery(name = "ShoppingCartDetail.getAll", query = "SELECT scd FROM ShoppingCartDetail scd"),

        @NamedQuery(name = "ShoppingCartDetail.getByCartId", query = "SELECT DISTINCT scd " +
                                                                     "FROM ShoppingCartDetail scd " +
                                                                     "WHERE scd.cart_id = :cart_id"),
        @NamedQuery(name = "ShoppingCartDetail.getByProductId", query = "SELECT scd " +
                                                                        "FROM ShoppingCartDetail scd " +
                                                                        "WHERE scd.item.id = :productid"),
        @NamedQuery(name = "ShoppingCartDetail.getByCartIdAndItemId", query = "SELECT DISTINCT scd " +
                                                                              "FROM ShoppingCartDetail scd " +
                                                                              "WHERE scd.cart_id = :cart_id " +
                                                                              "AND scd.item.id = :item_id")
})

public class ShoppingCartDetail {

    @TableGenerator(name = "shoppingcartdetail_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "shoppingcartdetail_gen")
    @Constraints.Required
    private int id;

    @Column(name = "cart_id")
    private int cart_id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "cart_product_item_pk"))
    private Product item;

    @Column(name = "quatity")
    private int quatity;

    @Column(name = "price")
    private int price;

    public ShoppingCartDetail() {
    }

    public ShoppingCartDetail(int cart_id, Product item, int quatity, int price) {
        this.cart_id = cart_id;
        this.item = item;
        this.quatity = quatity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }
}



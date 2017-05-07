package models;


import play.data.validation.Constraints;

import javax.persistence.*;

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
        @NamedQuery(name = "ShoppingCartDetail.getByCartIdAndItemId", query = "SELECT DISTINCT scd " +
                                                                              "FROM ShoppingCartDetail scd " +
                                                                              "WHERE scd.cart_id = :cart_id " +
                                                                              "AND scd.item.id = :item_id")
})

public class ShoppingCartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Constraints.Required
    private int id;

    @Column(name = "cart_id")
    private String cart_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "cart_product_item_pk"))
    private Product item;

    @Column(name = "quatity")
    private int quatity;

    @Column(name = "price")
    private int price;

    public ShoppingCartDetail() {
    }

    public ShoppingCartDetail(String cart_id, Product item, int quatity, int price) {
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

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }
}



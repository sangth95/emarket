package models;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Id;

import dao.ShoppingCartDao;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.Constraint;
import java.util.DoubleSummaryStatistics;
import java.util.List;


/**
 * Created by HongSang on 4/6/2017.
 */

@Entity
@Table(name = "shopping_cart")
@NamedQueries({
        @NamedQuery(name = "shoppingCart.getAll", query = "SELECT sc FROM ShoppingCart sc")
})
public class ShoppingCart {
    @TableGenerator(name = "shoppingcart_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "shoppingcart_gen")
    @Constraints.Required
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private String userId;

    private String date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart_id")
    private List<ShoppingCartDetail> shoppingCartDetailList;

    public List<ShoppingCartDetail> getShoppingCartDetailList() {
        return shoppingCartDetailList;
    }

    public void setShoppingCartDetailList(List<ShoppingCartDetail> shoppingCartDetailList) {
        this.shoppingCartDetailList = shoppingCartDetailList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTotalPrice() {
        long totalPrice = 0;
        for(ShoppingCartDetail shoppingCartDetail : getShoppingCartDetailList()) {
            totalPrice += shoppingCartDetail.getPrice();
        }
        return totalPrice;
    }

}

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
public class ShoppingCart {
    @Id
    @Constraints.Required
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

}

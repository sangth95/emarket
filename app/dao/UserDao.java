package dao;

import models.User;
import org.springframework.util.CollectionUtils;
import javax.inject.Singleton;

import java.util.List;

import static play.db.jpa.JPA.em;

/**
 * Created by An on 5/14/2017.
 */
@Singleton
public class UserDao {

    public User getUser(String username, String hashPassword) {
        List<User> users = em()
                        .createNamedQuery("User.getByUsernameAndHashPassword", User.class)
                        .setParameter("username", username)
                        .setParameter("hashPassword", hashPassword)
                        .getResultList();

        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        else {
            return users.get(0);
        }
    }

    public User getUser(String username) {
        List<User> users =  em()
                        .createNamedQuery("User.getByUsername", User.class)
                        .setParameter("username", username)
                        .getResultList();

        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        else {
            return users.get(0);
        }
    }
}

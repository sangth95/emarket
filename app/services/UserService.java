package services;

import dao.UserDao;
import models.User;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.security.MessageDigest;

/**
 * Created by An on 5/14/2017.
 */
@Singleton
public class UserService {
    private UserDao userDao;

    @Inject
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(String username, String password) {
        return userDao.getUser(username, getHashPassword(username, password));
    }

    private String getHashPassword(String username, String password)  {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            byte[] buffer = (username + password).getBytes("UTF-8");
            md.update(buffer);
            byte[] digest = md.digest();

            String hexStr = "";
            for (int i = 0; i < digest.length; i++) {
                hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
            }
            return hexStr;
        } catch (Exception e) {
            return "";
        }
    }
}

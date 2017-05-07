package Helper;

import models.Product;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.Objects;

/**
 * Created by HongSang on 4/13/2017.
 */

@Singleton
public class Sort {
    public static void sortByName(Product[] products, int sortType) {
        if (sortType == 0) {        //sort name descesding
            for (int i = 0; i < products.length; i++) {
                for (int j = i + 1; j < products.length; j++) {
                    if (products[i].getName().compareTo(products[j].getName()) > 0) {
                        Product tmp = products[i];
                        products[i] = products[j];
                        products[j] = tmp;
                    }
                }
            }
        } else {                   // sort name ascesding
            for (int i = 0; i < products.length; i++) {
                for (int j = i + 1; j < products.length; j++) {
                    if (products[i].getName().compareTo(products[j].getName()) < 0) {
                        Product tmp = products[i];
                        products[i] = products[j];
                        products[j] = tmp;
                    }
                }
            }
        }
    }

    public static void sortByPrice(Product[] products, int sortType) {
        if (sortType == 0) {            //asc
            for (int i = 0; i < products.length; i++) {
                for (int j = i + 1; j < products.length; j++) {
                    if (products[i].getPrice() > products[j].getPrice()) {
                        Product tmp = products[i];
                        products[i] = products[j];
                        products[j] = tmp;
                    }
                }
            }
        } else {                        //desc
            for (int i = 0; i < products.length; i++) {
                for (int j = i + 1; j < products.length; j++) {
                    if (products[i].getPrice() < products[j].getPrice()) {
                        Product tmp = products[i];
                        products[i] = products[j];
                        products[j] = tmp;
                    }
                }
            }
        }
    }
}

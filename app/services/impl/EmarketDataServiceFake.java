package services.impl;

import dao.ShoppingCartDetail;
import models.Product;
import models.ShoppingCart;
import services.EmarketDataService;

import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by An on 2/20/2017.
 */
@Singleton
public class EmarketDataServiceFake implements EmarketDataService {

    private List<Product> products;
/*
    public EmarketDataServiceFake() {
        products = new LinkedList<>();
        Product product = new Product();
        product.id = 1;
        product.name = "Laptop Asus GL552VX-DM070D i7-6700HQ 15.6 inch (Đen) - Hãng Phân phối chính thức";
        product.category = 1;
        product.manufacturer = 1;
        product.warranty = "24 tháng";
        product.price = 19500000;
        product.shortDescription = "Intel Core i7 6700HQ 2.6GHz up to 3.5GHz 6MB\n" +
                "8GB DDR4 2133MHz\n" +
                "NVIDIA GeForce GTX 950M 4GB GDDR5 Intel HD Graphics 530\n" +
                "15.6 inch Full HD (1920 x 1080 pixels)";
        product.informationDetail = "SKU\tAS082ELAA1KCXUVNAMZ-2556531\n" +
                "Kích thước màn hình\t13.3\n" +
                "Graphics Card\tIntel\n" +
                "Graphics Memory\t4GB\n" +
                "Hard Drive Capacity\t1TB\n" +
                "Input/Output Ports\tUSB 3.0\n" +
                "Mẫu mã\tTablet Plaza (Tp.HCM)-Laptop Asus GL552VX-DM070D 15.6 inch (Đen)\n" +
                "Hệ điều hành\tDOS\n" +
                "Processor Type\tNot Specified\n" +
                "Kích thước sản phẩm (D x R x C cm)\t37 x 25 x 2\n" +
                "Trọng lượng (KG)\t2.59\n" +
                "Sản xuất tại\tTrung Quốc\n" +
                "Thời gian bảo hành\t2 năm\n" +
                "Loại hình bảo hành\tBằng Phiếu bảo hành và Hóa đơn\n" +
                "Wireless Connectivity\tWifi";
        product.description = "";
        product.pictures = "1-1.jpg\n" +
                "1_2.jpg";
        for (int i = 0; i<12; i++)
            products.add(product);
    }
*/
    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Product getProduct(Integer id) {
        return products.stream().filter(product -> id.equals(product.getId())).findFirst().orElse(null);
    }

    @Override
    public ShoppingCart getShoppingCart(String id) {
        return null;
    }

    @Override
    public ShoppingCartDetail getShoppingCartDetail(String id) {
        return new ShoppingCartDetail();
    }
}

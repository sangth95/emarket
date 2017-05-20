package controllers;

import Helper.Sort;
import dao.ShoppingCartDao;
import models.Category;
import models.Manufacturer;
import models.Product;
import models.ShoppingCartDetail;
import play.api.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;

import play.mvc.Http;
import play.mvc.Result;
import security.RestrictByRole;
import services.CartService;
import services.ProductService;
import views.html.*;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongSang on 5/10/2017.
 */
public class AdminProductController extends Controller {
    private ProductService productService;
    private CartService cartService;
    private FormFactory formFactory;

    @Inject
    public AdminProductController(ProductService productService, CartService cartService, FormFactory formFactory) {
        this.productService = productService;
        this.cartService = cartService;
        this.formFactory = formFactory;
    }


    /**
     * view all product
     * @return
     */
    @Transactional
    @RestrictByRole({"admin"})
    public Result admin_ViewAllProduct() {
        return ok(admin_product.render(
                "admin_viewallproduct",
                "",
                "",
                productService.getProducts().toArray(new Product[productService.getProducts().size()])));
    }

    /**
     * add new product page
     * @return
     */
    @Transactional
    public Result admin_AddNewProductPage() {
        return ok(admin_addnewproduct.render("add new product", ""));
    }

    /**
     * add new product pót method
     * @return
     */
    @Transactional
    public Result admin_AddNewProduct() {
        DynamicForm form = formFactory.form().bindFromRequest();
        Http.MultipartFormData formData = request().body().asMultipartFormData();

        String inputName = form.get("inputName");
        String inputManufacture = form.get("inputManufacture");
        String inputShortDescription = form.get("inputShortDescription");
        String inputPrice = form.get("inputPrice");
        String inputInformationDetail = form.get("inputInformationDetail");
        String inputDescription = form.get("inputDescription");
        String inputCategory = form.get("inputCategory");
        String inputWarranty = form.get("inputWarranty");
        Http.MultipartFormData.FilePart filePart = formData.getFile("inputPicture1");
        String inputPicture2 = form.get("inputPicture2");
        String inputPicture3 = form.get("inputPicture3");

        if (       null != inputName
                && null != inputManufacture
                && null != inputShortDescription
                && null != inputPrice
                && null != inputInformationDetail
                && null != inputDescription
                && null != inputCategory
                && null != filePart){
            Product product = new Product();
            product.setName(inputName);
            product.setManufacturer(new Manufacturer(Integer.parseInt(inputManufacture)));
            product.setShortDescription(inputShortDescription);
            product.setPrice(Integer.parseInt(inputPrice));
            product.setInformationDetail((inputInformationDetail));
            product.setDescription(inputDescription);
            product.setCategory(new Category(Integer.parseInt(inputCategory)));
            product.setPictures(filePart.getFilename());
            product.setAvatar(filePart.getFilename());
            if(null != inputWarranty)
                product.setWarranty(inputWarranty);
            if(null != inputPicture2)
                product.setPictures(product.getPictures() + "\n" + inputPicture2);
            if(null != inputPicture3)
                product.setPictures(product.getPictures() + "\n" + inputPicture3);

            if(null != product)
                productService.addProduct(product);

            //Save image to public directory
            saveImageToPublicDir(filePart);

            return ok(admin_addnewproduct.render("add new product", "Add product successfuly!"));
        }
        return ok(admin_addnewproduct.render("add new product", "Add product fail!"));
    }

    /**
     * update product page
     * @param product_id
     * @return
     */
    @Transactional
    public Result admin_UpdateProductPage(int product_id) {
        return ok(admin_updateproduct.render("update product", productService.getProduct(product_id), ""));
    }

    @Transactional
    public Result admin_UpdateProduct(int product_id) {
        DynamicForm form = formFactory.form().bindFromRequest();
        Http.MultipartFormData formData = request().body().asMultipartFormData();

        String inputName = form.get("inputName");
        String inputManufacture = form.get("inputManufacture");
        String inputShortDescription = form.get("inputShortDescription");
        String inputPrice = form.get("inputPrice");
        String inputInformationDetail = form.get("inputInformationDetail");
        String inputDescription = form.get("inputDescription");
        String inputCategory = form.get("inputCategory");
        String inputWarranty = form.get("inputWarranty");
        Http.MultipartFormData.FilePart filePart = formData.getFile("inputPicture1");
        String inputPicture2 = form.get("inputPicture2");
        String inputPicture3 = form.get("inputPicture3");

        Product product = productService.getProduct(product_id);

        if(null != inputName && !inputName.equals(product.getName())) product.setName(inputName);
        if(!inputManufacture.isEmpty() && !inputManufacture.equals("1")) product.setManufacturer(new Manufacturer(Integer.parseInt(inputManufacture)));
        if(!inputShortDescription.isEmpty() && !inputShortDescription.equals(product.getShortDescription())) product.setShortDescription(inputShortDescription);

        System.out.println("Price: " + inputPrice);
        System.out.println("Price: " + product.getPrice().toString());
        if(!inputPrice.isEmpty() && !inputPrice.equals(product.getPrice().toString())) product.setPrice(Integer.parseInt(inputPrice));

        if(!inputInformationDetail.isEmpty() && !inputInformationDetail.equals(product.getInformationDetail())) product.setInformationDetail((inputInformationDetail));
        if(!inputDescription.isEmpty() && !inputDescription.equals(product.getDescription())) product.setDescription(inputDescription);
        if(!inputCategory.isEmpty() && !inputCategory.equals("2")) product.setCategory(new Category(Integer.parseInt(inputCategory)));
        if(!inputWarranty.isEmpty() && !inputWarranty.equals(product.getWarranty().toString())) product.setPictures(filePart.getFilename());
        if(!filePart.getFilename().isEmpty() && !filePart.getFilename().equals(product.getAvatar())){
            product.setAvatar(filePart.getFilename());
            saveImageToPublicDir(filePart);
        }

        if(!product.getPictures().equals(filePart.getFilename() + "\n" + inputPicture2 + "\n" + inputPicture3));

        //update product
        if (null != product)
            productService.updateProduct(product_id, product);

        return ok(admin_updateproduct.render("update product", productService.getProduct(product_id), "Update product successfully!"));
    }

    private void saveImageToPublicDir(Http.MultipartFormData.FilePart filePart) {
        if (null != filePart) {
            File file = (File) filePart.getFile();

            File dest = new File(Play.current().path().getAbsolutePath().toString() + "/public/images/products/" + filePart.getFilename());
            System.out.println(dest.getPath().toString());
            try {
                java.nio.file.Files.move( file.toPath(),dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * sort product
     * @return
     */
    @Transactional
    public Result sortProduct(String title, String categoryName) {
        DynamicForm form = formFactory.form().bindFromRequest();
        String sortType = form.get("sortType");
        if (title.equals("get product list by category"))
            return admin_ViewProductListByCategory(categoryName, sortType);
        else {
            return admin_SearchProduct(categoryName, sortType);
        }
    }

    /**
     * get product list by category
     * @param categoryName
     * @return
     */
    @Transactional
    public Result admin_ViewProductListByCategory(String categoryName, String sortType) {
        List<Product> productList = productService.getProducts("get by category", categoryName);
        Product[] products = productList.toArray(new Product[productList.size()]);

        if (sortType.equals("Product name A - Z")) {
            Sort.sortByName(products, 0);
        } else if (sortType.equals("Product name Z - A")) {
            Sort.sortByName(products, 1);
        } else if (sortType.equals("Price Highest first")) {
            Sort.sortByPrice(products, 1);
        } else if (sortType.equals("Price Lowest first")) {
            Sort.sortByPrice(products, 0);
        }

        return ok(admin_product.render("get product list by category", categoryName, sortType, products));
    }

    /**
     * search product by key
     * @param key
     * @return
     */
    @Transactional
    public Result admin_SearchProduct(String key, String sortType) {
        List<Product> productList = productService.getProducts("get all",key);
        Product[] products = productList.toArray(new Product[productList.size()]);

        if (sortType.equals("Product name A - Z")) {
            Sort.sortByName(products, 0);
        } else if (sortType.equals("Product name Z - A")) {
            Sort.sortByName(products, 1);
        } else if (sortType.equals("Price Highest first")) {
            Sort.sortByPrice(products, 1);
        } else if (sortType.equals("Price Lowest first")) {
            Sort.sortByPrice(products, 0);
        }
        return ok(admin_product.render("search product by key", key, sortType, products));
    }


    @Transactional
    public Result admin_DeleteProduct(int id) {
        productService.removeProduct(id);
        return admin_ViewAllProduct();
    }
}

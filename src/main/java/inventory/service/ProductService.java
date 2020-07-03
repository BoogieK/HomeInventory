package inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.Product;
import inventory.data.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepo;

    public List<Product> showInventory() {

        List<Product> products = new ArrayList<>();

        productRepo.findAll().forEach(i -> products.add(i));

        return products;
    }

    public void nameVerification(String name) {
        if (name.equals("") || name.equals(" ")) {
            System.out.println("Typo");
        }

    }

    public void standardizationCanned(Product product) {
        if (product.getCan().equals("Non") || product.getCan().equals("non") || product.getCan().equals("No")
                || product.getCan().equals("no") || product.getCan().equals("Nope")
                || product.getCan().equals("nope")) {
            product.setCan("Non");
        } else if (product.getCan().equals("Oui") || product.getCan().equals("oui") || product.getCan().equals("yes")
                || product.getCan().equals("Yes") || product.getCan().equals("yep") || product.getCan().equals("Yep")) {
            product.setCan("Oui");
        }

    }

    public String addProduct(Product product) {
        product.setAvailability(true);
        productRepo.save(product);
        return "Product added";
    }

    public boolean chekingForDuplicate(String name, List<Product> products) {

        int exist = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                exist++;
            }
        }

        if (exist != 0) {
            return true;
        }

        else {
            return false;

        }
    }

    public Long findByName(String name) {
        Long id = null;
        List<Product> products = new ArrayList<>();

        productRepo.findAll().forEach(u -> products.add(u));
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                id = products.get(i).getId();
            }
        }
        return id;
    }

    public Product findById(Long id) {
        return productRepo.findById(id).get();
    }

    public int addQuantity(int addQuantity, int productQuantity) {

        return addQuantity + productQuantity;
    }

    public void updateProductQuantity(int newQuantity, Long id) {

        Product product = productRepo.findById(id).get();

        if (newQuantity <= 0) {
            product.setAvailability(false);
            newQuantity = 0;
        } else if (newQuantity > 0) {
            product.setAvailability(true);
        }

        product.setQuantity(newQuantity);
        productRepo.save(product);

    }

    public void deleteProductById(Long id) {
        productRepo.deleteById(id);
    }

    public int subtractQuantity(int clicksNumber, int selectedProductQuantity) {
        return selectedProductQuantity - clicksNumber;
    }

    public List<Product> uiInventory() {

        List<Product> products = new ArrayList<>();

        productRepo.findAll().forEach(i -> {
            if (i.isAvailable() == true) {
                products.add(i);
            }
        });

        return products;
    }

}

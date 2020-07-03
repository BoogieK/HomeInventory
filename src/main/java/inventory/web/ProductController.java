package inventory.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import inventory.Product;
import inventory.Type;
import inventory.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private List<Product> products;

    @RequestMapping(method = RequestMethod.GET, value = "/inventory")
    public String showUIInventory(Model model) {

        products = productService.uiInventory();
        model.addAttribute("products", products);
        return "inventory";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public String modifyInventory(Model model, HttpServletRequest request, Long id) {

        Product selectedProduct = productService.findById(id);
        int selectedProductQuantity = selectedProduct.getQuantity();
        int result = 0;
        int clicksNumber = 1;

        String subtractButton = request.getParameter("Subtract");
        String addButton = request.getParameter("Add");

        if (subtractButton != null) {
            result = productService.subtractQuantity(clicksNumber, selectedProductQuantity);
        } else if (addButton != null) {
            result = productService.addQuantity(clicksNumber, selectedProductQuantity);
        }

        productService.updateProductQuantity(result, id);
        products = productService.uiInventory();
        // Given name + array
        model.addAttribute("products", products);
        return "inventory";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String formForAddingProduct() {
        return "add";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addProduct(Product product, Type type) {

        boolean duplicate = false;
        Long id;
        int newQuantity = 0;

        products = productService.showInventory();

        if (products.size() == 0) {
            productService.nameVerification(product.getName());
            productService.standardizationCanned(product);
            productService.addProduct(product);

        } else if (products.size() != 0) {

            duplicate = productService.chekingForDuplicate(product.getName(), products);

            if (duplicate == true) {
                id = productService.findByName(product.getName());
                Product existingProduct = productService.findById(id);
                newQuantity = productService.addQuantity(product.getQuantity(), existingProduct.getQuantity());
                productService.updateProductQuantity(newQuantity, id);
            } else {
                productService.nameVerification(product.getName());
                productService.standardizationCanned(product);
                productService.addProduct(product);
            }
        }
        return "add";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public String formToDeleteProduct() {
        return "delete";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String deleteProduct(String name) {

        Long id = productService.findByName(name);

        if (id == null) {
            System.out.println("Couldn't find product to delete.");

        } else {
            productService.deleteProductById(id);
        }
        return "delete";
    }

}

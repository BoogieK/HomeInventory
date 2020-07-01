package inventory.web;

import java.util.List;

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
	
	
	@RequestMapping(method=RequestMethod.GET, value="/inventory")
	public String showInventory(Model model) {
		
		products = productService.showInventory();
		model.addAttribute("products", products);
		return "inventory";
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/add")
	public String formForAddingProduct(){
		return "add";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/add")
	public String addProduct(Product product, Type type) {
		
		boolean duplicate = false;
		Long id;
		int newQuantity=0;
		
		products = productService.showInventory();
		

		if(products.size() == 0)
		{
			productService.nameVerification(product.getName());
			productService.standardizationCanned(product);
			productService.addProduct(product);
			
		}else if(products.size() !=0)
		{
			
			duplicate = productService.chekingForDuplicate(product.getName(), products);
			
			if(duplicate == true)
			{
				id = productService.findByName(product.getName());
				Product existingProduct = productService.findById(id);
				newQuantity = productService.addQuantity(product.getQuantity(),existingProduct.getQuantity());
				productService.updateProductQuantity(newQuantity, id);
			}else
			{
			productService.nameVerification(product.getName());
			productService.standardizationCanned(product);
			productService.addProduct(product);
			}
		}
		return "add";
		
	}

}

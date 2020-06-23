package inventory.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import inventory.Product;
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

}

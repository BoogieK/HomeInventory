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

}

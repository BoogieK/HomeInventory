package inventory.data;

import org.springframework.data.repository.CrudRepository;

import inventory.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}

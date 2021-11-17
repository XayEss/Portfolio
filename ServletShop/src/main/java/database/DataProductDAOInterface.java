package database;
import java.util.List;


import web.model.Product;

public interface DataProductDAOInterface {
	
	public List<Product> getProducts();
	public Product getProductById(int id);
	public Product getProductById(String id);
	public List<Product> getProductsByCategory(String categoryName);
		
}

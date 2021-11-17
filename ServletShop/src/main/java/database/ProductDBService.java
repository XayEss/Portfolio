package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import web.model.Product;

public class ProductDBService implements DataProductDAOInterface{
	private DbConnector connector;
	public static final Logger LOGGER = Logger.getLogger(ProductDBService.class.getName());
	public static final String SELECT_PRODUCTS = "SELECT p.id, p.name, description, price FROM products p ";
	public static final String SELECT_PRODUCTS_BY_ID = SELECT_PRODUCTS + "WHERE id = ?";
	public static final String SELECT_PRODUCTS_BY = SELECT_PRODUCTS + "JOIN producttocategory pc on p.id = pc.product_id JOIN categories c on pc.category_id = c.id WHERE c.name = ? ";

	public ProductDBService(DbConnector connector) {
		this.connector = connector;
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		Connection conn = connector.getConnection();
		LOGGER.info("Starting query");
		try (PreparedStatement ps = conn.prepareStatement(SELECT_PRODUCTS); ResultSet rs = ps.executeQuery() ) {
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				products.add(product);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Db Error: " + e.getMessage(), e);
			try {
				conn.close();
			} catch (Exception e1) {
				
			}
		}
		LOGGER.info("Query success");
		return products;
	}

	public Product getProductById(int id) {
		Product product = new Product();
		Connection conn = connector.getConnection();
		LOGGER.info("Starting query");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_PRODUCTS_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Db Error: " + e.getMessage(), e);
			
		}finally {
			try {
				conn.close();
			} catch (Exception e1) {
				
			}
			try {
				ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("Query success");
		return product;

	}
	public Product getProductById(String id) {
		return getProductById(Integer.parseInt(id));
	}

	public List<Product> getProductsByCategory(String categoryName) {
		List<Product> products = new ArrayList<>();
		Connection conn = connector.getConnection();
		LOGGER.info("Starting query");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (conn.prepareStatement(SELECT_PRODUCTS_BY));
			ps.setString(1, categoryName);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				products.add(product);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Db Error: " + e.getMessage(), e);
			try {
				conn.close();
			} catch (Exception e1) {
				
			}
		}
		LOGGER.info("Query success");
		return products;
	}
}

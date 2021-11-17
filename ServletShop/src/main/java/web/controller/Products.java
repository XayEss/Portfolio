package web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.Product;

import java.io.IOException;
import java.util.List;

import database.DAOFactory;
import database.DataProductDAOInterface;
import database.DbConnector;
import database.ProductDBService;

/**
 * Servlet implementation class Products
 */
public class Products extends HttpServlet implements Servlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/products.jsp");
		String category = req.getParameter("category");
		DataProductDAOInterface productService = DAOFactory.createProductImpl(0);
		List<Product> productList = null;
		if (category == null || category.isEmpty()) {
			productList = productService.getProducts();
		}else {
			productList = productService.getProductsByCategory(category);
		}
		req.setAttribute("productList", productList);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

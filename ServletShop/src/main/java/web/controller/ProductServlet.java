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

import java.io.IOException;
import java.util.List;

import database.DbConnector;
import database.ProductDBService;

import web.model.Product;
/**
 * Servlet implementation class Product
 */
public class ProductServlet extends HttpServlet implements Servlet {
       
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/product.jsp");
    	int id = Integer.parseInt(req.getParameter("id"));
    	ProductDBService productService = new ProductDBService(DbConnector.getInstance());
    	Product product = productService.getProductById(id);
    	req.setAttribute("product", product);
    	dispatcher.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }

}

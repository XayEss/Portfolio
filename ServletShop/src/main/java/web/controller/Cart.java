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
import jakarta.servlet.http.HttpSession;
import web.model.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import database.DAOFactory;
import database.DataProductDAOInterface;
import database.DbConnector;
import database.ProductDBService;

/**
 * Servlet implementation class Cart
 */
public class Cart extends HttpServlet implements Servlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		RequestDispatcher dispatcher;
		Object productCart = session.getAttribute("cart");
		Map<Product, Integer> cart = (Map<Product, Integer>) productCart;
		dispatcher = req.getRequestDispatcher("WEB-INF/views/cart.jsp");
		if (cart == null) {
			cart = new HashMap<>();
		}
		req.setAttribute("productMap", cart);
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		DataProductDAOInterface dbService = DAOFactory.createProductImpl(0);
		String id = req.getParameter("id");
		Product product;
		String amountStr = req.getParameter("amount");
		int amount = Integer.parseInt(amountStr);
		String change = req.getParameter("change");
		Object productCart = session.getAttribute("cart");
		Map<Product, Integer> cart = (Map<Product, Integer>) productCart;
		PrintWriter pw = resp.getWriter();
		if (id != null && !id.isEmpty()) {
			product = dbService.getProductById(id);
			if (cart == null) {
				cart = new HashMap<>();
			}
			if (cart.containsKey(product)) {
				cart.put(product, cart.get(product) + amount);
			} else {
				cart.put(product, amount);
			}
			if (cart.get(product) == 0) {
				cart.remove(product);
			}
			session.setAttribute("cart", cart);
			session.setAttribute("cartSize", count(cart));
			pw.write(String.valueOf(count(cart)));
		} else {
			if (change != null) {
				product = dbService.getProductById(change);
				if (amount != 0) {
					cart.put(product, amount);
				} else {
					cart.remove(product);
				}
			}
			session.setAttribute("cartSize", count(cart));
			session.setAttribute("productMap", cart);
			pw.write(String.valueOf(count(cart)));
		}
	}

	private int count(Map<Product, Integer> cart) {
		int sum = 0;
		for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
			sum += entry.getValue();
		}
		return sum;
	}

}

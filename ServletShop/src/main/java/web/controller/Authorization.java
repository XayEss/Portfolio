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

import java.io.IOException;
import java.io.PrintWriter;

import database.DAOFactory;
import database.DAOSelection;
import database.DataBaseDAOInterface;


/**
 * Servlet implementation class Authorization
 */
public class Authorization extends HttpServlet implements Servlet {
       
	private boolean showForm = true;
	private static final String AUTHORIZED = "authorized";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/authorization.jsp");
		if (req.getParameter("logout") != null) {
			session.setAttribute(AUTHORIZED, null);
			showForm = true;
		}
		req.setAttribute("showForm", showForm);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginFromSession = (String) session.getAttribute("authorized");
		if (loginFromSession != null) {
			showForm = false;
		} else {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			DAOFactory df = new DAOFactory();
			DataBaseDAOInterface de = df.createObject(DAOSelection.MYSQL);
			String userName = de.checkLogin(login, password);
			if (userName != null) {
				showForm = false;
				session.setAttribute(AUTHORIZED, userName);
				loginFromSession = userName;
				//out.write("<center><font color='green'>Access granted</font>");
			}
		}
		doGet(request, response);
	}

}

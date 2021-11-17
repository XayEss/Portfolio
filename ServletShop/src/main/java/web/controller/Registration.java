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
import web.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.DAOFactory;
import database.DAOSelection;
import database.DataBaseDAOInterface;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet implements Servlet {
	private String errorText;
	private boolean showForm = true;

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/views/registration.jsp");
		req.setAttribute("showForm", showForm);
		req.setAttribute("errorText", errorText);
		disp.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isError = false;
		Pattern pattern;
		Matcher matcher;
		PrintWriter out = response.getWriter();
		errorText = "<ul style='float:right'>";
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordRepeat");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String region = request.getParameter("region");
		String comment = request.getParameter("comment");
		String browser = request.getParameter("browser");
		User user = new User(login, passwordRepeat, password, name, gender, region, comment);
		// out.write("login = " + login + ", password = " + password + ", passwordRepeat
		// = " + passwordRepeat +
		// ", gender = " + gender + ", region = " + region + ", comment = " + comment +
		// ", browser = " + browser);
		if (login != null && !login.isEmpty()) {
			pattern = Pattern.compile("^(.+)@(.+)$");
			matcher = pattern.matcher(login);
			if (!matcher.matches()) {
				isError = true;
				errorText += "<li>Login doesn't match regex </li>";
			}
		} else {
			isError = true;
			errorText += "<li>Login is empty!</li>";
		}
		if (password != null && !password.isEmpty()) {
			Pattern pattern2 = Pattern.compile("[0-9]");
			Matcher matcher2 = pattern2.matcher(password);
			Pattern pattern3 = Pattern.compile("[A-Z]");
			Matcher matcher3 = pattern3.matcher(password);
			if (!Objects.equals(password, passwordRepeat)) {
				isError = true;
				errorText += "<li>Passwords don't match!</li>";
			}
			if ((password.length() <= 8)) {
				isError = true;
				errorText += "<li>Password lenght must be more than 8!</li>";
			}
			if (!matcher2.find()) {
				isError = true;
				errorText += "<li>Password must contain a number!</li>";
			}
			if (!matcher3.find()) {
				isError = true;
				errorText += "<li>Password must contain a capital letter</li>";
			}
		} else {
			isError = true;
			errorText += "<li>Password is empty!</li>";
		}
		if (name == null || name.isEmpty()) {
			isError = true;
			errorText += "<li>Name field is empty</li>";
		}
		if (gender == null || gender.isEmpty()) {
			isError = true;
			errorText += "<li>Gender field is empty</li>";
		}
		if (region == null || region.isEmpty()) {
			isError = true;
			errorText += "<li>Region field is empty</li>";
		}
		if (comment == null || comment.isEmpty()) {
			isError = true;
			errorText += "<li>Comment is empty</li>";
		}
		if (browser == null || browser.isEmpty()) {
			isError = true;
			errorText += "<li>Browser is empty</li>";
		}

		errorText += "</ul>";
		if (!isError) {
			DAOFactory df = new DAOFactory();
			DataBaseDAOInterface de = df.createObject(DAOSelection.MYSQL);
			de.addUser(name, login, password, gender, region, comment);
			//out.write("Registration succeeded.");
			showForm = false;
			errorText = null;
		} else {
			request.setAttribute("user", user);
		}
		doGet(request, response);
	}

}

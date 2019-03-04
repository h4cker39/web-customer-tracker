package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testDBServlet
 */
@WebServlet("/testDBServlet")
public class testDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jdbc = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	     String user ="springstudent";
	     String pass = "springstudent";
	     String driver = "com.mysql.jdbc.Driver";
	     
	     try {
	    	 PrintWriter out =  response.getWriter();
	    	 out.println("Connecting to database" + jdbc);
	    	 Class.forName(driver);
	    	 Connection myConn =  DriverManager.getConnection(jdbc, user, pass);
	    	 out.println("Sucess");
	    	 myConn.close();
	     }catch(Exception e) {
	    	 e.printStackTrace();
	    	 throw new ServletException(e);
	     }
		
		
	}
}

package com.mavenwebapp.servlets;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mavenwebapp.dao.ApplicationDAO;




@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				 
	        String code = (String) request.getParameter("code");
	 
	        String errorString = null;
	 
	        try {
	        	ApplicationDAO dao = new ApplicationDAO();
	        	dao.deleteProduct(code);
	        } catch (Exception e) {
	            e.printStackTrace();
	            errorString = e.getMessage();
	        } 
	         
	        // If has an error, redirect to the error page.
	        if (errorString != null) {
	            // Store the information in the request attribute, before forward to views.
	            request.setAttribute("errorString", errorString);
	             
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/deleteProductErrorView.jsp");
	            dispatcher.forward(request, response);
	        }
	        // If everything nice.
	        // Redirect to the product listing page.        
	        else {
	            response.sendRedirect(request.getContextPath() + "/productList");
	        }
	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

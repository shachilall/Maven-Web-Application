package com.mavenwebapp.dao;

import java.sql.SQLException;

import com.mavenwebapp.beans.Product;
import com.mavenwebapp.beans.UserAccount;
import com.mavenwebapp.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
	
	private Connection conn = null;
	
	 public UserAccount findUser(
	            String userName, String password) throws SQLException,ClassNotFoundException {
		 
		 UserAccount user = null;
		 
		 openConnection();
	 
	        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " //
	                + " where a.User_Name = ? and a.password= ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, userName);
	        pstm.setString(2, password);
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            String gender = rs.getString("Gender");
	            user = new UserAccount();
	            user.setUserName(userName);
	            user.setPassword(password);
	            user.setGender(gender); 
	        }
	        closeConnection();
	        return user;
	    }
	 
	    public UserAccount findUser(String userName) throws SQLException, ClassNotFoundException {
	 
	    	openConnection();
	    	
	    	UserAccount user = null;
	    	
	        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "//
	                + " where a.User_Name = ? ";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, userName);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            String password = rs.getString("Password");
	            String gender = rs.getString("Gender");
	           user = new UserAccount();
	            user.setUserName(userName);
	            user.setPassword(password);
	            user.setGender(gender);
	            
	        }
	        closeConnection();
	        
	        return user;
	    }
	 
	    public List<Product> queryProduct() throws SQLException, ClassNotFoundException {
	    	openConnection();
	        String sql = "Select a.Code, a.Name, a.Price from Product a ";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        ResultSet rs = pstm.executeQuery();
	        List<Product> list = new ArrayList<Product>();
	        while (rs.next()) {
	            String code = rs.getString("Code");
	            String name = rs.getString("Name");
	            float price = rs.getFloat("Price");
	            Product product = new Product();
	            product.setCode(code);
	            product.setName(name);
	            product.setPrice(price);
	            list.add(product);
	        }
	        closeConnection();
	        return list;
	    }
	 
	    public Product findProduct(String code) throws SQLException, ClassNotFoundException {
	    	
	    	openConnection();
	    	
	    	Product product = null;
	    	
	        String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, code);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        while (rs.next()) {
	            String name = rs.getString("Name");
	            float price = rs.getFloat("Price");
	            product = new Product(code, name, price);
	            
	        }
	        
	        closeConnection();
	        
	        return product;
	    }
	 
	    public void updateProduct(Product product) throws SQLException, ClassNotFoundException {
	    	
	    	openConnection();
	    	
	        String sql = "Update Product set Name =?, Price=? where Code=? ";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, product.getName());
	        pstm.setFloat(2, product.getPrice());
	        pstm.setString(3, product.getCode());
	        pstm.executeUpdate();
	        
	        closeConnection();
	    }
	 
	    public void insertProduct( Product product) throws SQLException, ClassNotFoundException {
	    	
	    	openConnection();
	    	
	        String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, product.getCode());
	        pstm.setString(2, product.getName());
	        pstm.setFloat(3, product.getPrice());
	 
	        pstm.executeUpdate();
	        
	        closeConnection();
	    }
	 
	    public void deleteProduct(String code) throws SQLException, ClassNotFoundException {
	    	
	    	openConnection();
	    	
	        String sql = "Delete From Product where Code= ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, code);
	 
	        pstm.executeUpdate();
	        
	        closeConnection();
	    }
	    
	    private void openConnection() throws ClassNotFoundException, SQLException{
	    	 if(conn == null || conn.isClosed()){
				 conn = ConnectionUtils.getConnection();
			 }
	    }
	    
	    private void closeConnection() throws SQLException{
	    	if(conn !=null && !conn.isClosed()){
	        	conn.close();
	        }
	    }


}

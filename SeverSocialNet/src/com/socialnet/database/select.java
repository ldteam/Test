package com.socialnet.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class select
 */
@WebServlet("/select")
public class select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public select() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		 
		String request_database =request.getHeader("request_database");
				//"select first_name, last_name, d_rating  from  anketa, rating where anketa.user_id = rating.user_id and anketa.user_id = ?" ;//request.getHeader("request_database");
		//String id=request.getHeader("id");


		JSONArray jArray = new JSONArray();
         int i=0;
		   
		PrintWriter out=response.getWriter();
        ResultSet result = null;
        //out.print("0");
	    InitialContext jndiContext;
		
	    try {
			jndiContext = new InitialContext();
			DataSource source = (DataSource) jndiContext.lookup("jdbc/_MySql");
		
			Connection con= source.getConnection();
			 PreparedStatement preparedStatement = con.prepareStatement(request_database);
			  

		//	 preparedStatement.setString(1,id);
				
			
			 result = preparedStatement.executeQuery();
			 ResultSetMetaData metaData = result.getMetaData();
	         int count = metaData.getColumnCount();
		
	         jndiContext = new InitialContext();
	
			
		        		          
		           if (result != null) 
		            {
		               result.beforeFirst();
		              while (result.next()) 
		              {		            	  
		  		                 try {	                   
		  		                      JSONObject arrayObj = new JSONObject();		              		                	   
		  		                 for (int n=1; n<=count; n++)
		  		    		     {
		  		                	
										arrayObj.put(metaData.getColumnName(n),result.getString(metaData.getColumnName(n)));  		    	    	
		  		    		     }		                	    		                	    						                    		
								 jArray.put(i, arrayObj);
									} catch (JSONException e) {
											e.printStackTrace();
									}
		  		       		        i++;	
		              }
		          	out.print(jArray);
		            }	
	} catch (NamingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
}

package com.socialnet.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class update
 */
@WebServlet("/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public update() {
        super();
 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		/* response.setContentType("text/html; charset=UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 
		 JSONObject Obj = new JSONObject();
			
		 try {
				Obj.put("name", "hello");
				Obj.put("surname", "my09809surname");
				Obj.put("adress_folder", "mysurname");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	
		 
		 //тело запроса, например - call insert_user (?, ?)
		 String	 request_database ="update anketa set name= ? , surname= ? , adress_folder= ?  WHERE id_anketa = ? ";
				 //request.getHeader("request_database");
		 String id= "116";
				 //request.getHeader("id");
		 
		 String request_columns ="name-surname-adress_folder";
				 //request.getHeader("request_columns");	 
			
			String [] columns = request_columns.split("-");
			
			 PrintWriter out=response.getWriter();
				out.print("response server: method update");

					 StringBuilder sb = new StringBuilder();
					 BufferedReader br = request.getReader();
					 String str;
					    
			  
					/* while( (str = br.readLine()) != null ){
					    	
					        sb.append(str);
					    
					    
					
					     try {
					    	
					    	
					   
							
							boolean is=UpdateInDatabase(request_database, id, Obj, columns);
							
					if (is==true)
					{
						out.print(" Update success");
					}
					else
					{
					 out.print("update error");
					}
					            
									 
				
				
		
		*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{


		 response.setContentType("text/html; charset=UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 
	
		 
		 //тело запроса, например - call insert_user (?, ?)
		 String	 request_database = request.getHeader("request_database");
		 String id=request.getHeader("id");
		 
		 String request_columns = request.getHeader("request_columns");	 
			
			String [] columns = request_columns.split("-");
		 
		 PrintWriter out=response.getWriter();
		out.print("response server: method update");

			 StringBuilder sb = new StringBuilder();
			 BufferedReader br = request.getReader();
			 String str;
			    
	  
			 while( (str = br.readLine()) != null ){
			    	
			        sb.append(str);
			    
			    
			
			     try {
			    	    JSONObject jObj = new JSONObject(str);
			    	    
			    
			    	    //проходим по ключам
			            Iterator<?> keys = jObj.keys();

			            while( keys.hasNext() ){
			                String key = (String)keys.next();
			                if( jObj.get(key) instanceof JSONObject ){
			                
			                }
			            	out.print(key);
			            }
			    	    
			    	 
			   
					
					boolean is=UpdateInDatabase(request_database, id, jObj, columns);
					
			if (is==true)
			{
				out.print(" Update success");
			}
			else
			{
			 out.print("update error");
			}
			            
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.print("error");
				}
			    }
		 
		
		
		
	}
	
	
	private boolean UpdateInDatabase(String request_database,  String id, JSONObject jObj, String [] columns)
	{
	    int i=1;
		   PreparedStatement preparedStatement = null;
		InitialContext jndiContext;
		try {
			jndiContext = new InitialContext();
			DataSource source = (DataSource) jndiContext.lookup("jdbc/_MySql");
			try {
				Connection con= source.getConnection();
				
				// String insertTableSQL = "INSERT INTO users"
					//		+ "(name, surname) VALUES"
						//	+ "(?,?)";
				 
				 preparedStatement = con.prepareStatement(request_database);
				  
				
				// preparedStatement.setString(1, id);
				// preparedStatement.setString(2, id);
				
			
				
				 
					
				 Iterator<?> keys = jObj.keys();
				 
				 while( keys.hasNext() ){
	            		String key = (String)keys.next();
	            		 for (int k=0; k<columns.length; k++)
	 					{
	            			 
	            			 if (key.equals(columns[k]))
	            			 {
	            				try {
									preparedStatement.setString(i, jObj.getString(key));	
									i++;
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		            		
	            			 }
	 					} 
				
				 					 
				 
				}
			 

						
						
						  
						  //вставляем id
						 preparedStatement.setString(i, id);
			   
						
						 
				
					
				
					// execute insert SQL stetement
			preparedStatement.executeUpdate();
				  
				  
			}
			
			 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 
				return false;
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		return true;
		
	}

}

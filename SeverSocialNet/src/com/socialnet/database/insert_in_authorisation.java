package com.socialnet.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
 * Servlet implementation class insert_in_authorisation
 */
@WebServlet("/insert_in_authorisation")
public class insert_in_authorisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public insert_in_authorisation() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{
		
		/*response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			//response.setContentType("application/json");
			
			JSONObject Obj = new JSONObject();
			String request_database ="CALL insert_in_authorisation(?, ?, ?); ";
			String request_columns = "login-password";
			try {
				 
					
		     	
		      	
				Obj.put("login", "_login");
				
				Obj.put("password", "_password");
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String [] columns = request_columns.split("-");
			
			
			
			
		
			
		
			 
			 
			
		
			 
			 
			
	        		
			
		    // out.print("response server: method insert");

		
			
			     
			    //	    JSONObject jObj = Obj;
			    	    //new JSONObject(str);
			    	

					
					
					int is=InsertIntoDatabase(request_database,  Obj, columns);
					
					out.print("return ID "+is);

					
					
					
			if (is>0)
			{
				out.print("Insert success");
			}
			else
			{
			 out.print("Insert error");
			} 
			*/
			    
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{
		
		response.setContentType("text/html; charset=UTF-8");
		
		 request.setCharacterEncoding("UTF-8");
		 
		 String request_database ="";
		 
		 
		 
		 request_database = request.getHeader("request_database");
		 
		String request_columns = request.getHeader("request_columns");	 
		
		String [] columns = request_columns.split("-");
		
	
		
	PrintWriter out=response.getWriter();
		
		
		
	
		//out.print("response server: method insert");

		 StringBuilder sb = new StringBuilder();
		    BufferedReader br = request.getReader();
		    String str;
		    
		    //
   while( (str = br.readLine()) != null ){
		    	
		        sb.append(str);
		    
		    
		
		     try {
		    	    JSONObject jObj = new JSONObject(str);
		    	

				
				
				int is=InsertIntoDatabase(request_database,  jObj, columns);
				
			out.print(is);
		      
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			//	out.print("error");
			}
		    }
		
		
	}
	
	@SuppressWarnings("unused")
	private int InsertIntoDatabase(String request_database,  JSONObject jObj, String [] columns)
	{
		int id=0;
		int i=1;
		   PreparedStatement preparedStatement = null;
		InitialContext jndiContext;
		try {
			jndiContext = new InitialContext();
			DataSource source = (DataSource) jndiContext.lookup("jdbc/_MySql");
			
			try {
				Connection con= source.getConnection();
				
				
				 
				
				String insertTableSQL=request_database;
				
			
					
					
					CallableStatement cs = con.prepareCall(insertTableSQL);
					
                     Iterator<?> keys = jObj.keys();
						 
					 while( keys.hasNext() )
					 {
		            		String key = (String)keys.next();
		            		 for (int k=0; k<columns.length; k++)
		 					{
		            			 
		            			 if (key.equals(columns[k]))
		            			 {
		            			
		            					try 
		            					{
											cs.setString(i, jObj.getString(key));
											i++;
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
		            			 }
		 					}
					 }
					
		            		  cs.registerOutParameter(i, Types.VARCHAR);
					
				
				
				    cs.executeQuery();

				   
	
				 
								
				
				    id = cs.getInt(i);

				  
				 
					 }
			
			 catch (SQLException e) {
					e.printStackTrace();
				 
				}
			} catch (NamingException e) {
				e.printStackTrace();
			}
		

		
		return id;
	}
	
}

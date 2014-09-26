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
 * Servlet implementation class insert
 */
@WebServlet("/insert")
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert() {
        super();
       
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		//response.setContentType("application/json");
		
		JSONObject Obj = new JSONObject();
		String request_database ="insert into registr_gsm_id (user_id, reg_id) values ( ? ,  ? );";
		
		
				//"insert into anketa (user_id, name, surname, mobile_phone, id_region, id_city) values (?, ?, ?, ?, ?, ?)";
		String request_columns = "user_id-reg_id";
		try {
			 
				
	     	
	      	
			Obj.put("user_id", "120");
		
			
			Obj.put( "reg_id", "APA91bE68AqsClzzNUfShS80Z_7bKZBOT-IHN3Upcfni7A3fAtUBvFAmcFvveXVXET9cBBjFOH4qjOJWjAeKaeZ7Ns3dlxhn9hp9V9nBihDDE863JuiTMtXlVklEqipMJzJb2uNYSI4Exflq347C0FLU3Odlx38hn_SzA7qpauf1wKTOB7Iu2ao");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			out.print(e.toString());
		}
		
		String [] columns = request_columns.split("-");
		
		
		
		
	
		
	
		 
		 
		
	
		 
		 
		
        		
		
	     out.print("response server: method insert");

	
		
		     
		    //	    JSONObject jObj = Obj;
		    	    //new JSONObject(str);
		    	

				
				
				boolean is=InsertIntoDatabase(request_database,  Obj, columns);
				
				for (int i=0; i<columns.length; i++)
				{
				out.print("Columns "+i+" "+columns.length);
				}
				
		if (is==true)
		{
			out.print("Insert success");
		}
		else
		{
		 out.print("Insert error");
		}
		            
			

		 
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html; charset=UTF-8");
		
		 request.setCharacterEncoding("UTF-8");
		 
		 String request_database ="";
		 
		 
		 
		 request_database = request.getHeader("request_database");
		 
		String request_columns = request.getHeader("request_columns");	 
		
		String [] columns = request_columns.split("-");
		
	
		
	PrintWriter out=response.getWriter();
		
		
		
	
		out.print("response server: method insert");

		 StringBuilder sb = new StringBuilder();
		    BufferedReader br = request.getReader();
		    String str;
		    
		    //
    while( (str = br.readLine()) != null ){
		    	
		        sb.append(str);
		    
		    
		
		     try {
		    	    JSONObject jObj = new JSONObject(str);
		    	

				
				
				boolean is=InsertIntoDatabase(request_database,  jObj, columns);
		if (is==true)
		{
			out.print("Insert success");
		}
		else
		{
		 out.print("Insert error");
		}
		            
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("error");
			}
		    }
	}
	
	@SuppressWarnings("unused")
	private boolean InsertIntoDatabase(String request_database,  JSONObject jObj, String [] columns)
	{
		
		int i=1;
		   PreparedStatement preparedStatement = null;
		InitialContext jndiContext;
		try {
			jndiContext = new InitialContext();
			DataSource source = (DataSource) jndiContext.lookup("jdbc/_MySql");
			
			try {
				Connection con= source.getConnection();
				
				
				 
				
				String insertTableSQL=request_database;
				
				 preparedStatement = con.prepareStatement(insertTableSQL);
				 
				 
					for (int k=0; k<columns.length; k++)
					{
						 Iterator<?> keys = jObj.keys();
						 
						 while( keys.hasNext() ){
				        		String key = (String)keys.next();
				        		
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
					
				
		/*			 Iterator<?> keys = jObj.keys();
					 
					 
					 
					 while( keys.hasNext() ){
		            		String key = (String)keys.next();
		            		 for (int k=0; k<columns.length; k++)
		 					{
		            			 
		            			 if (key.equals(columns[k]))
		            			 {
		            			
			            			
		            			 }
		 					}
					
					 					 
					 
					}*/
				 
								
				
					// execute insert SQL stetement
					preparedStatement .executeUpdate();
				  
				  
				  return true;
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
		
	}

}

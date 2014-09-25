package com.socialnet.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
 * Servlet implementation class delete
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public delete() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		 
		 
		 //тело запроса, например - delete from database (?, ?)
		 String request_database = request.getHeader("request_database");
		 
		//id, по которое нужно удалить
		 String id=request.getHeader("id");
		 
			PrintWriter out=response.getWriter();
			
			
			
			
			out.print("response server: method delete");
			
			boolean is=DeleteFromDatabase(request_database, id);
			if (is==true)
			{
				out.print("Delete success");
			}
			else
			{
			 out.print("Delete error");
			}
			
			
	}
	
	private boolean DeleteFromDatabase()
	{
		return true;
	}
	
	private boolean DeleteFromDatabase(String request_database,  String id)
	{
		
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
				 
				//тело запроса
				 preparedStatement = con.prepareStatement(request_database);
	
				 //добавляем id, которое нужно удалить
				  preparedStatement.setString(1, id);
				    
				
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

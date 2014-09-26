package com.socialnet.picture;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sent_picture_to_client
 */
@WebServlet("/Sent_picture_to_client")
public class Sent_picture_to_client extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sent_picture_to_client() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		//response.setContentType("application/json");
				out.print("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html; charset=UTF-8");
		 String id=req.getHeader("id");
		String filePath = System.getProperty("user.home").replace("\\", "/") + "/Desktop/"+id+"/ic_launcher_server.jpeg";
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		// if you want to use a relative path to context root:
		String relativePath = getServletContext().getRealPath("");
		System.out.println("relativePath = " + relativePath);
		
		// obtains ServletContext
		ServletContext context = getServletContext();
		
		// gets MIME type of the file
		String mimeType = context.getMimeType(filePath);
		if (mimeType == null) {			
			// set to binary type if MIME mapping not found
			//mimeType = "application/octet-stream";
			mimeType = "multipart/form-data";
		}
		System.out.println("MIME type: " + mimeType);
		
		// modifies response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		
		// forces download
	//	String headerKey = "Content-Disposition";
		//String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
	//	response.setHeader(headerKey, headerValue);
		
		// obtains response's output stream
		OutputStream outStream = response.getOutputStream();
		
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		
		inStream.close();
		outStream.close();	
	}

}

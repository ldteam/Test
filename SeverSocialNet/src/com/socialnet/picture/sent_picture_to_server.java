package com.socialnet.picture;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class sent_picture_to_server
 */
@WebServlet("/sent_picture_to_server")
public class sent_picture_to_server extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Random random = new Random();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sent_picture_to_server() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		  String path = getServletContext().getRealPath("/upload/"+random.nextInt());
		    out.print(path);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
		
		

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
	
		if (!isMultipart) {
		
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
 

		DiskFileItemFactory factory = new DiskFileItemFactory();

		factory.setSizeThreshold(1024*1024);
		
		

		File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(tempDir);
 

		ServletFileUpload upload = new ServletFileUpload(factory);
		

		upload.setSizeMax(1024 * 1024 * 10);
 
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			
			while (iter.hasNext()) {
			    FileItem item = (FileItem) iter.next();
 
			    if (item.isFormField()) {
			    	
			        processFormField(item);
			        String path = getServletContext().getRealPath("/upload/"+random.nextInt() + item.getName());
			        
			        
			        
			        out.print(path);
			    } else {
			    
			        processUploadedFile(item);
			        
     String path = getServletContext().getRealPath("/upload/"+random.nextInt() + item.getName());
			        
			        out.print(path);
			    }
			}			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}	
	}
	
	private void processUploadedFile(FileItem item) throws Exception {
		File uploadetFile = null;
	
		do{
			String path = getServletContext().getRealPath("/upload/"+random.nextInt() + item.getName());
			
			uploadetFile = new File(path);		
		}while(uploadetFile.exists());
		
	
		uploadetFile.createNewFile();
		
		item.write(uploadetFile);
	}
 


	private void processFormField(FileItem item) {
		System.out.println(item.getFieldName()+"="+item.getString());		
	}

}

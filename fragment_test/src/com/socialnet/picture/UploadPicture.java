package com.socialnet.picture;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.socialnet.httpconnect.HttpClient;
import com.socialnet.url.Url_path_to_server;

public class UploadPicture {
	
	
	String RealPath="";
	
	public UploadPicture(String RealPath)
	{
		this.RealPath=RealPath;
	}
	
	public Bitmap loadBitmap()
	{


		 Bitmap bitmap = BitmapFactory.decodeFile(RealPath);
	
	    return bitmap;
	}
	
	//�������� �������� �� ������
	public String UploadPicture_from_Server()
	{
		  Bitmap b=loadBitmap();
		  
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//�������
		b.compress(CompressFormat.PNG, 0, baos);
		
	try {
			  
			HttpClient client = new HttpClient(Url_path_to_server.UriSendPicture);
			client.connectForMultipart();
			client.addFormPart("param1", "drawable");
			client.addFormPart("param2", "logo.png");
			client.addFilePart("file", "logo.png", baos.toByteArray());
			client.finishMultipart();
	String data = client.getResponse();
	
	return data;
		}
		catch(Throwable t) {
			t.printStackTrace();
			Log.v("My", "UploadPicture UploadPicture() "+"error");
			
			return "error";
		}
		
	}

	
	
}

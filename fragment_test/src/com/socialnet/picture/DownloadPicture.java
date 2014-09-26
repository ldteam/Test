package com.socialnet.picture;

/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.socialnet.url.Url_path_to_server;

public class DownloadPicture {

	private MenuItem item;
	ImageView imgView;
	
	void DownloadPicture() {
						
	}
	
	public Bitmap con(String ID){
		SendHttpRequestTask t = new SendHttpRequestTask();
		String[] params = new String[] {Url_path_to_server.UriDowPicture, ID};
		t.execute(params);	
		Bitmap bit = null;
		try {
			bit = t.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bit;
	}


	private class SendHttpRequestTask extends AsyncTask<String, Void, Bitmap> {

		
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			String id = params[1];
			Bitmap data = downloadImage(url,id);

			return data;
		}
		
		
		public Bitmap downloadImage(String url, String id) {
			Bitmap b=null;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				DefaultHttpClient httpclient = new DefaultHttpClient();
	        	HttpPost httpPost = new HttpPost(url);
	        	httpPost.setHeader("Accept", "application/json");
	        	httpPost.setHeader("Content-type", "application/json");
	        	httpPost.setHeader("id", id);
	        	
	        	HttpResponse httpResponse = httpclient.execute(httpPost);
	        	InputStream inputStream = httpResponse.getEntity().getContent();
	        	
				Log.i("qwe", "-10");
				
				b = BitmapFactory.decodeStream(new FlushedInputStream(inputStream));
	
				//	con.disconnect();
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
			return b;
			//return baos.toByteArray();
		}
		
		
	/*
		@Override
		protected void onPostExecute(Bitmap result) {
			
			
			
				//byte[] b = new byte[1024];
				
				//while ( result.read(b) != -1)
				//baos.write(b);
			//imgView.setImageResource(R.drawable.aa);
			//imgView.setImageBitmap(result);			
			//item.setActionView(null);
			
		}*/
		
	}
	

	static class FlushedInputStream extends FilterInputStream {
        public FlushedInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public long skip(long n) throws IOException {
            long totalBytesSkipped = 0L;
            while (totalBytesSkipped < n) {
                long bytesSkipped = in.skip(n - totalBytesSkipped);
                if (bytesSkipped == 0L) {
                    int b = read();
                    if (b < 0) {
                        break;  // we reached EOF
                    } else {
                        bytesSkipped = 1; // we read one byte
                    }
                }
                totalBytesSkipped += bytesSkipped;
            }
            return totalBytesSkipped;
        }
    }
}

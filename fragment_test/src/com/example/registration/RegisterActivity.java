package com.example.registration;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import android.R.color;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.fragment_test.R;
import com.socialnet.get_real_path_from_file.Get_path;
import com.socialnet.url.Url_path_to_server;
//import com.socialnet.map_entry.ListEntry;
//import com.socialnet.map_entry.MyEntry;

@SuppressLint("NewApi") @TargetApi(Build.VERSION_CODES.KITKAT) public class RegisterActivity extends Activity implements OnClickListener, OnFocusChangeListener {
	
	ImageButton take_photo;
	Button register;
    Uri	fileUri;
    Drawable d;
    String GetAbsolutePath="";
    
    EditText surname;
    EditText name;
    EditText login;
    EditText password;
    EditText password2;
    
    String result = null;
    InputStream is = null;
    StringBuilder sb=null;
    
	
	

	

	
    

	
	
	private static final int OPEN_FILE = 300;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.register_form);
		 
		 
		 take_photo=(ImageButton)findViewById(R.id.imageButton1);
		 take_photo.setOnClickListener(this);
		 
		 register=(Button)findViewById(R.id.button1);
		 register.setOnClickListener(this);
		 
		 
		 surname=(EditText)findViewById(R.id.editText1);
		 name=(EditText)findViewById(R.id.editText2);
		 login=(EditText)findViewById(R.id.editText3);
		 password=(EditText)findViewById(R.id.editText4);
		 password2=(EditText)findViewById(R.id.editText5);
		 
		 
		 surname.setOnFocusChangeListener(this);
		 name.setOnFocusChangeListener(this);
		 login.setOnFocusChangeListener(this);
		 password.setOnFocusChangeListener(this);
		 password2.setOnFocusChangeListener(this);
		 
	}

	@Override
	public void onClick(View v) {
		  switch (v.getId()) {
		    case R.id.imageButton1:
		    	Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

		    	intent.setType("image/*");
		    	startActivityForResult(intent, OPEN_FILE);
		    	
		     
		      break;
		      
		    case R.id.button1:
		    	
		    	//если все заполнено правильно
		   	if (!TextUtils.isEmpty(login.getText().toString()) && android.util.Patterns.EMAIL_ADDRESS.matcher(login.getText().toString()).matches()&&surname.getText().toString().length()!=0&&name.getText().toString().length()!=0&&password.getText().toString().length()!=0&&password.getText().toString().equals(password2.getText().toString()))		    	{
		    		Log.v("My", "Alles works correctly");
		    		
		    		
		    		Connection con = new Connection(Url_path_to_server.UriSelectFromDatabase, "select *from authorisation where login ='"+login.getText().toString()+"';");
		    		try {
						con.connect();
						ContentValues[] val = con.returnvalues();
						
								//if login is not registriered
						if (val.length==0)
						{
							Intent intent1 = new Intent(this, RegisterActivitySecond.class);
					    
					   	  intent1.putExtra("GetAbsolutePath", GetAbsolutePath);
					    		 
					    
					   	  
					
				    		Log.v("My",  surname.getText().toString());
					  	intent1.putExtra("surname", surname.getText().toString());
				        intent1.putExtra("name", name.getText().toString());
				        intent1.putExtra("login", login.getText().toString());
				        intent1.putExtra("password", password.getText().toString());
				    		  
					    	 startActivity(intent1);
						} //end if
						else
						{
							Toast toast = Toast.makeText(this, "This user has already registered", Toast.LENGTH_LONG);
							toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
						}
						
					
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	
		    	
		    	
		    	}
		  
		    	else  
		    	{
		    		if ( ( TextUtils.isEmpty(login.getText().toString()) |!android.util.Patterns.EMAIL_ADDRESS.matcher(login.getText().toString()).matches()))
		    		{
		    			Toast.makeText(getApplicationContext(),  " e-mail is not correct", Toast.LENGTH_SHORT).show(); 
		    	
		    		Log.v("My", "e-mail is not correct");
		    		}
		    		
		    		if (surname.getText().toString().length()==0)
		    		{
		    			Log.v("My", "Surname is not correct");
		    			Toast.makeText(getApplicationContext(),  "Surname is not correct", Toast.LENGTH_SHORT).show(); 
		    			surname.setBackgroundColor(color.black);
		    		}
		    		
		    		if (name.getText().toString().length()==0)
		    		{
		    			Log.v("My", "Name is not correct");
		    			Toast.makeText(getApplicationContext(),  "Name is not correct", Toast.LENGTH_SHORT).show(); 
		    		}
		    		
		    		if (password.getText().toString().length()==0)
		    		{
		    			Toast.makeText(getApplicationContext(),  "Password is not correct", Toast.LENGTH_SHORT).show(); 
		    		}
		    		if (!password.getText().toString().equals(password2.getText().toString()))
		    		{
		    			password.setText("");
		    			password2.setText("");
		    			Toast.makeText(getApplicationContext(),  "No correct repeat password", Toast.LENGTH_SHORT).show(); 
		    		}
		    	} //end else

		    	
		    	
		    	
		    	
		    	
		    	
		   
		    	
		    //	Intent intent1 = new Intent(this, RegisterActivitySecond.class);
		        //startActivity(intent1);
		     
		    
		    /*	String[] params = new String[]{GetAbsolutePath};
		    		
		    	ListEntry list = new ListEntry();
		    	list.Add(new MyEntry<String, String>("name", name.getText().toString()));
		    	list.Add(new MyEntry<String, String>("surname", surname.getText().toString()));
	
			Connection con = new Connection(list, params );
		    	con.connect(); */
		    	break;
		  
			      
		    default:
		      break;
		    }
		
	}
	
	
	

	
	@SuppressWarnings("deprecation")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

			super.onActivityResult(requestCode, resultCode, data);
		

			if (resultCode == RESULT_OK) {
			    if(requestCode == OPEN_FILE){ 
			       
			    	
			    
			    	
			        Uri ChossefileUri = data.getData();
			        
			    	
			        if(ChossefileUri !=null){ 
			            fileUri = ChossefileUri;
			            if(fileUri != null) 
			            {   
			            	
			            	
			            	
			            	Log.v("My", "FileUri "+ Get_path.getPath(this, fileUri));
			            			
			            
			            	
			            	  File f = new File(Get_path.getPath(this, fileUri));  
			            	  GetAbsolutePath=f.getAbsolutePath();
			            	     d = Drawable.createFromPath(f.getAbsolutePath());
			            	  
			            	    take_photo.setBackgroundDrawable(d);  
			            	  
			            	
			            //	Log.v("log_tag", fileUri.toString());
			            
			            }
			             
			            }
			        }
			         
			     } 
			    
			 } 
			
	/*private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else { 
            cursor.moveToFirst(); 
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA); 
            return cursor.getString(idx); 
        }
    }*/
	
	

	//when edittext change focus
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		 if (!hasFocus)
		 {
		 switch (v.getId()) {


	
		 case R.id.editText1:
			if (surname.getText().equals(""))
				Toast.makeText(getApplicationContext(),  "Surname is empty", Toast.LENGTH_SHORT).show(); 
					Log.v("My", "Surname is empty");
			 
			 break;
			 
		 case R.id.editText2:
				if (name.getText().equals(""))
					Toast.makeText(getApplicationContext(),  "Name is empty", Toast.LENGTH_SHORT).show(); 
			 Log.v("My", "Name is empty");
		
			 break;
			 
		 case R.id.editText3:
			
			
				if (( TextUtils.isEmpty(login.getText().toString()) |!android.util.Patterns.EMAIL_ADDRESS.matcher(login.getText().toString()).matches()))
				{
					Toast.makeText(getApplicationContext(),  "Login is empty", Toast.LENGTH_SHORT).show(); 
				}
			 Log.v("My", "Login is empty");
			 break;
			 
		 case R.id.editText4:
			 if (password.getText().equals(""))
			 {
				 Toast.makeText(getApplicationContext(),  "Password is empty", Toast.LENGTH_SHORT).show(); 
			 }
		
			 break;
		 
		 case R.id.editText5:
			 if (password2.getText().equals(""))
			 {
				 Toast.makeText(getApplicationContext(),  "Password2 is empty", Toast.LENGTH_SHORT).show(); 
			 }
			 break;
		 }
			 
			 
			 
		 }
		
	
		
	}
	
	
	

}

package com.avilyne.gcm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

/**
 * Servlet implementation class GCMBroadcast
 */
@WebServlet("/GCMBroadcast")
public class GCMBroadcast extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// The SENDER_ID here is the "Browser Key" that was generated when I
	// created the API keys for my Google APIs project.
	private static final String SENDER_ID = "AIzaSyBuLDRdFPM0yW5VbUk3tL7IYl7MKiKpU7c";
	
	// APA91bE-OM8Gb8R63ulgQHv1bCA3txMADXlqJFk2dlF-Kv0CXmBenAHeBZp3jFIFIzo-_NAxrjNtl8yLm7tct5ITbeA8nDbGN_9PJBqajJs4UE9n9wNV9wRd-qBh03E1DLbn8apQb7I6WWteXIJYNGVj85M9VdoPZg

	
	// This is a *cheat*  It is a hard-coded registration ID from an Android device
	// that registered itself with GCM using the same project id shown above.
	private static final String ANDROID_DEVICE = "APA91bGXoCf1oEwjqnAPxTJpt5VRBCaesfdy_w1RmkLBVHiY4N1RGP4pg5-kHUUr9VoXxWCfbw1c4mz5yhQVhb5PTq9Xm_LDGM4U-S1Ix4VAHopdq2g42Bz0atOat-W3jCXO1p-dmIBwRUdHtbCcU3XHjSHIqJPg8Q";
		
	//APA91bGXoCf1oEwjqnAPxTJpt5VRBCaesfdy_w1RmkLBVHiY4N1RGP4pg5-kHUUr9VoXxWCfbw1c4mz5yhQVhb5PTq9Xm_LDGM4U-S1Ix4VAHopdq2g42Bz0atOat-W3jCXO1p-dmIBwRUdHtbCcU3XHjSHIqJPg8Q

	
	
	// This array will hold all the registration ids used to broadcast a message.
	// for this demo, it will only have the ANDROID_DEVICE id that was captured 
	// when we ran the Android client app through Eclipse.
	private List<String> androidTargets = new ArrayList<String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GCMBroadcast() {
    	
        super();

        // we'll only add the hard-coded *cheat* target device registration id 
        // for this demo.
        androidTargets.add(ANDROID_DEVICE);
        
    }
    
    // This doPost() method is called from the form in our index.jsp file.
    // It will broadcast the passed "Message" value.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// We'll collect the "CollapseKey" and "Message" values from our JSP page
		String collapseKey = "";
		String userMessage = "";
		
		try {
			userMessage = request.getParameter("Message");
			collapseKey = request.getParameter("CollapseKey");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		// Instance of com.android.gcm.server.Sender, that does the
		// transmission of a Message to the Google Cloud Messaging service.
		Sender sender = new Sender(SENDER_ID);
		
		// This Message object will hold the data that is being transmitted
		// to the Android client devices.  For this demo, it is a simple text
		// string, but could certainly be a JSON object.
		Message message = new Message.Builder()
		
		// If multiple messages are sent using the same .collapseKey()
		// the android target device, if it was offline during earlier message
		// transmissions, will only receive the latest message for that key when
		// it goes back on-line.
		.collapseKey(collapseKey)
		.timeToLive(30)
		.delayWhileIdle(true)
		.addData("message", userMessage)
		.build();
		
		try {
			// use this for multicast messages.  The second parameter
			// of sender.send() will need to be an array of register ids.
			MulticastResult result = sender.send(message, androidTargets, 1);
			
			if (result.getResults() != null) {
				int canonicalRegId = result.getCanonicalIds();
				if (canonicalRegId != 0) {
					
				}
			} else {
				int error = result.getFailure();
				System.out.println("Broadcast failure: " + error);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We'll pass the CollapseKey and Message values back to index.jsp, only so
		// we can display it in our form again.
		request.setAttribute("CollapseKey", collapseKey);
		request.setAttribute("Message", userMessage);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
				
	}

}

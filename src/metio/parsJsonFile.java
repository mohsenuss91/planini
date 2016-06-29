package metio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.connectionclasse.getjsonfile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class parsJsonFile {
	getjsonfile g=null;
	rr l=new rr();
	public parsJsonFile(String s){
		g=new getjsonfile(s);
	}
rr  getvalues() throws ClientProtocolException, URISyntaxException, IOException, JSONException{
	l.list=new ArrayList<clas>(3);
	String s = g.prosdata();
	JSONObject jo =new JSONObject(s);
	JSONObject jo2 =jo.getJSONObject("data");//2
	JSONArray jo3=jo2.getJSONArray("request");//request 1
    l.city=jo3.getJSONObject(0).getString("query");
    
	JSONArray  jo4 =jo2.getJSONArray("weather");//////3
 	 
 for(int i=0;i<3;i++) {
    clas c=new clas();
   
    c.date= jo4.getJSONObject(i).getString("date");
    c.max= jo4.getJSONObject(i).getString("tempMaxC");
    c.min= jo4.getJSONObject(i).getString("tempMinC");
	
   c.weatherDesc=jo4.getJSONObject(i).getJSONArray("weatherDesc").getJSONObject(0).getString("value");
    c.b= getImageBitmap(jo4.getJSONObject(i).getJSONArray("weatherIconUrl").getJSONObject(0).getString("value"));
	l.list.add(c);
	
	}
	return  l;
}
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////
	private Bitmap getImageBitmap(String url) {
	    Bitmap bm = null;
	    try {
	        URL aURL = new URL(url);
	        URLConnection conn = aURL.openConnection();
	        conn.connect();
	        InputStream is = conn.getInputStream();
	        BufferedInputStream bis = new BufferedInputStream(is);
	        bm = BitmapFactory.decodeStream(bis);
	        bis.close();
	        is.close();
	   } catch (IOException e) {
	      
	   }
	   return bm;
	} 
}

package com.connectionclasse;


import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class getjsonfile {
	  String uri=null;

	  public  getjsonfile(String s){uri=s;}
	  
public	String  prosdata() throws URISyntaxException, ClientProtocolException, IOException{
		  HttpClient cl=new DefaultHttpClient();
		  HttpGet get =new HttpGet();
		  get.setURI(new URI(uri));
		  HttpResponse r =cl.execute(get);
		  
		 return  EntityUtils.toString(r.getEntity());
		
		  
	  }




}

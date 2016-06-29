package menu;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.connectionclasse.getjsonfile;
import com.example.projetprincipal.R;

@SuppressLint("NewApi")
public class menu extends Activity implements OnClickListener {
Button met,conf,rech,dec,mesres;String mp,email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        this.setContentView(R.layout.menu);
        mp =this.getIntent().getExtras().getString("mp");
        email=this.getIntent().getExtras().getString("email");
        
	rech =(Button)this.findViewById(R.id.button1);
	conf =(Button)this.findViewById(R.id.button2);
	met =(Button)this.findViewById(R.id.button3);
	dec =(Button)this.findViewById(R.id.button4);
	mesres=(Button)this.findViewById(R.id.button5);
	
	rech.setOnClickListener(this);
	met.setOnClickListener(this);
	conf.setOnClickListener(this);
	dec.setOnClickListener(this);
	mesres.setOnClickListener(this);
	
	StrictMode.ThreadPolicy policy = new StrictMode.
			ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

	
	
	}
	@Override
	public void onClick(View arg0) {
		Intent i=new Intent();
		if(arg0==rech){i.putExtras(this.getIntent().getExtras());i.setClass(this, recherche.recherche.class);this.startActivity(i);}
		if(arg0==met){if(isNetworkAvailable()==true){i.setClass(this, metio.MainActivity.class);this.startActivity(i);}else new AlertDialog.Builder(this).setMessage("Pas De Connection Internet").setTitle("Error")
	        .setNegativeButton("Close", null).show();}
		if(arg0==conf){
		               Intent in=new Intent();
		            
		               in.putExtras(this.getIntent().getExtras());
		               in.setClass(this, com.update.update.class);
		               this.startActivity(in);
		              }
		if(arg0==dec){this.finish();}
		if(arg0==mesres){
			
			String uri="http://10.0.2.2/home/scrproj/index.php?tag=mesres&adrmail="+email;
			getjsonfile re =new getjsonfile(uri);
			try {
				String s=re.prosdata();
			   if(s.length()==3){new AlertDialog.Builder(this).setMessage("Vous n'avez pas des reservation").setTitle("Info")
				        .setNegativeButton("Close", null).show();}
			   else {
				   Bundle b=new Bundle();
				   b.putString("les reservation",s );
			      
			       b.putString("email", email);
				   Intent in=new Intent();
			       in.putExtras(b); 
			       in.setClass(this, com.lesreservation.mesresclasse.class);
			       this.startActivity(in);
			   
			   }
			} catch (ClientProtocolException e) {
			} catch (URISyntaxException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();
			}
		}
	}
	public boolean isNetworkAvailable() {
	    ConnectivityManager cm = (ConnectivityManager) 
	      getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	   
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    }
	    else  return false;
	} 	

}

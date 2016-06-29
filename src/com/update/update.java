package com.update;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import org.json.JSONObject;

import com.connectionclasse.getjsonfile;
import com.example.projetprincipal.R;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class update extends Activity implements OnClickListener {
	TextView  nom,prenom; 
	EditText motpass,adrmail;
	String uri="http://10.0.2.2/home/scrproj/index.php?tag=",motpas,oemail;
	Button update,retour;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.projetprincipal.R.layout.update);
		
		motpas =this.getIntent().getExtras().getString("mp");
		oemail =this.getIntent().getExtras().getString("email");
		
		nom=(TextView) this.findViewById(R.update.nom);
		prenom=(TextView) this.findViewById(R.update.pre);
		motpass=(EditText) this.findViewById(R.update.mp);
		adrmail=(EditText) this.findViewById(R.update.email);
		update=(Button) this.findViewById(R.update.update);
		retour=(Button) this.findViewById(R.update.retour);
		StrictMode.ThreadPolicy policy = new StrictMode.
				ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				
			try {
					
					String s=new getjsonfile(uri+"detail&email="+oemail+"&password="+motpas).prosdata();
				//	Toast.makeText(this, s, Toast.LENGTH_LONG).show();
					
					if(!s.contains("false")){
					JSONObject  jo=new JSONObject(s);
					nom.setText(jo.getString("Nom"));
					prenom.setText(jo.getString("Prenom"));
					adrmail.setText(jo.getString("AdrMail"));
					motpass.setText(jo.getString("motPass"));
					}
				} catch (ClientProtocolException e) {e.printStackTrace();
				} catch (URISyntaxException e) {e.printStackTrace();
				} catch (IOException e) {e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
	update.setOnClickListener(this);retour.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {

if(arg0==update){try {
		String s= new getjsonfile(uri+"update"+"&motpass="+motpass.getText()+"&oadrmail="+oemail+"&nadrmail="+adrmail.getText()).prosdata();
		if(s.contains("1")&&s.length()==2){new AlertDialog.Builder(this).setMessage("votre configuration est termine").setTitle("Info").setIcon(null)
	        .setNegativeButton("Close", null).show();}
		else{new AlertDialog.Builder(this).setMessage("Errror Error 123 viva l'algerie").setTitle("Error")
	        .setNegativeButton("Close", null).show();}
	} catch (ClientProtocolException e) {e.printStackTrace();
	} catch (URISyntaxException e) {e.printStackTrace();
	} catch (IOException e) {e.printStackTrace();
	}
}else {this.finish();}
		
	}

}

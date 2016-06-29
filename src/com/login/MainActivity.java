package com.login;

import java.io.IOException;
import android.content.DialogInterface;
import  java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.connectionclasse.getjsonfile;
import com.example.projetprincipal.R;

@SuppressLint({ "ShowToast", "NewApi" })
public class MainActivity extends Activity implements OnClickListener {
 Button insc,login,call;EditText ed1,ed2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	insc =(Button) this.findViewById(R.acountcreation.createAcount);
	login=(Button) this.findViewById(R.acountcreation.connect);
	call=(Button) this.findViewById(R.acountcreation.call);
	
	ed1 =(EditText) this.findViewById(R.acountcreation.email);
	ed2 =(EditText) this.findViewById(R.acountcreation.password);
	insc.setOnClickListener(this);
	login.setOnClickListener(this);
	call.setOnClickListener(this);
	
	StrictMode.ThreadPolicy policy = new StrictMode.
			ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View arg0) {
		
	Intent i =new Intent();
	if(arg0==login){
	//	Toast.makeText(this, "text", Toast.LENGTH_LONG).show();
	
	
	String l;
	String s="http://192.192.249.10/index.php?tag=login&email="+ed1.getText()+"&password="+ed2.getText();	
	getjsonfile g=new getjsonfile(s);
	try {
		 l= g.prosdata();
		if(l.contains("true")){ Bundle b=new Bundle();
		b.putString("mp", ed2.getText().toString());
		b.putString("email",ed1.getText().toString());
		i.putExtras(b);i.setClass(this, menu.menu.class);this.startActivity(i);}
		else{
			 new AlertDialog.Builder(this).setMessage("incorrect Mot de Passe ou Email").setTitle("Error")
		        .setNegativeButton("Close", null).show();
			 }
	} catch (ClientProtocolException e) {e.printStackTrace();
	} catch (URISyntaxException e) {e.printStackTrace();
	} catch (IOException e) {e.printStackTrace();}	
	}
	
	if(arg0==insc){
		i.setClass(this, inscription.inscription.class);
		
		this.startActivity(i);
		}
	if(arg0==call){
		           String  u ="tel:911";
		           this.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(u)));
		           }


}


	
}
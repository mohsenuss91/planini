package inscription;



import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.connectionclasse.getjsonfile;
import com.example.projetprincipal.R;

@SuppressLint("NewApi")
public class inscription extends Activity implements OnClickListener {
TextView t1,t2,t3,res;EditText t4,t5,t6;Button b1,b2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inscription);
		
		t1=(TextView) this.findViewById(R.id.nom);
		res=(TextView) this.findViewById(R.inscription.result);
		t2=(TextView) this.findViewById(R.inscription.pre);
		t3=(TextView) this.findViewById(R.inscription.email);
		t4=(EditText) this.findViewById(R.inscription.mp);
		t5=(EditText) this.findViewById(R.inscription.mpc);
		b1=(Button) this.findViewById(R.inscription.b1);
		b2=(Button) this.findViewById(R.inscription.b2);
		
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);	
		
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

	@Override
	public void onClick(View arg0) {
	if(arg0==b2){
	if(t3.getText().toString().matches("[0-9a-zA-Z]+@[a-zA-Z]+.[a-zA-Z][a-zA-Z][a-zA-Z]")){     	
	if(t4.getText().toString().equals(t5.getText().toString())){
String s="http://192.192.249.10/index.php?tag=register&name="+
t1.getText()+"&prenom="+t2.getText()+"&password="+t4.getText()+"&email="+t3.getText();
		getjsonfile g=new getjsonfile(s);
		
			try {
			     String ss= g.prosdata();
			    // Toast.makeText(this, ss,Toast.LENGTH_LONG).show();
			if(ss.contains("true")){
				final Intent in =new Intent();
				in.setClass(this, menu.menu.class);
				
				Bundle b = new Bundle();
				b.putString("mp", t4.getText().toString());
				b.putString("email", t3.getText().toString());
				in.putExtras(b);
				
				final Activity f=this;
				new AlertDialog.Builder(this).setMessage("Inscreption valide").setTitle("Info")
		        .setNegativeButton("Close",  new DialogInterface.OnClickListener(){
		            public void onClick(DialogInterface dialog, int which) { 
		            	f.startActivity(in);
		               f.finish();
		            }
		         }).show();
				
				
			};
			} catch (ClientProtocolException e) {e.printStackTrace();
				} catch (URISyntaxException e) {e.printStackTrace();
				} catch (IOException e) {e.printStackTrace();
				}
			
		}else {new AlertDialog.Builder(this).setMessage("Rentrer Vote Mot De Passe").setTitle("Error")
	        .setNegativeButton("Close", null).show();t4.setText(null);t5.setText(null);}
	}else{new AlertDialog.Builder(this).setMessage("Format Incorect du Adresse Mail").setTitle("Error")
		
        .setNegativeButton("Close", null).show();t4.setText(null);t5.setText(null);t3.setText(null);}
	}else {this.finish();	}
		
	}
		
}

package com.lesreservation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.connectionclasse.getjsonfile;
import com.example.projetprincipal.R;

public class mesresclasse extends Activity implements OnClickListener {
TextView t1,t2,t3,t4,t5,t6,t7;Button b1,b2,b3,b4;int index=0;List<mesres> l;String adr;Activity c;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mesres);c=this;
	   
		StrictMode.ThreadPolicy policy = new StrictMode.
				ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);

				
		t1=(TextView)this.findViewById(R.mesres.villed);
		
		t2=(TextView)this.findViewById(R.mesres.villea);
		t3=(TextView)this.findViewById(R.mesres.numvol);
		t4=(TextView)this.findViewById(R.mesres.nbplac);
		t5=(TextView)this.findViewById(R.mesres.date);
		t6=(TextView)this.findViewById(R.mesres.classe);
		t7=(TextView)this.findViewById(R.mesres.np);
		
		b1=(Button)this.findViewById(R.mesres.delete);
		b2=(Button)this.findViewById(R.mesres.fermer);
		b3=(Button)this.findViewById(R.mesres.avan);
		b4=(Button)this.findViewById(R.mesres.prec);
		b1.setOnClickListener(this);b2.setOnClickListener(this);b3.setOnClickListener(this);b4.setOnClickListener(this);
	
		
	
		String s =this.getIntent().getExtras().getString("les reservation");
	   adr=this.getIntent().getExtras().getString("email");
		try {
			l=new parsemesres(s).parse();
		} catch (JSONException e) {e.printStackTrace();
		}
	
			display();
		
	
	
	
	}
	void display(){
	t1.setText(l.get(index).villed);	
	t2.setText(l.get(index).villea);
	t3.setText(l.get(index).id);
	t4.setText(l.get(index).nbplace);
	t5.setText(l.get(index).date);
	t6.setText(l.get(index).classe);
	t7.setText(index+"/"+l.size());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
	
		switch(arg0.getId()){
		case R.mesres.avan:{if((index+1)<l.size()) {index++;display();}break;}
		case R.mesres.prec:{{if(index>0) index--;display();}break;}
		case R.mesres.fermer:{this.finish();break;}
		case R.mesres.delete:{
			try {
		
			String s="http://10.0.2.2/home/scrproj/index.php?tag=delete&adrmail="+adr+"&nbplace="+t4.getText()+"&classe="+t6.getText()+"&idvol="+t3.getText();
			String res = new getjsonfile(s).prosdata();
		    if(res.contains("true")){ new AlertDialog.Builder(this).setMessage("Votre Reservation Est Annuler").setTitle("Info")
		        .setNegativeButton("Close",   new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					 if(index>0){index--;display();}else {new AlertDialog.Builder(c).setMessage("Aucune reservation est disponible").setTitle("Info")
	        .setNegativeButton("Close", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
				
					c.finish();
				}}).show();}
						
					}}).show();
		   
		    }
		} catch (ClientProtocolException e) {e.printStackTrace();
		} catch (URISyntaxException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}
		break;
		}
		default: {}
		}
		
	}

}

package resultat;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import recherche.escall;
import recherche.vol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.connectionclasse.getjsonfile;
import com.example.projetprincipal.R;
import com.connectionclasse.getjsonfile;
public class resultat extends Activity implements OnClickListener {
Button escale,annuler,reser,aven,reven;int index=0; Bundle bun;TabHost th;String mail=null;
TextView  villed,villea,hd,ha,date,comp,numv,nbplace,prix,classe,dur;Context n;
List<vol> l1=new ArrayList<vol>();
List<vol> l2=new ArrayList<vol>();
List<vol> l3=new ArrayList<vol>();
@SuppressLint("NewApi")
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);n=this;
		this.setContentView(R.layout.resultat);
		StrictMode.ThreadPolicy policy = new StrictMode.
				ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
		mail= this.getIntent().getExtras().getString("email");
		
		 th =(TabHost) this.findViewById(R.id.tabhost);
	     th.setup();
	     
	     TabSpec ts =th.newTabSpec("tag1");
	     ts.setIndicator("Aller");
	     ts.setContent(R.id.aetr);
	     th.addTab(ts);
	  
	
	      
	      escale =(Button)this.findViewById(R.resultat.escale);
	      reser=(Button)this.findViewById(R.resultat.reserver);
	      annuler=(Button)this.findViewById(R.resultat.fermer);
	      aven=(Button)this.findViewById(R.resultat.avan);
	      reven=(Button)this.findViewById(R.resultat.rev);
	      
	      
	     escale.setOnClickListener(this);
	     reser.setOnClickListener(this);
	     annuler.setOnClickListener(this);
	     aven.setOnClickListener(this);
	     reven.setOnClickListener(this);
	    
	     dur =(TextView)this.findViewById(R.resultat.dure);
	     villed =(TextView)this.findViewById(R.resultat.villed);
	     villea =(TextView)this.findViewById(R.resultat.villea);
	     date =(TextView)this.findViewById(R.resultat.date);
	     hd =(TextView)this.findViewById(R.resultat.hd);
	     ha =(TextView)this.findViewById(R.resultat.ha);
	     comp =(TextView)this.findViewById(R.resultat.company);
	     numv =(TextView)this.findViewById(R.resultat.numvol);
	     nbplace =(TextView)this.findViewById(R.resultat.nbplace);
	     prix =(TextView)this.findViewById(R.resultat.prix);
	     classe=(TextView) this.findViewById(R.resultat.classe); 
	  
	     th.setOnClickListener(this);
	     
	     bun= new Bundle ();
	     bun=this.getIntent().getExtras();
	     villed.setText(bun.getString("villed"));
		 villea.setText(bun.getString("villea"));
	     
		 String resultat=null;
		 
		 resultat=bun.getString("aller");
	     try {
			 l1 = new parsresult(resultat).parsre();
			 l3=l1;
		} catch (JSONException e1) {e1.printStackTrace();
		}
	     if(l1==null){
	Toast.makeText(this, "nulllllll", Toast.LENGTH_SHORT).show();	 
	     }else Toast.makeText(this, l1.get(0).Ha, Toast.LENGTH_SHORT).show();
	     if(bun.getInt("nb")==2){  
	     ts =th.newTabSpec("tag2");
	     ts.setIndicator("Retour");
	     ts.setContent(R.id.aetr);
	     th.addTab(ts);
	      resultat= bun.getString("retour");
		 try {
			 l2= new parsresult(resultat).parsre();
		} catch (JSONException e) {e.printStackTrace();
		}
	     th.setCurrentTab(1);
	
	     th.getTabWidget().getChildAt(1).setOnClickListener(new OnClickListener() {
	         @Override
	         public void onClick(View v) {
	             index=0;    
	         	l3=l2;
	                 th.setCurrentTab(1);
	                 villed.setText(bun.getString("villea"));
	        		 villea.setText(bun.getString("villed"));
	            update();
	         }
	      });
	     
	     }
	 
	     th.setCurrentTab(0);
	 l3=l1;	
	update();
	
	




	th.getTabWidget().getChildAt(0).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
        	 villed.setText(bun.getString("villed"));
    		 villea.setText(bun.getString("villea"));
            index=0;    
        	l3=l1;
                th.setCurrentTab(0);
           update();
        }
     });

	










}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("ShowToast")
	@Override
	public void onClick(View arg0) {
		switch	(arg0.getId()){
		
		
		
		case R.resultat.avan :{if((index+1)<l3.size()){index++; update();}break;}
		
		case R.resultat.rev :{if(index>0){index--;update();}break;}
		case R.resultat.reserver:{ 
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
	    final EditText input = new EditText(this);
	    alert.setView(input);alert.setTitle("Reseerver");alert.setMessage("Entrer le Nombre des Place");
	    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int whichButton) {
	            String value = input.getText().toString().trim();
	            String uri ="http://192.192.249.10/index.php?tag=reservation&nbplace=4"/*+value*/+"&mail=aharoun@gmail.com"/*+mail+*/+"&classe="+l3.get(index).classe+"&idvol="+l3.get(index).id;
	       try {
			String res= new getjsonfile(uri).prosdata();
			if(res.contains("true")){new AlertDialog.Builder(n).setTitle("Info").setMessage("reservation realiser").setNegativeButton("clase", null).show();}
		} catch (ClientProtocolException e) {e.printStackTrace();
		} catch (URISyntaxException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}
	        }
	    });

	    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int whichButton) {
	            dialog.cancel();
	        }
	    });
	    alert.show();  
	    break;}
		case R.resultat.fermer:{this.finish();break;}

		
		default:{}
		}
	
	
	}
	
	String calcdur(String hd, String ha){
	int j1= Integer.parseInt(hd.split(":")[0]);
	int j2= Integer.parseInt(hd.split(":")[1]); 
    int i1=Integer.parseInt(ha.split(":")[0]);
    int i2=Integer.parseInt(ha.split(":")[1]);	
	int c=0;
    while((j1!=i1)||(j2!=i2)){c++;j2++;if(j2==60){j2=0;j1++;if(j1==24) j1=0;}}
	return c/60+"Heures &"+c%60+"Minutes";
}

	void update(){
		
		Toast.makeText(this, l3.get(0).Ha, Toast.LENGTH_SHORT);
		dur.setText(calcdur(l3.get(index).Hd.substring(0, 5),l3.get(index).Ha.substring(0, 5)));
		prix.setText(l3.get(index).prix);
		comp.setText(l3.get(index).nomcamp);
		date.setText(l3.get(index).date);
		hd.setText(l3.get(index).Hd.substring(0, 5));
		ha.setText(l3.get(index).Ha.substring(0, 5));
		numv.setText(l3.get(index).id);
	    nbplace.setText(l3.get(index).nbplace);
	    classe.setText(l3.get(index).classe);
	    //int i=l3.get(index).list.size();if(i>0) i--;
	    //escale.setText(i+": Escale");
	}
}


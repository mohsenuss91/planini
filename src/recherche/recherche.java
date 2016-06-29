package recherche;



import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;

import org.apache.http.client.ClientProtocolException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.connectionclasse.getjsonfile;
import com.example.projetprincipal.R;

public class recherche extends Activity implements OnClickListener {

	 private EditText mDateDisplay; DatePickerDialog t1; DatePickerDialog t2;RadioButton  r1,r2; 
	private EditText mDateDisplay2;TextView t;Button recherche,pickDate,pickDate2;CheckBox cb;
	 private int mYear;String uria ="http://192.192.249.10/index.php?tag=recherche";Bundle b=new Bundle();
	 private int mMonth;Spinner sp;
	 private int mDay;
	EditText villed,villea,company;
	
	 static final int Start_DATE_DIALOG_ID = 0;
	 static final int End_DATE_DIALOG_ID = 1;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contrainte);
		
		StrictMode.ThreadPolicy policy = new StrictMode.
				ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
		
	
        ArrayAdapter<String> adapter;
        String numbers[] = { "N'importe","Economique", "Affaire", "Premiere"};
        sp = (Spinner) findViewById(R.id.spin);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, numbers);
                   sp.setAdapter(adapter);
        
		 villed=(EditText)this.findViewById(R.id.villed);
		 villea =(EditText)this.findViewById(R.id.villea);
		 cb= (CheckBox)this.findViewById(R.id.checkBox1);
		 r1=(RadioButton)this.findViewById(R.id.radio1);
		 r2=(RadioButton)this.findViewById(R.id.radio2);
         mDateDisplay = (EditText) findViewById(R.id.dateDiplay);
		 mDateDisplay2 = (EditText) findViewById(R.id.dateDiplay2);
	     company =(EditText)this.findViewById(R.id.compagne);
		 pickDate = (Button) findViewById(R.id.pickDate);
	     pickDate2= (Button) findViewById(R.id.pickDate2);
	        
	         recherche=(Button) this.findViewById(R.id.recherche);
	         recherche.setOnClickListener(this);
	         
	/*        pickDate.setOnClickListener(new View.OnClickListener() {
	            @SuppressWarnings("deprecation")
				public void onClick(View v) {
	                showDialog(Start_DATE_DIALOG_ID);
	               
	            }
	        });*/
	    /*    pickDate2.setOnClickListener(new View.OnClickListener() {
	            @SuppressWarnings("deprecation")
				public void onClick(View v) {
	                showDialog(End_DATE_DIALOG_ID);
	               
	            }
	        });*/
	     currentime();
	        updateDisplay(mDateDisplay);
	        updateDisplay(mDateDisplay2);
	  
	    }
	    @Override
	    protected Dialog onCreateDialog(int id) {

	        switch(id){
	            case Start_DATE_DIALOG_ID:{
	                   t1 =new DatePickerDialog(this,mDateSetListener, mYear, mMonth, mDay); return t1;}
	                case End_DATE_DIALOG_ID:{ currentime();
	                    t2= new DatePickerDialog(this,mDateSetListener,mYear, mMonth,mDay);return t2;}
	        }
	            return null;
	    }
	     
	    private void updateDisplay(EditText arg0) {
	        arg0.setText(
	            new StringBuilder()
	            // Month is 0 based so add 1
	            .append(mYear).append("-")
	            .append(mMonth + 1).append("-")
	            .append(mDay).toString());
	      
	    }
	  
	   
	    private DatePickerDialog.OnDateSetListener mDateSetListener =
	        new DatePickerDialog.OnDateSetListener() {
	 
	        @SuppressLint("NewApi")
			public void onDateSet(DatePicker view, int year, int monthOfYear,
	                int dayOfMonth) {
	            mYear = year;
	            mMonth = monthOfYear;
	            mDay = dayOfMonth;
	  if(t1!=null)if(view==t1.getDatePicker()){updateDisplay(mDateDisplay);}
	  if(t2!=null) if(view==t2.getDatePicker()){updateDisplay(mDateDisplay2);}
	           
	           currentime();
	           
	        }
	    };
	   
	    
	    void currentime(){
	    Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
	    }
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View arg0) {
		//Toast.makeText(this, "text", Toast.LENGTH_SHORT).show();
		
	    Intent in =new Intent(this,resultat.resultat.class);
	    
	   
       try {
    	
			

    	   String uri="http://192.192.249.10/index.php?tag=recherche&villed="+villed.getText()+"&villea="+villea.getText()+"&date="+mDateDisplay.getText()+"&company=0"+"&escale=0"+"&classe=n'importe";
    	   getjsonfile g=  new getjsonfile(uri);
			String aller= g.prosdata();
	Toast.makeText(this, aller, Toast.LENGTH_SHORT).show();
			//  Toast.makeText(this, aller, Toast.LENGTH_LONG).show();
			//Toast.makeText(this, aller, Toast.LENGTH_LONG).show();
			
			 
			//Log.e("lee", Integer.toString(Integer.parseInt(aller)));
		
			if(aller.length()!=3)
		    {
				b=new Bundle();
				b=new Bundle();
		    b.putString("villed", villed.getText().toString());
		    b.putString("villea", villea.getText().toString());
		    b.putString("aller", aller);
		       if(r2.isChecked()){
			     			   String retour=new getjsonfile(uria+"&villed=alger"+villea.getText()+"&villea="+villed.getText()+"&date="+mDateDisplay2.getText()+"&escale=0&classe=n'importe"+"&company=0").prosdata();
		            if(retour.length()!=3)
		        {   b.putString("retour", retour);
		        	b.putInt("nb", 2);
		            this.startActivity(in);  
		        }else{b.putInt("nb", 1);new AlertDialog.Builder(this).setMessage("Aucune Vol pour La retour est trouv�").setTitle("Info")
		               .setNegativeButton("Close", new DialogInterface.OnClickListener(){
				            public void onClick(DialogInterface dialog, int which) { 
				            
				            }}).show();
		              }
		    }else{
		    b.putInt("nb", 1);}
		    b.putString("email", this.getIntent().getExtras().getString("email"));
		    in.putExtras(b);    
		   this.startActivity(in);  
		   

       } else{new AlertDialog.Builder(this).setMessage("Aucune Vol trouv�").setTitle("Info")
		        .setNegativeButton("Close", null).show();
		         }
		//		    	Log.e("uri",uria+"&villed="+villed.getText()+"&villea="+villea.getText()+"&date="+mDateDisplay.getText()+"&escale="+i+"&classe="+sp.getSelectedItem().toString()+"&company="+company.getText());
        } catch (ClientProtocolException e) {e.printStackTrace();
		} catch (URISyntaxException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}
		
  	

}
	}

	



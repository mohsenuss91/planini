package recherche;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetprincipal.R;

public class escale extends Activity implements OnClickListener {
	List<escall> e=new ArrayList<escall>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.escale);
		LinearLayout lp=(LinearLayout) this.findViewById(R.id.lp1);
	 

		int ii=this.getIntent().getExtras().getBundle("nb").getInt("n");
	    Bundle b=this.getIntent().getExtras();int j=0;
		while(j<ii){
	    LinearLayout l3=new LinearLayout(this);
		l3.setOrientation(LinearLayout.VERTICAL);
		if(j%2==0)l3.setBackgroundResource(R.drawable.bb);else l3.setBackgroundResource(R.drawable.ml);	
		LinearLayout l1=new LinearLayout(this);l1.setPadding(8, 8, 8, 8);
			l1.setOrientation(LinearLayout.HORIZONTAL);
	        LinearLayout l2=new LinearLayout(this);l2.setPadding(8, 8, 8, 8);
			l2.setOrientation(LinearLayout.HORIZONTAL);
			ImageView im =new ImageView(this);
			im.setBackgroundResource(R.drawable.fleche);
		
			TextView vd=new TextView(this);vd.setText(b.getBundle(Integer.toString(j)).getString("villed"));vd.setTextColor(Color.BLUE);
			TextView va=new TextView(this);va.setText(b.getBundle(Integer.toString(j)).getString("villea"));va.setTextColor(Color.BLUE);
			TextView hd=new TextView(this);hd.setText("Heure De Depart:");
			TextView ha=new TextView(this);ha.setText(" Heure D'arriveé:");
			
			TextView hdv=new TextView(this);hdv.setText(b.getBundle(Integer.toString(j)).getString("hd").substring(0, 5));hdv.setTextColor(Color.BLUE);
			TextView hav=new TextView(this);hav.setText(b.getBundle(Integer.toString(j)).getString("ha").substring(0, 5));hav.setTextColor(Color.BLUE);
			
			l1.addView(vd);
			l1.addView(im);
			l1.addView(va);
			
			l2.addView(hd);l2.addView(hav);
			l2.addView(ha);l2.addView(hdv);
			l3.addView(l1);l3.addView(l2);
		   
		   lp.addView(l3);
		j++;
		}
		
		Button bb=new Button(this);
		bb.setText("Retour");
	    bb.setOnClickListener(this);
	    
		lp.addView(bb);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		this.finish();
		
	}

}

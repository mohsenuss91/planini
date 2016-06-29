package metio;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetprincipal.R;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener {
	rr l;Button re,ch;LinearLayout la;
	ImageView[] im=new ImageView[3];TextView[] tex =new TextView[13];
	parsJsonFile m;EditText ed; 
	String urlp1 ="http://api.worldweatheronline.com/free/v1/weather.ashx?q=";
	String urlp2="&format=json&num_of_days=3&cc=no&includelocation=no&key=bnxxmj4jru5a7ugcej39mq6w";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.metio);
		la= (LinearLayout) this.findViewById(R.id.met);
	 im[0] = (ImageView) this.findViewById(R.id.im1);
	 im[1] = (ImageView) this.findViewById(R.id.im2);
	 im[2] = (ImageView) this.findViewById(R.id.im3);

     tex[12]= (TextView) this.findViewById(R.id.city);
	
	 tex[0] = (TextView) this.findViewById(R.id.vdat1);
	 tex[1] = (TextView) this.findViewById(R.id.tmax1);
	 tex[2] = (TextView) this.findViewById(R.id.tmin1);
	 tex[3] =(TextView) this.findViewById(R.id.des1);
	 
	 tex[4] = (TextView) this.findViewById(R.id.vdat2);
	 tex[5] = (TextView) this.findViewById(R.id.tmax2);
	 tex[6] = (TextView) this.findViewById(R.id.tmin2);
	 tex[7] =(TextView) this.findViewById(R.id.des2);
	 
	 tex[8] = (TextView) this.findViewById(R.id.vdat3);
	 tex[9] = (TextView) this.findViewById(R.id.tmax3);
	 tex[10] = (TextView) this.findViewById(R.id.tmin3);
	 tex[11] =(TextView) this.findViewById(R.id.des3);
	 
	 
	 ch = (Button) this.findViewById(R.id.charg);
	 re = (Button) this.findViewById(R.id.ret);
	 
	 ch.setOnClickListener(this);
	 re.setOnClickListener(this);
     
	 ed =(EditText) this.findViewById(R.id.editv);
	 StrictMode.ThreadPolicy policy = new StrictMode.
			ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
    
    
   m =new parsJsonFile("http://api.worldweatheronline.com/free/v1/weather.ashx?q=annaba&format=json&num_of_days=3&cc=no&includelocation=no&key=bnxxmj4jru5a7ugcej39mq6w");
	proc();
	}
  void proc(){
	  try {
			l= m.getvalues();
			
		    tex[12].setText("Ville/Pays : "+l.city);
	
	for(int i=0;i<9;i+=4){		   
		    	im[i/4].setImageBitmap(l.list.get(i/4).b);
		    	tex[i].setText(l.list.get(i/4).date);
		    	tex[i+1].setText(l.list.get(i/4).max);
		    	tex[i+2].setText(l.list.get(i/4).min);
		    	tex[i+3].setText(l.list.get(i/4).weatherDesc);
		
	}

		} catch (IOException e) {e.printStackTrace();
		} catch (JSONException e) {e.printStackTrace();
		} catch (URISyntaxException e) {e.printStackTrace();
		}
		   
			}
			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.main, menu);
				return true;
			}
  


@Override
public void onClick(View arg0) {

	if(arg0==ch){m=new parsJsonFile(urlp1+ed.getText()+urlp2);
	             try {
					l= m.getvalues();
				} catch (ClientProtocolException e) {e.printStackTrace();
				} catch (URISyntaxException e) {e.printStackTrace();
				} catch (IOException e) {e.printStackTrace();
				} catch (JSONException e) {e.printStackTrace();
				}
	             proc();
	             }
	if(arg0==re){this.finish();}
}
	
}
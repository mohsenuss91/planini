package resultat;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import recherche.escall;
import recherche.vol;


public class parsresult {
	
	String res=null;
	
	parsresult(String s){
		this.res=s;
	}

	List<vol>  parsre() throws JSONException{
		JSONArray ja =new JSONArray(res);
		List<vol> lv=new ArrayList<vol>(ja.length());
		
		
		for(int i=0;i<ja.length();i++){
		JSONObject jo =ja.getJSONObject(i);
		JSONArray escale =jo.getJSONArray("escale");
			vol v=new vol();
	
			v.date=jo.getString("date_depart");
	
			v.Ha=jo.getString("heur_arrive");
			
			v.Hd=jo.getString("heur_dapart");
			v.id=jo.getString("id_vol");
			
		    v.classe=jo.getString("class");
			v.nomcamp=jo.getString("nom_company");
            
			v.prix=jo.getString("prix");
			//res=v.prix;
			
			
				v.list =new ArrayList<escall>(escale.length());
		
	/*	     for(int j=0;j<escale.length();j++){
			 escall e=new escall();
			// e.ha= escale.getJSONObject(j).getString("ha");
			 
			// e.hd=escale.getJSONObject(j).getString("hd");
			
			 e.va=escale.getJSONObject(j).getString("arrive");
			 
			 e.vd=escale.getJSONObject(j).getString("depart");
			 v.list.add(e);
	
		     }*/
			
		lv.add(v);	
		
		}
			
	
	return lv; 
	}
	
}

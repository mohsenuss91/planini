package com.lesreservation;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class parsemesres {
  String s;
  public parsemesres(String s){this.s=s;}
  
  List<mesres>  parse() throws JSONException{
	  JSONArray ja= new JSONArray(s);
	  List<mesres>  l=new ArrayList<mesres>(ja.length());
	  for(int i=0;i<ja.length();i++){
		  mesres m=new mesres();
		 JSONObject jo= ja.getJSONObject(i);
	     m.villed=jo.getString("villed");
	     m.villea=jo.getString("villea");
	     m.id=jo.getString("idvol");
	     m.nbplace=jo.getString("nbplace");
	     m.classe=jo.getString("classe");
	     m.date=jo.getString("date");
	     l.add(m);
	  }
	  
	  return l;
  }
	
}

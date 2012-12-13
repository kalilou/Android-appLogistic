package gigadroid.com;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

public class Manager extends TabActivity implements OnTabChangeListener, OnInitListener {

	TabHost tabHost;
	TextToSpeech tts;
	HashMap<String,String> map;
	
	
	TabSpec spec;
	TabWidget widget;
	FrameLayout layout;
	LinearLayout tabClient, tabCamion, tabChauffeur, tabOperation;
	ListView listClient, listCamion, listChauffeur;
	TextView client, camion, chauffeur, operation;
	Button editAll, mapOperation;
	

	
	DBadapter db;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manager);

		widget = (TabWidget) this.findViewById(android.R.id.tabs);
		

		//creation de ma tabHost, les onglets
		tabHost = (TabHost) this.findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		
		spec = tabHost.newTabSpec("Operation");
		spec.setContent(R.id.editClient);
		spec.setIndicator("Operation");
		tabHost.addTab(spec);
		
		
		spec = tabHost.newTabSpec("Chauffeur");
		spec.setContent(R.id.chauffeurLinear);
		spec.setIndicator("Chauffeur");
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("Camion");
		spec.setContent(R.id.CamionLinear);
		spec.setIndicator("Camion");
		tabHost.addTab(spec);
		
		
		
		

		TabSpec spec = tabHost.newTabSpec("Client");
		spec.setContent(R.id.clientLinear);
		spec.setIndicator("Client");
		tabHost.addTab(spec);
	
		
		LoadClient();
		LoadCamion();
		LoadChauffeur();
		loadFromXML();
		
		mapOperation.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
			}
		});
		
		editAll.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Intent intent = new Intent(v.getContext(), Editactivity.class);
				
				startActivity(intent);
				
			}
		});

//		});
		//base de donnee instancie
	   // db = new DBadapter(this);
		//ouverture de la base de donnees
    	//db.open();
//    	Log.i("Dans la crŽation", "debut");
//		
//        db.insertToDBClient(String.valueOf(R.drawable.icon),
//       								"Diaby", "Kalilou", 
//      								"0533181410","ENSIAS",
//        								"kalilou1988@gmail.com", 
//        								"computime");
		
		 Intent checkIntent = new Intent();
         checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
         startActivityForResult(checkIntent, 0);
	}

	
	protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                tts = new TextToSpeech(this, this);
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }
	
	
	
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	}
	

	
	public void LoadClient(){
		
		 
		listClient = (ListView) findViewById(R.id.listclient);

		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	        //On déclare la HashMap qui contiendra les informations pour un item
	        HashMap<String, String> map;
	        map = new HashMap<String, String>();
	        map.put("clientname", "Kalilou diaby");
	        map.put("country", "Mali");
	        map.put("imgClient", String.valueOf(R.drawable.diaby));
	        map.put("social1",String.valueOf(R.drawable.facebook));
	        map.put("social2",String.valueOf(R.drawable.skype));
	        map.put("social3",String.valueOf(R.drawable.twitter));
	        
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Linnea Andriansson");
	        map.put("country", "Sweden");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        map.put("social1",String.valueOf(R.drawable.facebook));
	        map.put("social2",String.valueOf(R.drawable.skype));
	        map.put("social3",String.valueOf(R.drawable.twitter));
	        
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Wrapp");
	        map.put("country", "Sweden");
	        map.put("imgClient", String.valueOf(R.drawable.wrapp));
	        map.put("social1",String.valueOf(R.drawable.facebook));
	        map.put("social2",String.valueOf(R.drawable.skype));
	        map.put("social3",String.valueOf(R.drawable.twitter));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Mamadou Diaby");
	        map.put("country", "Mali");
	        map.put("imgClient", String.valueOf(R.drawable.mamadou));
	        map.put("social1",String.valueOf(R.drawable.facebook));
	        map.put("social2",String.valueOf(R.drawable.linkedin));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Chankai");
	        map.put("country", "Chine");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        map.put("social1",String.valueOf(R.drawable.facebook));
	        map.put("social2",String.valueOf(R.drawable.skype));
	        map.put("social3",String.valueOf(R.drawable.twitter));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Alexis");
	        map.put("country", "France");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        map.put("social1",String.valueOf(R.drawable.facebook));
	        map.put("social2",String.valueOf(R.drawable.skype));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Sofia Ericksson");
	        map.put("country", "Sweden");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        map.put("social1",String.valueOf(R.drawable.facebook));
	        map.put("social2",String.valueOf(R.drawable.skype));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Babacar N diaye");
	        map.put("country", "Senegal");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        map.put("social1",String.valueOf(R.drawable.facebook));
	        map.put("social2",String.valueOf(R.drawable.skype));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Samuel Tettner");
	        map.put("country", "Netherlands");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        map.put("social1",String.valueOf(R.drawable.facebook));
	        map.put("social2",String.valueOf(R.drawable.skype));
	        map.put("social3",String.valueOf(R.drawable.twitter));
	        listItem.add(map);
	        
	       
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Jeffrey Hunter");
	        map.put("country", "Australia");
	        map.put("imgClient", String.valueOf(R.drawable.jeffrey));
	        map.put("social1",String.valueOf(R.drawable.facebook));
	        map.put("social2",String.valueOf(R.drawable.skype));
	        map.put("social3",String.valueOf(R.drawable.twitter));
	        listItem.add(map);
	        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.clientlist,
		               new String[] {"imgClient", "clientname","country","social1","social2","social3"}, new int[] {R.id.imgClient, R.id.clientname,R.id.country,R.id.social1
	        	,R.id.social2,R.id.social3});
		        listClient.setAdapter(mSchedule);
		        
		        listClient.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long id) {
						@SuppressWarnings("unchecked")
						final
						HashMap<String, String> map = (HashMap<String, String>) listClient.getItemAtPosition(position);
						AlertDialog.Builder adb = new AlertDialog.Builder(Manager.this);
						
						adb.setTitle("More Information about " + map.get("clientname"));
						adb.setIcon(R.drawable.contacts);
						adb.setNegativeButton("Call " +  map.get("clientname"), null);						
						adb.setPositiveButton("Send msg to " +  map.get("clientname"), new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								
								try {
							        Intent callIntent = new Intent(Intent.ACTION_DIAL,Uri.parse(map.get("clientname")));
							        startActivity(callIntent);
							    } catch (ActivityNotFoundException e) {
							        Log.e("helloandroid dialing example", "Call failed", e);
							    }
							}
								
						});
						adb.setNeutralButton("Cancel", null);
						adb.show();
		               
						
					}
				});
		        
		       
		        
		}  

	
	public void LoadChauffeur(){
		
		 
		listChauffeur = (ListView) findViewById(R.id.listChauffeur);

		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	        //On déclare la HashMap qui contiendra les informations pour un item
	        HashMap<String, String> map;
	        map = new HashMap<String, String>();
	        map.put("clientname", "Kalilou diaby");
	        map.put("country", "Mali");
	        map.put("imgClient", String.valueOf(R.drawable.diaby));
	     
	        listItem.add(map);
	        
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Wrapp");
	        map.put("country", "Sweden");
	        map.put("imgClient", String.valueOf(R.drawable.wrapp));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Mamadou Diaby");
	        map.put("country", "Mali");
	        map.put("imgClient", String.valueOf(R.drawable.mamadou));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Chankai");
	        map.put("country", "Chine");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Alexis");
	        map.put("country", "France");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Sofia Ericksson");
	        map.put("country", "Sweden");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Babacar N diaye");
	        map.put("country", "Senegal");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Samuel Tettner");
	        map.put("country", "Netherlands");
	        map.put("imgClient", String.valueOf(R.drawable.client));
	        listItem.add(map);
	        
	       
	        
	        map = new HashMap<String, String>();
	        map.put("clientname", "Jeffrey Hunter");
	        map.put("country", "Australia");
	        map.put("imgClient", String.valueOf(R.drawable.jeffrey));
	        listItem.add(map);
	        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.clientlist,
		               new String[] {"imgClient", "clientname","country"}, new int[] {R.id.imgClient, R.id.clientname,R.id.country});
		        listChauffeur.setAdapter(mSchedule);
		        
		       
		        
		}  

	
	public void LoadCamion(){
		
		 
		listCamion= (ListView) findViewById(R.id.listCamionview);

		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	        //On déclare la HashMap qui contiendra les informations pour un item
	        HashMap<String, String> map;
	        map = new HashMap<String, String>();
	        map.put("marqueCamion", "VOLVO");
	        map.put("typeCamion", "level1");
	        map.put("imgCamion", String.valueOf(R.drawable.volvo));
	        map.put("imgCa", String.valueOf(R.drawable.volvoee));
	        map.put("plaqueCamion", "VE52852");
	        listItem.add(map);
	        
	        
	        map = new HashMap<String, String>();
	        map.put("marqueCamion", "VOLVO");
	        map.put("typeCamion", "level2");
	        map.put("imgCamion", String.valueOf(R.drawable.volvo1));
	        map.put("imgCa", String.valueOf(R.drawable.volvoee));
	        map.put("plaqueCamion", "VE520200");
	        listItem.add(map);
	        
	        
	        map = new HashMap<String, String>();
	        map.put("marqueCamion", "MERCEDES");
	        map.put("typeCamion", "level1");
	        map.put("imgCamion", String.valueOf(R.drawable.mercedes1));
	        map.put("plaqueCamion", "ME524440");
	        map.put("imgCa", String.valueOf(R.drawable.mercedes));
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("marqueCamion", "MERCEDES");
	        map.put("typeCamion", "level2");
	        map.put("imgCamion", String.valueOf(R.drawable.mercedes2)); 
	        map.put("imgCa", String.valueOf(R.drawable.mercedes));
	        map.put("plaqueCamion", "ME524777");
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("marqueCamion", "TOYOTA");
	        map.put("typeCamion", "level1");
	        map.put("imgCa", String.valueOf(R.drawable.toyota));
	        map.put("imgCamion", String.valueOf(R.drawable.toyota1));
	        map.put("plaqueCamion", "ME524777");
	        listItem.add(map);
	        
	        map = new HashMap<String, String>();
	        map.put("marqueCamion", "TOYOTA");
	        map.put("typeCamion", "level2");
	        map.put("imgCa", String.valueOf(R.drawable.toyota));
	        map.put("imgCamion", String.valueOf(R.drawable.toyota2));
	        map.put("plaqueCamion", "ME524777");
	        listItem.add(map);
	    
	    
	        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.camionlist,
		               new String[] {"marqueCamion", "typeCamion","imgCamion","plaqueCamion","imgCa"}, new int[] {R.id.marqueCamion, R.id.typeCamion,R.id.imgCamion,R.id.plaqueCamion,R.id.imgCa});
	        listCamion.setAdapter(mSchedule);
		        
		       
		        
		}  

	@Override
	protected void onDestroy() {
		
		//db.close();
		super.onDestroy();
	}

	
	public void loadFromXML(){	
	//editCamion = (Button) findViewById(R.id.EditCamion);
	editAll = (Button) findViewById(R.id.EditAll);
	mapOperation = (Button) findViewById(R.id.MapOperation);
		
	}

	public void onTabChanged(String arg0) {
		
		
		
		if (arg0 == "Camion"){
			tabHost.setCurrentTab(0);
		}
		if (arg0 == "Chauffeur"){
			tabHost.setCurrentTab(1);
		}
		if (arg0 == "Operation"){
			tabHost.setCurrentTab(2);
		}
		if (arg0 == "Client"){
			tabHost.setCurrentTab(3);
		}

	}


	
}

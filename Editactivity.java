package gigadroid.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Editactivity extends Activity{


	//GridView gridscipro;
	ImageView image;
	ListView listClient;
	Button editClient, editCamion, editChauffeur;
	LinearLayout clientL, camionL, chauffeurL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.editlist);
	    
	    editClient = (Button) findViewById(R.id.EditCientBt);
	    editCamion = (Button) findViewById(R.id.EditCamionBt);
	    editChauffeur = (Button) findViewById(R.id.EditChauffeurBt);
	    
	    
	    clientL = (LinearLayout) findViewById(R.id.formulaireClient);
	    camionL = (LinearLayout) findViewById(R.id.formulaireCamion);
	    chauffeurL = (LinearLayout) findViewById(R.id.formulaireChauffeur);
	    
	    editClient.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				clientL.setVisibility(LinearLayout.VISIBLE);
				chauffeurL.setVisibility(LinearLayout.INVISIBLE);
				camionL.setVisibility(LinearLayout.INVISIBLE);
			}
		});
	    
	    editCamion.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				clientL.setVisibility(LinearLayout.INVISIBLE);
				chauffeurL.setVisibility(LinearLayout.INVISIBLE);
				camionL.setVisibility(LinearLayout.VISIBLE);
			}
		});
	    
	    editChauffeur.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				clientL.setVisibility(LinearLayout.INVISIBLE);
				chauffeurL.setVisibility(LinearLayout.VISIBLE);
				camionL.setVisibility(LinearLayout.INVISIBLE);
			}
		});
	    
	}
	   
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}


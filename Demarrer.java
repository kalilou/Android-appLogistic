package gigadroid.com;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class Demarrer extends Activity implements OnClickListener, OnInitListener {
 
	Button buttonCon, buttonDem;
	TextToSpeech mTts;
	DBadapter db;
	CheckBox box;
	LinearLayout lin1, lin2,lin3;
	RadioButton butt1,butt2;
	TextView newManager,nomMang,prenomMang,adresseManag,passaword2,langue;
	EditText eLogin,ePassword, EnomM,EprenomM,eRePassword;
	RadioButton option1,option2;
	String mm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_vue);
        
        buttonDem = (Button)  		 this.findViewById(R.id.ButtonDem);
		buttonCon = (Button)  		 this.findViewById(R.id.ButtonCon);
		box       = (CheckBox) 		 this.findViewById(R.id.checkbox);
		lin1      = (LinearLayout)	 this.findViewById(R.id.LineaireCon1);
		lin2      = (LinearLayout)	 this.findViewById(R.id.LineaireCon2);
		lin3      = (LinearLayout)	 this.findViewById(R.id.addMannger);
		butt1     = (RadioButton)    this.findViewById(R.id.option1);
		butt2     = (RadioButton)    this.findViewById(R.id.option2);
		EnomM     =(EditText)		 this.findViewById(R.id.nomManager);
	 EprenomM	  =(EditText)		 this.findViewById(R.id.prenomManager);
	 option1      =(RadioButton) 	 this.findViewById(R.id.option1);
	 option2      =(RadioButton) 	 this.findViewById(R.id.option2);
	 langue 	  =(TextView)		 this.findViewById(R.id.langue);
	 newManager   =(TextView)		 this.findViewById(R.id.newManager);
	 eLogin       =(EditText)		 this.findViewById(R.id.loginManager);
	 ePassword    =(EditText)		 this.findViewById(R.id.passwordManager);
	 eRePassword  =(EditText)        this.findViewById(R.id.rePasswordManager);
	 
	 	
		butt1.setOnClickListener(this);
		butt2.setOnClickListener(this);
		buttonDem.setOnClickListener(this);
		EnomM.setOnClickListener(this);
		buttonCon.setOnClickListener(this);
		
		// verification des librairie de  textToSpeech  s'ils sont bien installer sur le telephone android
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, 0);	   
    }
    
    
    public void onClickChangeBox1(View view) {
    	
		if(box.isChecked()){
			
			if(butt1.isChecked()){
			mTts.speak("Veuillez vous enregistrez",TextToSpeech.QUEUE_ADD, null);
			}
			else{
			mTts.speak("Please make your subscription",TextToSpeech.QUEUE_ADD, null);
			}
				
			lin3.setVisibility(LinearLayout.VISIBLE);
			lin1.setVisibility(LinearLayout.INVISIBLE);
			lin2.setVisibility(LinearLayout.INVISIBLE);
			
		}
		
		else{				
			
			if(butt1.isChecked()){
			mTts.speak("Veuillez vous connectez",TextToSpeech.QUEUE_ADD, null);	
			}
			else{ 
				mTts.speak("Please make your connexion",TextToSpeech.QUEUE_ADD, null);	
			}

			lin3.setVisibility(LinearLayout.INVISIBLE);
			lin1.setVisibility(LinearLayout.VISIBLE);
			lin2.setVisibility(LinearLayout.VISIBLE);	
		}
		
		

}

	public void onClick(View v) {
		if(v.getId() == R.id.option1){			
			francais();
		}
		if(v.getId() == R.id.option2){
			english();
		}
		
		if(v.getId() == R.id.ButtonCon){
			
			if (!mTts.getLanguage().equals(Locale.FRANCE)){
			mTts.speak("Bienvenue dans la section manager", TextToSpeech.QUEUE_ADD, null);
			}
			if (mTts.getLanguage().equals(Locale.ENGLISH)){
				mTts.speak("Welcome to the manager section", TextToSpeech.QUEUE_ADD, null);
			}
				
			Intent intent = new Intent(this,Manager.class);
			startActivity(intent);	
			
		}
		
		if(v.getId() == R.id.ButtonDem ){
			
			
			mm = mTts.getLanguage().toString();
			
			
			
			if(mm.equals("fra_FRA")){
			
			if(EnomM.getText().toString().equals("")){
				mTts.speak("Veuillez Saisir le nom ", TextToSpeech.QUEUE_ADD, null);
				EnomM.setHint("Nom ici");		
			}
			else if(EprenomM.getText().toString().equals("")){
				mTts.speak("Veuillez Saisir le prenom ", TextToSpeech.QUEUE_ADD, null);
				EprenomM.setHint("prenom ici ");				
			}
			else if(eLogin.getText().toString().equals("")){
				mTts.speak("Veuillez Saisir le login", TextToSpeech.QUEUE_ADD, null);
				eLogin.setHint("login ici ");				
			}
			else if(ePassword.getText().toString().equals("")){
				mTts.speak("Veuillez Saisir le mot de passer", TextToSpeech.QUEUE_ADD, null);
				ePassword.setHint("Mot de passe ici ");				
			}
			else if(eRePassword.getText().toString().equals("")){
				mTts.speak("Veuillez Saisir le meme mot de passer ", TextToSpeech.QUEUE_ADD, null);
				eRePassword.setHint(" le meme mot de passe ici ");				
			}

			else{		
				
					mTts.speak("Bienvenue dans la section manager", TextToSpeech.QUEUE_ADD, null);
					Intent intent = new Intent(this,Manager.class);
					startActivity(intent);
					
					
			}
			}
			
			else{						
				
				if(EnomM.getText().toString().equals("")){
					mTts.speak("Please type the last name ", TextToSpeech.QUEUE_ADD, null);
					EnomM.setHint("last name here");		
				}
				else if(EprenomM.getText().toString().equals("")){
					mTts.speak("Please type the first name ", TextToSpeech.QUEUE_ADD, null);
					EprenomM.setHint("first name here ");				
				}
				else if(eLogin.getText().toString().equals("")){
					mTts.speak("Please type the login", TextToSpeech.QUEUE_ADD, null);
					eLogin.setHint("login here ");				
				}
				else if(ePassword.getText().toString().equals("")){
					mTts.speak("Please type the password", TextToSpeech.QUEUE_ADD, null);
					ePassword.setHint("password here ");				
				}
				else if(eRePassword.getText().toString().equals("")){
					mTts.speak("Please type the same password ", TextToSpeech.QUEUE_ADD, null);
					eRePassword.setHint(" same password here ");				
				}

				else{		
						Intent intent = new Intent(this,Manager.class);
						startActivityForResult(intent, 200);					
				
			}
				
			}
		}
		
	}
	
	
	
	
	protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                mTts = new TextToSpeech(this, this);
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
 }
	
	
	public void onInit(int arg0) {
		mTts.setLanguage(Locale.FRANCE);

	}
	
	
	public void francais(){
		mTts.setLanguage(Locale.FRANCE);
		mTts.speak("Bienvenue dans l'applicaton mobile logistique", TextToSpeech.QUEUE_ADD, null);
		newManager.setText("Nouveau Manager");
		buttonDem.setText("Demarrer");
		option1.setText("Francais");
		option2.setText("Englais");
		langue.setText("Choisir une langue");

	}
		
	public void english(){
		mTts.setLanguage(Locale.ENGLISH);
		mTts.speak("Welcome to mobile logistic", TextToSpeech.QUEUE_ADD, null);
		newManager.setText("New Manager");
		buttonDem.setText("Start here");
		option1.setText("French");
		option2.setText("English");
		langue.setText("Choose your language");
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(mTts != null){
			mTts.stop();
			mTts.shutdown();
		}
}
	
	
}
package gigadroid.com;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBadapter {
	

	DatabaseHelper DBHelper;
	
	Context context;
	
	SQLiteDatabase db;
	
	private String DB_MANAGER = "create table manager(_id integer primary key autoincrement," +
			"photo text not null," +
			"nom text not null," +
			"prenom text not null," +
			"adresse text not null," +
			"telephone text not null," +
			"email text not null" +
			");";
	
	private String DROP_MANAGER = "drop table if exists manager";
	private String DROP_CLIENT = "drop table if exists manager";
	private String DROP_CAMION = "drop table if exists manager";
	private String DROP_CHAUFFEUR = "drop table if exists manager";
	
	//the constructor of DBadapter
		public DBadapter(Context context){
			this.context = context;
			DBHelper = new DatabaseHelper(context);
		}
	
		public class DatabaseHelper extends SQLiteOpenHelper{

			Context context;
			
			//the constructor of the class DataHelper
			public DatabaseHelper(Context context) {
				super(context, "logistic", null, 1);
				this.context = context;
			
			}

			@Override
			public void onCreate(SQLiteDatabase arg0) {
				db.execSQL("create table client (_id integer primary key autoincrement," +
						"photo text not null,"+
						"nom text not null," +
						"prenom text not null," +
						"adresse text not null," +
						"telephone text not null," +
						"email text not null," +
						"entreprise text not null"
						+ ");");
				
				db.execSQL(DB_MANAGER);

			}

			@Override
			public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
				//show the message on the screen for users
				db.execSQL(DROP_CLIENT);
				db.execSQL(DROP_MANAGER);
				db.execSQL(DROP_CAMION);
				db.execSQL(DROP_CHAUFFEUR);
				onCreate(db);
			}
		}
				
		//method for opening the database
				public DBadapter open(){
					db = DBHelper.getWritableDatabase();
					return this;				
					}
				
				//method to close the database
				public void close(){
					db.close();
				}
				
				//method to delete the items of database
				public void truncatClient(){
					db.execSQL("DELETE FROM client");
				}
				
				public void truncatManager(){
					db.execSQL("DELETE FROM manager");
				}
				
				//method to insert into database
				public long insertToDBClient(String photo, String nom, String prenom, String adresse, String telephone,
						String email, String entreprise){
					//create an object is like container for us items before inserting
					ContentValues values = new ContentValues();			
					values.put("photo",photo);
					values.put("nom", nom);
					values.put("prenom", prenom);
					values.put("adresse", adresse);
					values.put("telephone", telephone);
					values.put("email", email);
					values.put("entreprise", entreprise);
					return db.insert("client", null, values);	
				}
				
				
				//method to insert into database
						public long insertToDBManager(String photo, String nom, String prenom, String adresse, String telephone,
								String email){
							//create an object is like container for us items before inserting
							ContentValues values = new ContentValues();			
							values.put("photo",photo);
							values.put("nom", nom);
							values.put("prenom", prenom);
							values.put("adresse", adresse);
							values.put("telephone", telephone);
							values.put("email", email);
							return db.insert("manager", null, values);	
						}
				
				//delete a item from database
				public boolean deleteFromDBClient(int id){
					return db.delete("client", "_id=" + id, null) > 0;
				}
				
				//delete a item from database
						public boolean deleteFromDBManager(int id){
							return db.delete("manager", "_id=" + id, null) > 0;
						}
				
				//method to show the database items on the phone screen
				public Cursor getClient(){			
					Cursor cursor = db.query("client",new String[]{
					                                      "nom",
					                                      "prenom",
					                                      "adresse",
					                                      "telephone",
					                                      "email",
					                                      "entreprise"
					                                      }
					,null, null, null, null, null,null);
								
					return cursor;
					
				}
				
				
				//method to show the database items on the phone screen
						public Cursor getManager(){			
							Cursor cursor = db.query("client",new String[]{
							                                      "_id",
							                                      "photo",
							                                      "nom",
							                                      "prenom",
							                                      "adresse",
							                                      "telephone",
							                                      "email"
							                                      }
							,null, null, null, null, null,null);
										
							return cursor;
							
						}
				
				
				// method to show the number of client from database
		public int  getNumberclient(){
					
					return db.query("client",new String[]{
					                                      "_id",
					                                      "photo",
					                                      "nom",
					                                      "prenom",
					                                      "adresse",
					                                      "telephone",
					                                      "email",
					                                      "entreprise"
					                                      }
					,null, null, null, null, null).getCount();
				}


		public void getInfosContact(int id){
			Cursor cursor =  db.query("contacts", new String[]{
					"_id",
					"nom",
					"photo",
					"prenom",
					"adresse",
					"telephone",
					"email",
					"entreprise"
			}, null, null, null, null, null);
			cursor.moveToPosition(id);
			Log.i("Contenu du curseur : ", ""+cursor.toString());
			
			String nom = new String("");
			String photo = new String("");
			String prenom = new String("");
			String adresse = new String("");
			String telephone = new String("");
			String email = new String("");
			String entreprise = new String("");
			photo = cursor.getString(1);
			nom  = cursor.getString(2);
			prenom = cursor.getString(3);
			adresse = cursor.getString(4);
			telephone = cursor.getString(5);
			email = cursor.getString(6);
			entreprise = cursor.getString(7);
			Integer.parseInt(photo);
			AlertDialog.Builder contact = new AlertDialog.Builder(context);
			contact.setIcon(R.drawable.icon);
			contact.setItems(new String[]{nom,prenom,adresse,telephone,email,entreprise},new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			contact.setPositiveButton("close", new android.content.DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			contact.show();
			
		}
}

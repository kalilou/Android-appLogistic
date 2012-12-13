package gigadroid.com;

import android.graphics.Bitmap;

public class StandardClass {
	

	String nameActivity;
	Bitmap image;
	
	public StandardClass(String nameActivity, Bitmap image) {
		super();
		this.nameActivity = nameActivity;
		this.image = image;
	}
	public String getNameActivity() {
		return nameActivity;
	}
	public void setNameActivity(String nameActivity) {
		this.nameActivity = nameActivity;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}

}

package socialtoilet.android.activities;

import java.util.Collection;
import java.util.UUID;

import socialtoilet.android.R;
import socialtoilet.android.model.GaleryManager;
import socialtoilet.android.model.IToiletPicture;
import socialtoilet.android.services.factories.ServicesFactory;
import socialtoilet.android.services.get.IRetrieveToiletGaleryService;
import socialtoilet.android.services.get.IRetrieveToiletGaleryServiceDelegate;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;

public class ToiletGaleryActivity extends Activity
	implements IRetrieveToiletGaleryServiceDelegate
{
	private static final int DEFAULT_GALLERY_SIZE = 3; 
	private final int PICKER = 1;
	private int currentPic = 0;
	private int currentSize = DEFAULT_GALLERY_SIZE;
	private Gallery picGallery;
	private ImageView picView;
	private PicAdapter imgAdapt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toilet_galery);
		setupActionBar();
		
		Intent intent = getIntent();
		String toiletId = intent.getStringExtra(DetailToiletActivity.EXTRA_TOILET_ID);
		UUID id = UUID.fromString(toiletId);
		
		
		picView = (ImageView) findViewById(R.id.picture);
		picGallery = (Gallery) findViewById(R.id.gallery);
		imgAdapt = new PicAdapter(this, DEFAULT_GALLERY_SIZE);
		picGallery.setAdapter(imgAdapt);
			
		picGallery.setOnItemClickListener(new OnItemClickListener() {
		    //handle clicks
		    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        //set the larger image view to display the chosen bitmap calling method of adapter class
		        picView.setImageBitmap(imgAdapt.getPic(position));
		        currentPic = position;
		    }
		});
		
//		IRetrieveToiletGaleryService service = ServicesFactory.createRetrieveToiletGaleryService();
//		service.retrieveToiletGalery(id, this);
	}

public class PicAdapter extends BaseAdapter {
		
		int defaultItemBackground;
		private Context galleryContext;
		private Bitmap[] imageBitmaps;
		Bitmap placeholder;
		int size;
		
		public PicAdapter(Context c, int size) {
		    galleryContext = c;
		    imageBitmaps  = new Bitmap[size];
	
// imagenes iniciales harcodeadas
		    imageBitmaps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.clean1);
		    imageBitmaps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.clean2);
		    imageBitmaps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.clean3);
 //		    placeholder = BitmapFactory.decodeResource(getResources(), R.drawable.clean1);   
//		    for(int i=0; i<imageBitmaps.length; i++)
//		        imageBitmaps[i]=placeholder;
		    
		    TypedArray styleAttrs = galleryContext.obtainStyledAttributes(R.styleable.PicGallery);
		    defaultItemBackground = styleAttrs.getResourceId(
		    		R.styleable.PicGallery_android_galleryItemBackground, 0);
		    styleAttrs.recycle();
		}
		
		public PicAdapter(Context c, int size, PicAdapter ad) {
		    galleryContext = c;
		    imageBitmaps  = new Bitmap[size];
		    
		    for(int i=0; i<ad.getCount(); i++)
		    	imageBitmaps[i]=ad.getPic(i);

		    
		    TypedArray styleAttrs = galleryContext.obtainStyledAttributes(R.styleable.PicGallery);
		    defaultItemBackground = styleAttrs.getResourceId(
		    		R.styleable.PicGallery_android_galleryItemBackground, 0);
		    styleAttrs.recycle();
		}
		
	    public int getCount() {
	        return imageBitmaps.length;
	    }
	    
	    public Bitmap getPic(int posn) {
	        return imageBitmaps[posn];
	    }
	    
	    public Object getItem(int position) {
	        return position;
	    }

	    public void addPic(Bitmap newPic) {	    	
	        imageBitmaps[currentPic] = newPic;	        
	    }
	    
	    public long getItemId(int position) {
	        return position;
	    }
	    
	    public View getView(int position, View convertView, ViewGroup parent) {
	        
	    	ImageView imageView = new ImageView(galleryContext);
	        imageView.setImageBitmap(imageBitmaps[position]);
	        imageView.setLayoutParams(new Gallery.LayoutParams(300, 200));
	        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	        imageView.setBackgroundResource(defaultItemBackground);
	        return imageView;
	    }
	}
	
	



	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == PICKER) {
				Uri pickedUri = data.getData();
				Bitmap pic = null;
				String imgPath = "";
				String[] medData = { MediaStore.Images.Media.DATA };
				Cursor picCursor = managedQuery(pickedUri, medData, null, null, null);
    	
				if(picCursor!=null) {
					int index = picCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					picCursor.moveToFirst();
					imgPath = picCursor.getString(index);
				}
				else
					imgPath = pickedUri.getPath();
					if(pickedUri!=null) {
						int targetWidth = 600;
						int targetHeight = 400;
						BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
						bmpOptions.inJustDecodeBounds = true;
						BitmapFactory.decodeFile(imgPath, bmpOptions);
						int currHeight = bmpOptions.outHeight;
						int currWidth = bmpOptions.outWidth;
						int sampleSize = 1;
    		
						if (currHeight>targetHeight || currWidth>targetWidth) {
							if (currWidth>currHeight)
								sampleSize = Math.round((float)currHeight/(float)targetHeight);
							else
								sampleSize = Math.round((float)currWidth/(float)targetWidth);
						}
    		
						bmpOptions.inSampleSize = sampleSize;	    		
						bmpOptions.inJustDecodeBounds = false;	    		
						pic = BitmapFactory.decodeFile(imgPath, bmpOptions);
    		
						currentPic = currentSize;
						currentSize++;
						
						picView = (ImageView) findViewById(R.id.picture);
						picGallery = (Gallery) findViewById(R.id.gallery);
						imgAdapt = new PicAdapter(this,currentSize, imgAdapt);
						
						picGallery.setAdapter(imgAdapt);
						
			//			currentPic = currentSize;
						
						imgAdapt.addPic(pic);
						picGallery.setAdapter(imgAdapt);
						picView.setImageBitmap(pic);
						picView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    		
    		}
    	}
	}
}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar()
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		{
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toilet_galery, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_exit:
			onCloseButtonTapped();
	    	return true;
		case R.id.action_settings:
	        startActivity(new Intent(this, SettingsActivity.class));
			return true;
		case R.id.item_add_picture_button:
    		Intent pickIntent = new Intent();
    		pickIntent.setType("image/*");
    		pickIntent.setAction(Intent.ACTION_GET_CONTENT);
    		startActivityForResult(Intent.createChooser(pickIntent, "Select Picture"), PICKER);
    		return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    private void onCloseButtonTapped()
    {
     	Intent intent = new Intent(this, StartSessionActivity.class);
     	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);	
	}
    
	@Override
	public void retrieveToiletGaleryServiceFinish(
			IRetrieveToiletGaleryService service,
			Collection<IToiletPicture> comments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieveToiletGaleryServiceFinishWithError(
			IRetrieveToiletGaleryService service, String errorCode) {
		// TODO Auto-generated method stub
		
	}
}

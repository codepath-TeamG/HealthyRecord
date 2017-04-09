package com.example.zen.healthyrecord;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.zen.healthyrecord.fragments.DatePickerFragment;
import com.example.zen.healthyrecord.fragments.FragmentAddItemPage;
import com.example.zen.healthyrecord.fragments.FragmentAddItemPageSport;
import com.example.zen.healthyrecord.fragments.TimePickerFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by sharonyu on 2017/3/19.
 */

public class AddItemActivity extends AppCompatActivity implements FragmentAddItemPage.OnFragmentInteractionListener,
        DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {


    private DatabaseReference mDatabase;
    private StorageReference mStorageRef;
    private FragmentAddItemPage addItemPageFragment;
    private FragmentAddItemPageSport addSportItemPageFragment;

    Button btnSport;
    Button btnFood;


    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private Toolbar toolbar;
    private ImageView photoView;
    public int mState;
    public String photoFileName = "photo.jpg";
    public final String APP_TAG = "HealthyRecordApp";
    public Uri downloadUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int pos= getIntent().getExtras().getInt("POS_ID");
        Log.d("d", String.valueOf(pos));
        mStorageRef = FirebaseStorage.getInstance().getReference();


        if (savedInstanceState == null) {
            if(pos==0){
                setContentView(R.layout.add_item_layout);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragAddItem, new FragmentAddItemPage(), "FOOD").commit();
            }else{
                setContentView(R.layout.add_item_layout);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragAddItem, new FragmentAddItemPageSport(), "SPORT").commit();

            }

        }


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




    }







    public void camera(View v) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        it.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName)); // set the image file name
        startActivityForResult(it, 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
//            Bundle extras = data.getExtras();
//            Bitmap bmp = (Bitmap) extras.get("data");
//            photoView = (ImageView) findViewById(R.id.photoView);
//            photoView.setImageBitmap(bmp);

            Uri takenPhotoUri = getPhotoFileUri(photoFileName);
            // by this point we have the camera photo on disk
            Bitmap rawTakenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());

            Bitmap rotateBitmap = rotateBitmapOrientation(takenPhotoUri.getPath());

            // RESIZE BITMAP, see section below
            Bitmap resizedBitmap = BitmapScaler.scaleToFitWidth(rotateBitmap, 800);

            // Configure byte output stream
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            // Compress the image further
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            // Create a new file for the resized bitmap (`getPhotoFileUri` defined above)
            Uri resizedUri = getPhotoFileUri(photoFileName + "_resized");
            File resizedFile = new File(resizedUri.getPath());
            try {
                resizedFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(resizedFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // Write the bytes of the bitmap to file
            try {
                fos.write(bytes.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Load the taken image into a preview
            ImageView ivPreview = (ImageView) findViewById(R.id.photoView);
            ivPreview.setImageBitmap(resizedBitmap);
//            Picasso.with(this).load(takenPhotoUri).resize(75, 75).centerCrop().into(photoView);

            Random rand = new Random();

            int  n = rand.nextInt(1000) + 1;
            String fileName = "record" + n+".jpg";

            StorageReference riversRef = mStorageRef.child("images");
            StorageReference ref = riversRef.child(fileName);
            UploadTask uploadTask;
            uploadTask = ref.putFile(resizedUri);



//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            takenImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            byte[] data2 = baos.toByteArray();
//
//            uploadTask = ref.putBytes(data2);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//                    downloadUrl =  taskSnapshot.getMetadata().getDownloadUrl();
//                    SharedPreferences sharedPreferences = getSharedPreferences("photo", 0);
//                    sharedPreferences.edit()
//                            .putString("i", downloadUrl.toString()).apply();
                }
            });

            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "You didn't take photo", Toast.LENGTH_LONG).show();
        }
    }

    // Returns the Uri for a photo stored on disk given the fileName
    public Uri getPhotoFileUri(String fileName) {
        // Only continue if the SD Card is mounted
        if (isExternalStorageAvailable()) {
            // Get safe storage directory for photos
            // Use `getExternalFilesDir` on Context to access package-specific directories.
            // This way, we don't need to request external read/write runtime permissions.
            File mediaStorageDir = new File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
                Log.d(APP_TAG, "failed to create directory");
            }

            // Return the file target for the photo based on filename
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));

            // wrap File object into a content provider
            // required for API >= 24
            // See https://guides.codepath.com/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
//            return FileProvider.getUriForFile(AddItemActivity.this, "com.codepath.fileprovider", file);
        }
        return null;
    }

    // Returns true if external storage for photos is available
    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }



    public void changeFragmentFood() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragAddItem, new FragmentAddItemPage(),"FOOD");
        ft.commit();
    }

    public void changeFragmentSport() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragAddItem, new FragmentAddItemPageSport(),"SPORT");
        ft.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragAddItem, new FragmentAddItemPage());
        getFragmentManager().popBackStack();
        ft.commit();

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        TextView txtDatePicker = (TextView) findViewById(R.id.txtDatePicker);
        txtDatePicker.setText(formattedDate);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        String formattedTime = tf.format(c.getTime());
//        TextView txtTimePicker = (TextView) findViewById(R.id.txtTimePicker);
//        txtTimePicker.setText(formattedTime);
    }

    public void onSaveAction(View v){
        Toast.makeText(getApplicationContext(),"Success Save",Toast.LENGTH_LONG).show();

        v.setEnabled(false);

//        Bundle bundle = new Bundle();
//        bundle.putString("ImageDownloadUrl", downloadUrl.toString());
//
//        addItemPageFragment.setArguments(bundle);

        if (mState == 1){
            addItemPageFragment = (FragmentAddItemPage)
                    getSupportFragmentManager().findFragmentByTag("FOOD");
            addItemPageFragment.getValue();
        } else {
            addSportItemPageFragment = (FragmentAddItemPageSport)
                    getSupportFragmentManager().findFragmentByTag("SPORT");
            addSportItemPageFragment.getValue();
        }

        Intent i = new Intent(AddItemActivity.this, HomeRecordsActivity.class);
        startActivity(i);

    }

    public String getDownloadUrl() {
        if (downloadUrl == null){
            return "https://firebasestorage.googleapis.com/v0/b/healthyrecord-cf27b.appspot.com/o/images%2Ffood1.jpg?alt=media&token=e242bfb9-eef5-4e3b-ac85-4f831d52cca4";
        } else{
            return downloadUrl.toString();
        }

    }

    public String getDownloadSportUrl() {
        if (downloadUrl == null){
            return "https://firebasestorage.googleapis.com/v0/b/healthyrecord-cf27b.appspot.com/o/images%2Ftop_photo_healthy.jpg?alt=media&token=6ca9ebd1-bfe1-4f44-8704-1d4a0cb556df";
        } else{
            return downloadUrl.toString();
        }

    }

    public Bitmap rotateBitmapOrientation(String photoFilePath) {
        // Create and configure BitmapFactory
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoFilePath, bounds);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile(photoFilePath, opts);
        // Read EXIF Data
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(photoFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
        // Return result
        return rotatedBitmap;
    }




}


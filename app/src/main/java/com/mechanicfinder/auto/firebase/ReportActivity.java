package com.mechanicfinder.auto.firebase;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class ReportActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 3;
    private static final int SELECT_FILE = 2;

    //DECLARE THE FIELDS
    EditText userNameEditText,emailEditText,locationEditText,reportTitleEditText,reportDescEditText;
    ImageView reportView;
    Button submitBtn;

    //FIREBASE AUTHENTICATION FIELDS
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    //FIREBASE DATABASE FIELDS
    DatabaseReference mUserDatabase;
    StorageReference mStorageRef;

    //IMAGE HOLD URI
    Uri imageHoldUri = null;

    //PROGRESS DIALOG
    ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //ASSIGN ID'S
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        locationEditText = (EditText) findViewById(R.id.locationEditText);
        reportTitleEditText = (EditText) findViewById(R.id.reportTitleEditText);
        reportDescEditText = (EditText) findViewById(R.id.reportDescEditText);

        reportView = (ImageView) findViewById(R.id.reportView);

        submitBtn = (Button) findViewById(R.id.submitBtn);

        //ASSIGN INSTANCE TO FIREBASE AUTH
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //LOGIC CHECK USER
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if( user != null)
                {

                    finish();
                    Intent moveToHome = new Intent(ReportActivity.this, MotoristaMainActivity.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(moveToHome);

                }
            }
        };

        //Progress Dialog
        mProgress = new ProgressDialog(this);

        //FIREBASE DATABASE INSTANCE
        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("Reports");
        mStorageRef = FirebaseStorage.getInstance().getReference();


        //ONCLICK SUBMIT REPORT BTN
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //LOGIC FOR SUBMITTING REPORT
                submitReport();



            }
        });

        //REPORT IMAGEVIEW ONCLICK LISTENER
        reportView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //LOGIC FOR PICKING IMAGE
                reportPhotoSelection();
            }
        });

    }

    private void reportPhotoSelection() {
        //DISPLAY DIALOG TO CHOOSE PICTURE FROM CAMERA OR GALLERY

        final CharSequence[] items = {"Take a Picture", "Choose Photo from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this);
        builder.setTitle("Add Photo!");

        //SET ITEMS AND THEIR LISTENERS
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take a Picture")) {
                    cameraIntent();
                } else if (items[item].equals("Choose Photo from Library")) {
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    private void submitReport(){
        final String username,email,location,report_title,report_desc;

        username = userNameEditText.getText().toString().trim();
        location = locationEditText.getText().toString().trim();
        email = emailEditText.getText().toString().trim();
        report_title = reportTitleEditText.getText().toString().trim();
        report_desc = reportDescEditText.getText().toString().trim();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(location) && !TextUtils.isEmpty(report_title) && !TextUtils.isEmpty(report_desc))
        {


            if (imageHoldUri != null)
            {
                mProgress.setTitle("Submitting Report");
                mProgress.setMessage("Please wait...");
                mProgress.show();
                StorageReference mChildStorage = mStorageRef.child("Reports").child(imageHoldUri.getLastPathSegment());
                String reportPhotoUrl = imageHoldUri.getLastPathSegment();

                mChildStorage.putFile(imageHoldUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        final Uri imageUrl = taskSnapshot.getDownloadUrl();

                        mUserDatabase.child("username").setValue(username);
                        mUserDatabase.child("email").setValue(email);
                        mUserDatabase.child("location").setValue(location);
                        mUserDatabase.child("report title").setValue(report_title);
                        mUserDatabase.child("report description").setValue(report_desc);
                        mUserDatabase.child("user id").setValue(mAuth.getCurrentUser().getUid());
                        mUserDatabase.child("image url").setValue(imageUrl.toString());

                        Picasso.with(ReportActivity.this).load(imageUrl).into(reportView);
                        mProgress.dismiss();

                        finish();
                        Intent moveToHome = new Intent(ReportActivity.this, MotoristaMainActivity.class);
                        moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(moveToHome);
                    }
                });
            }
            else{
                Toast.makeText(ReportActivity.this, "Please select a profile photo", Toast.LENGTH_LONG).show();
            }
        }

        else{
            Toast.makeText(ReportActivity.this, "Please fill up all of the required fields", Toast.LENGTH_LONG).show();
        }


    }
    private void cameraIntent() {

        //CHOOSE CAMERA
        Log.d("gola", "entered here");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {

        //CHOOSE IMAGE FROM GALLERY
        Log.d("gola", "entered here");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //SAVE URI FROM GALLERY
        if(requestCode == SELECT_FILE && resultCode == RESULT_OK)
        {
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }else if ( requestCode == REQUEST_CAMERA && resultCode == RESULT_OK ){
            //SAVE URI FROM CAMERA

            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }


        //image crop library code
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageHoldUri = result.getUri();

                reportView.setImageURI(imageHoldUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }


}
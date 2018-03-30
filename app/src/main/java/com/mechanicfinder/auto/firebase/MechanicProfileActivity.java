package com.mechanicfinder.auto.firebase;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MechanicProfileActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 3;
    private static final int SELECT_FILE = 2;
    //DECLARE THE FIELDS
    EditText firstNameText, surnameText, contactNumberText, emailAddressText ;
    ImageView profilePhoto;
    Button btnChangePhoto, btnSaveProfile;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        firstNameText = (EditText) findViewById(R.id.firstNameEditText);
        surnameText = (EditText) findViewById(R.id.surnameEditText);
        contactNumberText = (EditText) findViewById(R.id.contactNumberEditText);
        emailAddressText = (EditText) findViewById(R.id.emailAddressEditText);
        profilePhoto = (ImageView) findViewById(R.id.profilePhotoImageView);
        btnChangePhoto = (Button) findViewById(R.id.btnChangePhoto);
        btnSaveProfile = (Button) findViewById(R.id.btnSaveProfile);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Logic for checking user
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    finish();
                    Intent moveToHome = new Intent(MechanicProfileActivity.this, MechanicMainActivity.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(moveToHome);
                }
            }
        };

        //Progress Dialog
        mProgress = new ProgressDialog(this);

        //Firebase Database Instance
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Mechanic").child(mAuth.getCurrentUser().getUid());
        mStorageRef = FirebaseStorage.getInstance().getReference();

        //OnClickListener Save Profile Button
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Logic for saving user profile
                saveUserProfile();
            }
        });

        //User ImageView OnClickListener
        btnChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Logic for choosing images
                profilePhotoSelection();
            }
        });
    }
    private void profilePhotoSelection() {


        //DISPLAY DIALOG TO CHOOSE PICTURE FROM CAMERA OR GALLERY

        final CharSequence[] items = {"Take a Picture", "Choose Photo from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MechanicProfileActivity.this);
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

    private void saveUserProfile(){
        final String firstname, surname, contactNumber, emailAddress;

        firstname = firstNameText.getText().toString().trim();
        surname = surnameText.getText().toString().trim();
        contactNumber = contactNumberText.getText().toString().trim();
        emailAddress = emailAddressText.getText().toString().trim();

        if (!TextUtils.isEmpty(firstname) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(contactNumber) && !TextUtils.isEmpty(emailAddress))
        {
            if (imageHoldUri != null)
            {
                mProgress.setTitle("Saving Profile");
                mProgress.setMessage("Please wait...");
                mProgress.show();
                StorageReference mChildStorage = mStorageRef.child("Mechanic Profile").child(imageHoldUri.getLastPathSegment());
                String profilePhotoUrl = imageHoldUri.getLastPathSegment();

                mChildStorage.putFile(imageHoldUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        final Uri imageUrl = taskSnapshot.getDownloadUrl();

                        mUserDatabase.child("firstname").setValue(firstname);
                        mUserDatabase.child("surname").setValue(surname);
                        mUserDatabase.child("contact number").setValue(contactNumber);
                        mUserDatabase.child("email address").setValue(emailAddress);
                        mUserDatabase.child("user id").setValue(mAuth.getCurrentUser().getUid());
                        mUserDatabase.child("image url").setValue(imageUrl.toString());

                        Picasso.with(MechanicProfileActivity.this).load(imageUrl).into(profilePhoto);
                        mProgress.dismiss();

                        finish();
                        Intent moveToHome = new Intent(MechanicProfileActivity.this, MechanicMainActivity.class);
                        moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(moveToHome);
                    }
                });
            }
            else{
                Toast.makeText(MechanicProfileActivity.this, "Please select a profile photo", Toast.LENGTH_LONG).show();
            }
        }

        else{
            Toast.makeText(MechanicProfileActivity.this, "Please fill up all of the required fields", Toast.LENGTH_LONG).show();
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

                profilePhoto.setImageURI(imageHoldUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }


}

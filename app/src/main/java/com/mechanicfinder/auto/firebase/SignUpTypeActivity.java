package com.mechanicfinder.auto.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpTypeActivity extends AppCompatActivity {

    private Button mMechanic, mVowner;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_type);
        mMechanic = (Button) findViewById(R.id.mechanic);
        mVowner = (Button) findViewById(R.id.motorista);

        mMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpTypeActivity.this, MechanicSignUpActivity.class);
                startActivity(intent);
                finish();

            }
        });

        mVowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpTypeActivity.this, MotoristaSignUpActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
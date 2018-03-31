package com.mechanicfinder.auto.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginTypeActivity extends AppCompatActivity {

    private Button mMechanic, mVowner;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_type);

        mMechanic = (Button) findViewById(R.id.mechanic);
        mVowner = (Button) findViewById(R.id.motorista);

        mMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginTypeActivity.this, MechanicLoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

        mVowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginTypeActivity.this, MotoristaLoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
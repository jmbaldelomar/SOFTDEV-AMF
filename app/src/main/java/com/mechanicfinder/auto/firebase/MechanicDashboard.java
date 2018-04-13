package com.mechanicfinder.auto.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MechanicDashboard extends AppCompatActivity implements View.OnClickListener{
    private CardView mapsCard,searchCard,profileCard,transactionCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista_dashboard);
        mapsCard = (CardView) findViewById(R.id.maps_card);
        searchCard = (CardView) findViewById(R.id.search_mechanic_card);
        profileCard = (CardView) findViewById(R.id.user_profile_card);
        transactionCard = (CardView) findViewById(R.id.transaction_card);


        //Add Click listener to the cards
        mapsCard.setOnClickListener(this);
        searchCard.setOnClickListener(this);
        profileCard.setOnClickListener(this);
        transactionCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch(view.getId()){
            case R.id.maps_card : i = new Intent(this,MapActivity.class);startActivity(i);break ;
            case R.id.search_mechanic_card : i = new Intent(this,MapActivity.class);startActivity(i);break ;
            case R.id.user_profile_card : i = new Intent(this,MechanicMainActivity.class);startActivity(i);break ;
            case R.id.transaction_card : i = new Intent(this,MapActivity.class);startActivity(i);break ;
            default:break;
        }

    }
}

package fr.eisti.android.ecobolforrestaurants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Promos extends AppCompatActivity {


    ImageView commandes;
    ImageView promos;
    ImageView stock;
    TextView commandesText;
    TextView promosText;
    TextView stockText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promos);

        stock = (ImageView)findViewById(R.id.stock);
        stockText= (TextView)findViewById(R.id.stocktext);

        commandes = (ImageView)findViewById(R.id.commandes);
        commandesText=(TextView)findViewById(R.id.commandestext);





        promos = (ImageView)findViewById(R.id.promos);
        promosText=(TextView)findViewById(R.id.promostext);
        promos.setColorFilter(Color.parseColor("#7CB342"));
        promosText.setTextColor(Color.parseColor("#7CB342"));


        commandes.setColorFilter(Color.parseColor("#605E5E"));
        commandesText.setTextColor(Color.parseColor("#605E5E"));


        stock.setColorFilter(Color.parseColor("#605E5E"));
        stockText.setTextColor(Color.parseColor("#605E5E"));


        commandes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Promos.this,Commandes.class));

            }
        });

        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Promos.this,Historique.class));

            }
        });



        //commandes.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);





    }



}
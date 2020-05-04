package fr.eisti.android.ecobolforrestaurants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class Login extends AppCompatActivity {

    Button passwordLost;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        passwordLost=(Button)findViewById(R.id.forgotpassword);
        passwordLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,PasswordLost.class));
            }
        });


        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Commandes.class));
                new HTTPTask().execute("https://beta.api.ecobol.fr/login/salemmyria@eisti.eu/12345");
            }
        });
    }
}

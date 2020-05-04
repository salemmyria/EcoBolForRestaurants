package fr.eisti.android.ecobolforrestaurants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class PasswordLost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordlost);

        final TextView email = (TextView)findViewById(R.id.logintextedit);
        final String emailString = email.toString();

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://beta.api.ecobol.fr/forgot";
                url +="/"+emailString;

                HTTPTask httpTask = new HTTPTask();
                httpTask.execute("https://beta.api.ecobol.fr/forgot/salemmyria@eisti.eu");

                try {
                    String value = new HTTPTask().execute("https://beta.api.ecobol.fr/token").get();
                    Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
                    //email.setText(value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }
        });
    }
}
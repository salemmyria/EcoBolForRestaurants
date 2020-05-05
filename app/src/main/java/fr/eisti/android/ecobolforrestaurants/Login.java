package fr.eisti.android.ecobolforrestaurants;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static fr.eisti.android.ecobolforrestaurants.Constants.API_URL;


public class Login extends AppCompatActivity {

    Button passwordLost;
    Button login;
    Button signUp;
    TextView email;
    TextView password;

    final class ResponseLoginPost
    {
        String token;
        String _id;
        String name;
        String email;
        String role;
        String verified;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final ResponseLoginPost responseLoginPost = new ResponseLoginPost();

        String urlLogin = API_URL;
        urlLogin = new StringBuilder().append(urlLogin).append("/login/").toString();
        final String finalUrlLogin = urlLogin;

        final String[] errorMsg = {new String()};


        email= (TextView)findViewById(R.id.name);
        password=(TextView)findViewById(R.id.password);

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
                AndroidNetworking.post(finalUrlLogin).addBodyParameter("email",email.getText().toString()).addBodyParameter("password",password.getText().toString()).setPriority(Priority.MEDIUM).build().getAsJSONObject(new JSONObjectRequestListener() {
                    public void onResponse(JSONObject response) {
                        try {
                            responseLoginPost.token=response.getString("token");

                            JSONObject user = response.getJSONObject("user");
                            responseLoginPost._id=user.getString("_id");
                            responseLoginPost.name = user.getString("name");
                            responseLoginPost.email = user.getString("email");
                            responseLoginPost.role = user.getString("role");
                            responseLoginPost.verified = user.getString("verified");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    public void onError(ANError error) {
                        // handle error
                        errorMsg[0] = error.getErrorBody();
                        //Toast.makeText(getApplicationContext(),errorMsg[0],Toast.LENGTH_LONG).show();
                    }
                });


                //startActivity(new Intent(Login.this,Commandes.class));
            }
        });

        signUp= (Button)findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, EmailSignUp.class));
            }
        });
    }
}

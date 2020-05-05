package fr.eisti.android.ecobolforrestaurants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import static fr.eisti.android.ecobolforrestaurants.Constants.API_URL;

public class EmailSignUp extends AppCompatActivity {

    Button signUp;
    TextView name;
    TextView email;
    TextView password;

    final class ResponseSignUpPost
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
        setContentView(R.layout.emailsignup);


        String urlRegister = API_URL;
        urlRegister = new StringBuilder().append(urlRegister).append("/register/").toString();
        final String finalUrlLogin = urlRegister;

        final ResponseSignUpPost responseSignUpPost = new ResponseSignUpPost();
        final String[] errorMsg = {new String()};

        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.mail);
        password = (TextView)findViewById(R.id.password);



        signUp=(Button)findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AndroidNetworking.post(finalUrlLogin).addBodyParameter("name",name.getText().toString()).addBodyParameter("email",email.getText().toString()).addBodyParameter("password",password.getText().toString()).setPriority(Priority.MEDIUM).build().getAsJSONObject(new JSONObjectRequestListener() {

                    public void onResponse(JSONObject response) {
                        try {
                            responseSignUpPost.token=response.getString("token");

                            JSONObject user = response.getJSONObject("user");
                            responseSignUpPost._id=user.getString("_id");
                            responseSignUpPost.name = user.getString("name");
                            responseSignUpPost.email = user.getString("email");
                            responseSignUpPost.role = user.getString("role");
                            responseSignUpPost.verified = user.getString("verified");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    public void onError(ANError error) {
                        errorMsg[0] = error.getErrorBody();
                    }
                });
                startActivity(new Intent(EmailSignUp.this,Commandes.class));
            }
        });
    }
}
package fr.eisti.android.ecobolforrestaurants;

import android.os.Bundle;
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

import static fr.eisti.android.ecobolforrestaurants.Constants.API_URL;

public class PasswordLost extends AppCompatActivity {

    final class ResponseForgotPost
    {
        String msg;
        String email;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String urlFrogot = API_URL;
        urlFrogot = new StringBuilder().append(urlFrogot).append("/forgot/").toString();
        final String finalUrlForgot = urlFrogot;

        final ResponseForgotPost responseForgotPost = new ResponseForgotPost();
        final String[] errorMsg = {new String()};


        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordlost);
        AndroidNetworking.initialize(getApplicationContext());


        final TextView email = (TextView)findViewById(R.id.name);


        Button submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(finalUrlForgot).addBodyParameter("email",email.getText().toString()).setPriority(Priority.MEDIUM).build().getAsJSONObject(new JSONObjectRequestListener() {

                    public void onResponse(JSONObject response) {
                        try {
                            responseForgotPost.msg= response.getString("msg");
                            responseForgotPost.email = response.getString("email");
                            Toast.makeText(getApplicationContext(),responseForgotPost.msg,Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    public void onError(ANError error) {
                        errorMsg[0] = error.getErrorBody();
                        Toast.makeText(getApplicationContext(),errorMsg[0],Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }
}
package com.easytask.easytask.frontend.views;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.easytask.easytask.R;


/**
 * Created by Silas on 27-09-2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    Button mainLoginBtn;
    Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Hides the actionbar on the login screen. This needs to be called before setContentView */
        ActionBar actionBar =  getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_login);


        /* Instantiate views*/
        mainLoginBtn = (Button)findViewById(R.id.main_login_button);
        signupBtn = (Button)findViewById(R.id.signup_button);

        /* Assign click handlers to buttons */
        mainLoginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        if(v == mainLoginBtn)
        {
            Toast.makeText(this, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (v == signupBtn)
        {
            Toast.makeText(this, "Signup", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.easytask.easytask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    Button mainLoginBtn;
    Button signupBtn;
    Button createTaskLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Instantiate views*/
        mainLoginBtn = (Button)findViewById(R.id.main_login_button);
        signupBtn = (Button)findViewById(R.id.signup_button);
        createTaskLoginBtn = (Button)findViewById(R.id.create_task_login_button);

        /* Assign click handlers to buttons */
        mainLoginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        createTaskLoginBtn.setOnClickListener(this);


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
        else if(v == createTaskLoginBtn)
        {
            Toast.makeText(this, "create task", Toast.LENGTH_SHORT).show();
        }
    }
}

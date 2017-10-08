package com.easytask.easytask.frontend.views;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.easytask.easytask.R;
import com.easytask.easytask.util.Validator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by Silas on 27-09-2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static Button mainLoginBtn;
    private Button signupBtn;
    private EditText usernameTextbox, passwordTextbox;
    private FirebaseAuth firebaseAuth;

    private Validator validator;

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
        usernameTextbox = (EditText)findViewById(R.id.username_textbox);
        passwordTextbox = (EditText)findViewById(R.id.password_textbox);


        validator = new Validator();
        firebaseAuth = FirebaseAuth.getInstance();


        /* Assign click handlers to buttons */
        mainLoginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        if(v == mainLoginBtn)
        {


            String email = usernameTextbox.getText().toString();
            String password = passwordTextbox.getText().toString();

            if(email.isEmpty() || password.isEmpty())
            {
                Toast.makeText(this, "You need to fill out all fields", Toast.LENGTH_SHORT).show();
            }
            else
            {
                /* Validate both username and password */
                if(validator.checkEmail(email) && validator.checkPassword(password))
                {
                    Toast.makeText(this, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();

                    Intent mainscreen = new Intent(this, MainActivity.class);
                    startActivity(mainscreen);
                }
                else
                {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
//


        }
        else if (v == signupBtn)
        {

            Toast.makeText(this, "Signup", Toast.LENGTH_SHORT).show();


            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            ft.add(R.id.fragment_container_login, new CreateUserFragment());
            ft.addToBackStack(null);
            ft.commit();

            mainLoginBtn.setEnabled(false);


        }
    }
}

package com.easytask.easytask.frontend.views;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.easytask.easytask.R;
import com.easytask.easytask.util.Validator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by Silas on 27-09-2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

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

        firebaseAuth = FirebaseAuth.getInstance();

        /* Instantiate views*/
        mainLoginBtn = (Button)findViewById(R.id.main_login_button);
        signupBtn = (Button)findViewById(R.id.signup_button);
        usernameTextbox = (EditText)findViewById(R.id.username_textbox);
        passwordTextbox = (EditText)findViewById(R.id.password_textbox);
        usernameTextbox.setText("test@test.com");
        passwordTextbox.setText("test12345");

        validator = new Validator();
        firebaseAuth = FirebaseAuth.getInstance();


        /* Assign click handlers to buttons */
        mainLoginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        Toast.makeText(this, currentUser.toString(), Toast.LENGTH_SHORT).show();
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

                    // TODO: create asynctask to show progress dialog while trying to authorize

                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(LoginActivity.this, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();

                                        Intent mainscreen = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(mainscreen);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                else
                {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }


        }
        else if (v == signupBtn)
        {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            ft.add(R.id.fragment_container_login, new CreateUserFragment());
            ft.addToBackStack(null);
            ft.commit();

            mainLoginBtn.setEnabled(false);


        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        /* Hides keyboard whenever user touches outside of edittext */

        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}

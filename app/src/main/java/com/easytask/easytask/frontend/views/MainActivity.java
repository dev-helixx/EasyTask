package com.easytask.easytask.frontend.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.easytask.easytask.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Silas on 27-09-2017.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private TextView user_name, user_email;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private boolean isTaskCreator;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Alle Opgaver");

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();


        /* Checks if user is a task creator or just task do'er*/
        checkUser();



        /* Inflates cardview fragment*/
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
        ft.replace(R.id.fragment_container_main, new FindTasksFragment());
        ft.commit();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

       addUserInfoToNavHeader();




    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            Fragment ctf = getSupportFragmentManager().findFragmentByTag("createtaskfragment");
            Fragment mpf = getSupportFragmentManager().findFragmentByTag("myprofilfragment");
            Fragment ftf = getSupportFragmentManager().findFragmentByTag("findtasksfragment");
            Fragment af = getSupportFragmentManager().findFragmentByTag("aboutfragment");

            if (ctf != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out)
                        .remove(ctf)
                        .commit();
            }
            else if( mpf != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out)
                        .remove(ftf)
                        .commit();
            }
            else if( ftf != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out)
                        .remove(ftf)
                        .commit();
            }
            else if( af != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out)
                        .remove(af)
                        .commit();
            }else {
                super.onBackPressed();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem createTaskActionButton = menu.findItem(R.id.action_addTask);

        if(!isTaskCreator) {
            createTaskActionButton.setVisible(false);
        }else {
            createTaskActionButton.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addTask) {

            Fragment ctf = getSupportFragmentManager().findFragmentByTag("createtaskfragment");

            if(ctf == null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
                ft.add(R.id.fragment_container_main, new CreateTaskFragment(), "createtaskfragment");
                ft.commit();
            }


        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_findtasks) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            ft.replace(R.id.fragment_container_main, new FindTasksFragment(), "findtasksfragment");
            ft.commit();

        } else if (id == R.id.nav_myprofile) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            ft.replace(R.id.fragment_container_main, new MyProfilFragment(), "myprofilfragment");
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_mytasks) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            ft.replace(R.id.fragment_container_main, new MyTasksFragment(), "findtasksfragment");
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_about) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            ft.replace(R.id.fragment_container_main, new AboutFragment(), "aboutfragment");
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_logout) {
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void checkUser() {
        if(firebaseAuth.getCurrentUser() == null) {
            this.finish();
            startActivity(new Intent(this, LoginActivity.class));
        }else {

            database.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("isTaskCreator").addListenerForSingleValueEvent(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot snap) {

                    isTaskCreator = (boolean)snap.getValue();

                    // Calls onCreateOptionsMenu and hides create button if user is not creator
                    invalidateOptionsMenu();

                    // Hides "My Tasks" in navigation drawer if user is not creator
                    if(!isTaskCreator) {
                        navigationView = (NavigationView) findViewById(R.id.nav_view);
                        Menu nav_Menu = navigationView.getMenu();
                        nav_Menu.findItem(R.id.nav_mytasks).setVisible(false);
                    }


                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void addUserInfoToNavHeader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        user_name = (TextView) headerView.findViewById(R.id.nav_header_name_label);
        user_email = (TextView) headerView.findViewById(R.id.nav_header_email_label);
        user_email.setText(firebaseAuth.getCurrentUser().getEmail());

        database.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snap) {
                   user_name.setText(snap.getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        navigationView.setNavigationItemSelectedListener(this);

    }


}

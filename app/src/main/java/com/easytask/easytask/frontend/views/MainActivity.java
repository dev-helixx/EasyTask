package com.easytask.easytask.frontend.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.easytask.easytask.R;
import com.easytask.easytask.frontend.controllers.Task;
import com.easytask.easytask.frontend.controllers.RVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silas on 27-09-2017.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            Fragment ctf = getSupportFragmentManager().findFragmentByTag("createtaskfragment");

            if (ctf != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out)
                        .remove(ctf)
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

        } else if (id == R.id.nav_mytasks) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            ft.replace(R.id.fragment_container_main, new MyTasksFragment());
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_about) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            ft.replace(R.id.fragment_container_main, new AboutFragment());
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_logout) {
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

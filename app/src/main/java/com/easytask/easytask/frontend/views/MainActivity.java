package com.easytask.easytask.frontend.views;

import android.os.Bundle;
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

    private RecyclerView recyclerView;
    private List<Task> tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Initializes data to be displayed on the cards */
        initializeCardViewData();


        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(tasks);
        recyclerView.setAdapter(adapter);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    // This method creates an ArrayList that has four Task objects
    private void initializeCardViewData(){

        tasks = new ArrayList<>();

        tasks.add(new Task("Slå græs", "Jeg skal have slået min græsplæne"));
        tasks.add(new Task("Vaske bil", "Min bil skal vaskes!"));
        tasks.add(new Task("Vaske terasse", "Skal vaskes hurtigt, er meget beskidt"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            Toast.makeText(this, "Add something", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_logout) {
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.example.benjaminsalamon.msoplaner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import layout.LessonFragment;

public class ExamsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Logic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExamsActivity.this, NewExamActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.nav_exams);
        item.setChecked(true);

        LessonFragment last = LessonFragment.newInstance(15);
        LinearLayout ll = (LinearLayout) findViewById(R.id.content_exams);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.disallowAddToBackStack();
        ft.add(R.id.content_exams, LessonFragment.newInstance(12), "first");
        ft.add(R.id.content_exams, LessonFragment.newInstance(13), "second");
        ft.add(R.id.content_exams, LessonFragment.newInstance(14), "third");
        ft.add(R.id.content_exams, last, "fourth");
        //ft.hide(last);
        ft.commit();

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
        getMenuInflater().inflate(R.menu.exams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            Intent intent = new Intent(ExamsActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (id == R.id.nav_homework) {
            Intent intent = new Intent(ExamsActivity.this, HomeworkActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        else if (id == R.id.nav_timetable) {
            Intent intent = new Intent(ExamsActivity.this, TimeTableActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
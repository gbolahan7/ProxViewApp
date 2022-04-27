package uk.ac.tees.b1110843.proxviewapp.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.databinding.ActivityMainBinding;
import uk.ac.tees.b1110843.proxviewapp.databinding.NavdrawerLayoutBinding;
import uk.ac.tees.b1110843.proxviewapp.databinding.ToolbarLayoutBinding;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private NavdrawerLayoutBinding navDrawerLayoutBinding;
    private ToolbarLayoutBinding toolbarLayoutBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        navDrawerLayoutBinding=NavdrawerLayoutBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main2);
        uk.ac.tees.b1110843.proxviewapp.databinding.ActivityMainBinding activityMainBinding = navDrawerLayoutBinding.mainActivity;
        toolbarLayoutBinding= activityMainBinding.toolbar;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                navDrawerLayoutBinding.NavDrawer,
                toolbarLayoutBinding.toolbar,
                R.string.open_NavDrawer,
                R.string.close_NavDrawer
        );
    }
}
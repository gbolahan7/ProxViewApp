package uk.ac.tees.b1110843.proxviewapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.UserModel;
import uk.ac.tees.b1110843.proxviewapp.databinding.ActivityMainBinding;
import uk.ac.tees.b1110843.proxviewapp.databinding.NavdrawerLayoutBinding;
import uk.ac.tees.b1110843.proxviewapp.databinding.ToolbarLayoutBinding;

import de.hdodenhof.circleimageview.CircleImageView;
import com.google.android.material.navigation.NavigationView;
//import Android.App;




public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
//public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding activityMainBinding;
    private NavdrawerLayoutBinding navDrawerLayoutBinding;
    private ToolbarLayoutBinding toolbarLayoutBinding;
    private CircleImageView imgHeader;
    //    private FirebaseDatabase firebaseAuth;
    private FirebaseAuth firebaseAuth;
    private TextView textName, textEmail;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navDrawerLayoutBinding=NavdrawerLayoutBinding.inflate(getLayoutInflater());
        setContentView(navDrawerLayoutBinding.getRoot());
        uk.ac.tees.b1110843.proxviewapp.databinding.ActivityMainBinding activityMainBinding = navDrawerLayoutBinding.mainActivity;
        toolbarLayoutBinding= activityMainBinding.toolbar;

//        setSupportActionBar(toolbarLayoutBinding.toolbar);
        firebaseAuth = FirebaseAuth.getInstance();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                navDrawerLayoutBinding.NavDrawer,
                toolbarLayoutBinding.toolbar,
                R.string.open_NavDrawer,
                R.string.close_NavDrawer
        );

        navDrawerLayoutBinding.NavDrawer.addDrawerListener(toggle);
        toggle.syncState();

//        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.buttonAbout).setDrawerLayout(drawer).build()

        //connect the drawer layout with nav graph
        NavController navController= Navigation.findNavController(this,R.id.fragmentContainer);
//        navigationView.setNavigationItemSelectedListener(this);
        NavigationUI.setupWithNavController(
                navDrawerLayoutBinding.NavView,
                navController

        );
        View headerLayout=navDrawerLayoutBinding.NavView.getHeaderView(0);
        textName=headerLayout.findViewById(R.id.HeaderName);
        textEmail=headerLayout.findViewById(R.id.HeaderEmail);

        getUserData();

        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.buttonHelp:
                    Toast.makeText(MainActivity.this, "Help", Toast.LENGTH_SHORT).show();
//                    Log.d("TAG", "Help");

                    break;
                case R.id.buttonAbout:
                    Toast.makeText(MainActivity.this, "About App", Toast.LENGTH_SHORT).show();
                    break;
            }
            navDrawerLayoutBinding.NavDrawer.closeDrawer(GravityCompat.START);
            return true;
        }

    @Override
    public void onBackPressed() {
        if(navDrawerLayoutBinding.NavDrawer.isDrawerOpen(GravityCompat.START))
            navDrawerLayoutBinding.NavDrawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void getUserData(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                if(snapshot.exists()){
                    UserModel userModel=snapshot.getValue(UserModel.class);
//                    Glide.with(MainActivity.this).load(userModel.getImage()).into(imgHeader);
                    textName.setText(userModel.getUsername());
                    textEmail.setText(userModel.getEmail());


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }
        });
    }


}
package uk.ac.tees.b1110843.proxviewapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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


public class MainActivity extends AppCompatActivity {

    private NavdrawerLayoutBinding navDrawerLayoutBinding;
    private ToolbarLayoutBinding toolbarLayoutBinding;
//    private FirebaseDatabase firebaseAuth;
    private FirebaseAuth firebaseAuth;
    private TextView textName, textEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navDrawerLayoutBinding=NavdrawerLayoutBinding.inflate(getLayoutInflater());
        setContentView(navDrawerLayoutBinding.getRoot());
        uk.ac.tees.b1110843.proxviewapp.databinding.ActivityMainBinding activityMainBinding = navDrawerLayoutBinding.mainActivity;
        toolbarLayoutBinding= activityMainBinding.toolbar;

        setSupportActionBar(toolbarLayoutBinding.toolbar);

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

        //connect the drawer layout with navgraph
        NavController navController= Navigation.findNavController(this,R.id.fragmentContainer);
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
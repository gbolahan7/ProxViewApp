package uk.ac.tees.b1110843.proxviewapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.databinding.ActivityForgetBinding;

public class ForgetActivity extends AppCompatActivity {
    
    private ActivityForgetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityForgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        binding.buttonBack.setOnClickListener(View-> {
            onBackPressed();
        });
    }
}
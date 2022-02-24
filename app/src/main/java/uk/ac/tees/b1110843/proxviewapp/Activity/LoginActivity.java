package uk.ac.tees.b1110843.proxviewapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonRegister.setOnClickListener(view->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
        binding.textForgetPass.setOnClickListener(view->{
            startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
        });
    }
}
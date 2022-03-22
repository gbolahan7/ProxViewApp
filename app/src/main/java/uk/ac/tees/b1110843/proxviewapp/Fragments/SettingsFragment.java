package uk.ac.tees.b1110843.proxviewapp.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getActivity());


        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Settings");
        binding.EmailText.setText(firebaseAuth.getCurrentUser().getEmail());
        binding.usernameText.setText(firebaseAuth.getCurrentUser().getDisplayName());

//        Glide.with(requireContext()).load(firebaseAuth.getCurrentUser().getPhotoUrl().into(binding.imgProfile));

        if(firebaseAuth.getCurrentUser().isEmailVerified()){
            binding.MailText.setVisibility(View.GONE);
        }else{
            binding.MailText.setVisibility(View.VISIBLE);
        }

        //send verification email on click
        binding.MailText.setOnClickListener(verify->{
            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>(){
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getContext(), "Email verifiction sent", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onComplete: user email " + task.getException());
                    }
                }
            });
        });
    }
}
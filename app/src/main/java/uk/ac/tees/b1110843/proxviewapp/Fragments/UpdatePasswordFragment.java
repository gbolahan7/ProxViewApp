package uk.ac.tees.b1110843.proxviewapp.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.databinding.FragmentUpdatePasswordBinding;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link UpdatePasswordFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class UpdatePasswordFragment extends Fragment {


    private FragmentUpdatePasswordBinding binding;
    private ProgressDialog progressDialog;
    private String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdatePasswordBinding.inflate(inflater, container, false);
        progressDialog = new ProgressDialog(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.updatePasswordButton.setOnClickListener(update -> {
            if (areFieldReady()) {
                progressDialog.show();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(requireContext(), "Password is updated", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(update).popBackStack();
                        } else {
                            progressDialog.dismiss();
                            Log.d("TAG", "onComplete: " + task.getException());
                            Toast.makeText(requireContext(), "Error : " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }


    private boolean areFieldReady() {


        password = binding.userEditPassword.getText().toString().trim();

        boolean flag = false;
        View requestView = null;

        if (password.isEmpty()) {
            binding.userEditPassword.setError("Field is required");
            flag = true;
            requestView = binding.userEditPassword;
        } else if (password.length() < 8) {
            binding.userEditPassword.setError("Minimum 8 characters");
            flag = true;
            requestView = binding.userEditPassword;
        }

        if (flag) {
            requestView.requestFocus();
            return false;
        } else {
            return true;
        }

    }
}
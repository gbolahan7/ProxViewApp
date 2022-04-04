package uk.ac.tees.b1110843.proxviewapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.chip.Chip;

import uk.ac.tees.b1110843.proxviewapp.Constant.Constants;
import uk.ac.tees.b1110843.proxviewapp.LocationModel;
import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private FragmentHomeBinding binding;
    private GoogleMap mGoogleMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapDefault);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        for (LocationModel locationModel: Constants.locationsName){
           // create a chip instance
            Chip chip = new Chip(requireContext());
            chip.setId(locationModel.getId());
            chip.setText(locationModel.getName());
            chip.setPadding(7,7,7,7);
            chip.setTextColor(getResources().getColor(R.color.white, null));
            chip.setChipBackgroundColor(getResources().getColorStateList(R.color.primaryColor, null));
            chip.setCheckedIconVisible(false);
            chip.setCheckable(true);
            chip.setChipIcon(ResourcesCompat.getDrawable(getResources(), locationModel.getDrawableId(),null));

            binding.locationGroup.addView(chip);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }
}
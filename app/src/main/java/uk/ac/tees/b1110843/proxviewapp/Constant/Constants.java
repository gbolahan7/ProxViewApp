package uk.ac.tees.b1110843.proxviewapp.Constant;

import uk.ac.tees.b1110843.proxviewapp.LocationModel;
import uk.ac.tees.b1110843.proxviewapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public interface Constants {

    int STORAGE_REQUEST_CODE = 1000;
    int LOCATION_REQUEST_CODE = 2000;
    String IMAGE_PATH = "/Profile/";

// list of locations that can be viewed
    ArrayList<LocationModel> locationsName = new ArrayList<>(
            Arrays.asList(
                    new LocationModel(1, R.drawable.ic_eatery, "Restaurant", "restaurant"),
                    new LocationModel(2, R.drawable.ic_store, "Store", "store"),
                    new LocationModel(3, R.drawable.ic_school, "School", "school"),
                    new LocationModel(4, R.drawable.ic_clinic, "Clinics", "clinic")


                    )
    );
}

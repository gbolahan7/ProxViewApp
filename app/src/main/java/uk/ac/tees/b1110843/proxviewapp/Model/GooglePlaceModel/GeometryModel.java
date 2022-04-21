package uk.ac.tees.b1110843.proxviewapp.Model.GooglePlaceModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class GeometryModel {

    @SerializedName("location")
    @Expose
    private LocationModel location;

    public LocationModel getLocation() {
        return location;
    } // this model returns the lat and long of a location

    public void setLocation(LocationModel location) {
        this.location = location;
    }
}

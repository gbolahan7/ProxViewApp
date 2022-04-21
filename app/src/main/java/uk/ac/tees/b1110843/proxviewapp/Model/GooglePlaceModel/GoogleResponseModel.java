package uk.ac.tees.b1110843.proxviewapp.Model.GooglePlaceModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import uk.ac.tees.b1110843.proxviewapp.GooglePlaceModel;

// All the locations searched for would be returned
public class GoogleResponseModel {

    @SerializedName("results")
    @Expose
    private List<GooglePlaceModel> googlePlaceModelList;

    @SerializedName("error_message")
    @Expose
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<GooglePlaceModel> getGooglePlaceModelList() {
        return googlePlaceModelList;
    }

    public void setGooglePlaceModelList(List<GooglePlaceModel> googlePlaceModelList) {
        this.googlePlaceModelList = googlePlaceModelList;
    }
}

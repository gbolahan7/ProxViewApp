package uk.ac.tees.b1110843.proxviewapp.WebServices;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import uk.ac.tees.b1110843.proxviewapp.Model.GooglePlaceModel.GoogleResponseModel;

public interface RetrofitAPI {
//
    @GET
    Call<GoogleResponseModel> getNearByPlaces(@Url String url);

//    @GET
//    Call<DirectionResponseModel> getDirection(@Url String url);
}

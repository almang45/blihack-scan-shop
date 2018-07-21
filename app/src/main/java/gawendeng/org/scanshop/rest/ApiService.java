package gawendeng.org.scanshop.rest;

import gawendeng.org.scanshop.request.CartRequest;
import gawendeng.org.scanshop.response.DataResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
  @Headers({
      "Accept: application/json",
      "Content-Type: application/json",
      "Authorization: bearer FBD19438-DE87-42EC-899D-B0BD4B1FE57E"
  })
  @POST("backend/retail/carts")
  Call<DataResponse> addToCart(@Body CartRequest cartRequest);

  @Headers({
      "Accept: application/json",
      "Content-Type: application/json",
      "Authorization: bearer FBD19438-DE87-42EC-899D-B0BD4B1FE57E"
  })
  @GET("backend/retail/carts")
  Call<DataResponse> getCart();

  @Headers({
      "Accept: application/json",
      "Content-Type: application/json",
      "Authorization: bearer FBD19438-DE87-42EC-899D-B0BD4B1FE57E"
  })
  @PUT("backend/retail/carts")
  Call<DataResponse> updateCart(@Body CartRequest cartRequest);
}

package gawendeng.org.scanshop.rest;

import gawendeng.org.scanshop.request.AddToCartRequest;
import gawendeng.org.scanshop.response.DataResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
  @Headers({
      "Accept: application/json",
      "Content-Type: application/json",
      "Authorization: bearer ACD6554E-2A32-4D98-82D7-40E1CBE67B27"
  })
  @POST("backend/retail/carts")
  Call<DataResponse> addToCart(@Body AddToCartRequest addToCartRequest);

  @Headers({
      "Accept: application/json",
      "Content-Type: application/json",
      "Authorization: bearer ACD6554E-2A32-4D98-82D7-40E1CBE67B27"
  })
  @GET("backend/retail/carts")
  Call<DataResponse> getCart();
}

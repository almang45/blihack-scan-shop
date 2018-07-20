package gawendeng.org.scanshop.rest;

public class ApiUtils {
  private ApiUtils() {}

  public static final String BASE_URL = "https://www.blibli.com/";

  public static ApiService getApiService() {
    return ApiClient.getClient(BASE_URL).create(ApiService.class);
  }
}

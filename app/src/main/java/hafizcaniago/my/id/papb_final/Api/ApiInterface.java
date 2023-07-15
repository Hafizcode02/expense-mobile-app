package hafizcaniago.my.id.papb_final.Api;

import hafizcaniago.my.id.papb_final.Data.Body.BodyLogin;
import hafizcaniago.my.id.papb_final.Data.Body.BodyRegister;
import hafizcaniago.my.id.papb_final.Data.Response.Login.LoginResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Register.RegisterResponse;
import hafizcaniago.my.id.papb_final.Data.Response.User.ShowUserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("api/index.php?route=login")
    Call<LoginResponse> postLogin(@Body BodyLogin bodyLogin);

    @POST("api/index.php?route=users")
    Call<RegisterResponse> postRegister(@Body BodyRegister bodyRegister);

    @GET("api/index.php?route=users")
    Call<ShowUserResponse> getUserData(@Query("id") String userID);
}

package hafizcaniago.my.id.papb_final.Api;

import hafizcaniago.my.id.papb_final.Data.Body.BodyLogin;
import hafizcaniago.my.id.papb_final.Data.Body.BodyRegister;
import hafizcaniago.my.id.papb_final.Data.Response.Login.LoginResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Register.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("api/index.php?route=login")
    Call<LoginResponse> postLogin(@Body BodyLogin bodyLogin);

    @POST("api/index.php?route=users")
    Call<RegisterResponse> postRegister(@Body BodyRegister bodyRegister);
}

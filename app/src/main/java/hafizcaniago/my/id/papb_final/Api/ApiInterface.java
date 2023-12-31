package hafizcaniago.my.id.papb_final.Api;

import hafizcaniago.my.id.papb_final.Data.Body.BodyLogin;
import hafizcaniago.my.id.papb_final.Data.Body.BodyPostExpenseData;
import hafizcaniago.my.id.papb_final.Data.Body.BodyRegister;
import hafizcaniago.my.id.papb_final.Data.Body.BodyUpdateExpense;
import hafizcaniago.my.id.papb_final.Data.Body.BodyUpdateUser;
import hafizcaniago.my.id.papb_final.Data.Response.Expense.DeleteExpenseResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Expense.PostExpenseResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Expense.ShowExpenseResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Expense.UpdateExpenseResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Expenses.AllExpenseResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Expenses.TotalExpenseResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Login.LoginResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Register.RegisterResponse;
import hafizcaniago.my.id.papb_final.Data.Response.User.ShowUserResponse;
import hafizcaniago.my.id.papb_final.Data.Response.User.UpdateUserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("api/index.php?route=login")
    Call<LoginResponse> postLogin(@Body BodyLogin bodyLogin);

    @POST("api/index.php?route=users")
    Call<RegisterResponse> postRegister(@Body BodyRegister bodyRegister);

    @GET("api/index.php?route=users")
    Call<ShowUserResponse> getUserData(@Query("id") String userID);

    @PUT("api/index.php?route=users")
    Call<UpdateUserResponse> updateUser(@Body BodyUpdateUser bodyUpdateUser, @Query("id") String userID);

    @GET("api/index.php?route=expenses/count")
    Call<TotalExpenseResponse> getAllExpenseCount(@Query("id") String userID);

    @GET("api/index.php?route=expenses")
    Call<AllExpenseResponse> getAllExpenseItem(@Query("id") String userID);

    @POST("api/index.php?route=expense")
    Call<PostExpenseResponse> postExpense(@Body BodyPostExpenseData bodyPostExpenseData);

    @GET("api/index.php?route=expense")
    Call<ShowExpenseResponse> getExpenseByID(@Query("id") String expenseID);

    @PUT("api/index.php?route=expense")
    Call<UpdateExpenseResponse> updateExpenseByID(@Body BodyUpdateExpense bodyUpdateExpense, @Query("id") String expenseID);

    @DELETE("api/index.php?route=expense")
    Call<DeleteExpenseResponse> deleteExpenseByID(@Query("id") String expenseID);
}

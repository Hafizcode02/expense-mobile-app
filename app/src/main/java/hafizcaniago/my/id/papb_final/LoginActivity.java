package hafizcaniago.my.id.papb_final;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import hafizcaniago.my.id.papb_final.Api.RestClient;
import hafizcaniago.my.id.papb_final.Data.Body.BodyLogin;
import hafizcaniago.my.id.papb_final.Data.Response.Login.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnToRegister;
    Button btnToLogin;

    TextInputEditText edtEmail;
    TextInputEditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpLoginProcess();
        moveToRegisterActivity();
    }

    private void setUpLoginProcess() {
        btnToLogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnToLogin.setOnClickListener(view -> {
            BodyLogin bodyLogin = new BodyLogin();
            bodyLogin.setEmail(edtEmail.getText().toString());
            bodyLogin.setPassword(edtPassword.getText().toString());

            RestClient.getService().postLogin(bodyLogin).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("Response", response.body().toString());
                    if(response.body().getMessage().equals("Login Berhasil")){
                        Intent moveActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(moveActivity);
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void moveToRegisterActivity() {
        btnToRegister = findViewById(R.id.btnRegister);
        btnToRegister.setOnClickListener(view -> {
            Intent moveActivity = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(moveActivity);
        });
    }
}
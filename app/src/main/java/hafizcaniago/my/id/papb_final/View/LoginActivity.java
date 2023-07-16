package hafizcaniago.my.id.papb_final.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import hafizcaniago.my.id.papb_final.Api.RestClient;
import hafizcaniago.my.id.papb_final.Data.Body.BodyLogin;
import hafizcaniago.my.id.papb_final.Data.Response.Login.LoginResponse;
import hafizcaniago.my.id.papb_final.R;
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
            bodyLogin.setEmail(Objects.requireNonNull(edtEmail.getText()).toString());
            bodyLogin.setPassword(Objects.requireNonNull(edtPassword.getText()).toString());

            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = getSharedPreferences("USER_DATA", MODE_PRIVATE).edit();

            RestClient.getService().postLogin(bodyLogin).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    assert response.body() != null;
                    String toastMessage = response.body().getMessage().equals("Login Berhasil") ? "Login Success" : "Login Failed";
                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    Log.i("Response", response.body().toString());
                    if (response.body().getMessage().equals("Login Berhasil")) {
                        editor.putString("USER_ID", response.body().getData().getId());
                        editor.putString("USER_FULLNAME", response.body().getData().getFullname());
                        editor.apply();
                        Intent moveActivity = new Intent(getApplicationContext(), MainActivity.class);
                        moveActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(moveActivity);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {

                }
            });
        });
    }

    private void moveToRegisterActivity() {
        btnToRegister = findViewById(R.id.btnRegister);
        btnToRegister.setOnClickListener(view -> {
            Intent moveActivity = new Intent(getApplicationContext(), RegisterActivity.class);
            moveActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(moveActivity);
            finish();
        });
    }
}
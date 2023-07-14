package hafizcaniago.my.id.papb_final.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import hafizcaniago.my.id.papb_final.Api.RestClient;
import hafizcaniago.my.id.papb_final.Data.Body.BodyRegister;
import hafizcaniago.my.id.papb_final.Data.Response.Register.RegisterResponse;
import hafizcaniago.my.id.papb_final.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btnToLogin;
    Button btnToRegister;
    TextInputEditText edtFullname;
    TextInputEditText edtEmail;
    TextInputEditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        moveToLoginActivity();
        setupRegisterProgress();
    }

    private void setupRegisterProgress() {
        btnToRegister = findViewById(R.id.btnRegister);
        edtFullname = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnToRegister.setOnClickListener(view -> {
            BodyRegister bodyRegister = new BodyRegister();
            bodyRegister.setFullname(Objects.requireNonNull(edtFullname.getText()).toString());
            bodyRegister.setEmail(Objects.requireNonNull(edtEmail.getText()).toString());
            bodyRegister.setPassword(Objects.requireNonNull(edtPassword.getText()).toString());

            RestClient.getService().postRegister(bodyRegister).enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                    assert response.body() != null;
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("Response", response.body().toString());
                    if (response.body().getMessage().equals("User Created Successfully")) {
                        Intent moveActivity = new Intent(getApplicationContext(), LoginActivity.class);
                        moveActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(moveActivity);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failed to Create Account", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }


    private void moveToLoginActivity() {
        btnToLogin = findViewById(R.id.btnLogin);
        btnToLogin.setOnClickListener(view -> {
            Intent moveActivity = new Intent(getApplicationContext(), LoginActivity.class);
            moveActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(moveActivity);
            finish();
        });
    }

}
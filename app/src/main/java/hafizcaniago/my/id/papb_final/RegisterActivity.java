package hafizcaniago.my.id.papb_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    Button btnToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnToLogin = findViewById(R.id.btnLogin);
        btnToLogin.setOnClickListener(view -> {
            Intent moveActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(moveActivity);
        });
    }
}
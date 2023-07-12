package hafizcaniago.my.id.papb_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnToRegister = findViewById(R.id.btnRegister);
        btnToRegister.setOnClickListener(view -> {
            Intent moveActivity = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(moveActivity);
        });
    }
}
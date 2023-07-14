package hafizcaniago.my.id.papb_final.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import hafizcaniago.my.id.papb_final.R;

public class MainActivity extends AppCompatActivity {

    ImageButton btnToWebView;
    ImageButton btnToProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToWebView = findViewById(R.id.btnToWebView);
        btnToProfile = findViewById(R.id.btnToProfile);

        btnToWebView.setOnClickListener(view -> {
            Intent moveActivity = new Intent(getApplicationContext(), WebViewActivity.class);
            startActivity(moveActivity);
        });

        btnToProfile.setOnClickListener(view -> {
            Intent moveActivity = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(moveActivity);
        });
    }
}
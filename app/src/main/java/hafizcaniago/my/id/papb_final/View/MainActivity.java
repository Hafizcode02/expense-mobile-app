package hafizcaniago.my.id.papb_final.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import hafizcaniago.my.id.papb_final.Api.RestClient;
import hafizcaniago.my.id.papb_final.Data.Response.Expenses.AllExpenseResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Expenses.DataItem;
import hafizcaniago.my.id.papb_final.Data.Response.Expenses.TotalExpenseResponse;
import hafizcaniago.my.id.papb_final.Helper.Helper;
import hafizcaniago.my.id.papb_final.R;
import hafizcaniago.my.id.papb_final.View.Adapter.ExpenseAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageButton btnToWebView;
    ImageButton btnToProfile;
    FloatingActionButton fab_add;
    TextView yourExpenseTotal;


    String USER_ID;
    String USER_FULLNAME;


    RecyclerView recyclerView;
    ExpenseAdapter adapter;
    List<DataItem> expenseItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        USER_ID = prefs.getString("USER_ID", "111111");
        USER_FULLNAME = prefs.getString("USER_FULLNAME", "HAFIZ");

        btnToWebView = findViewById(R.id.btnToWebView);
        btnToProfile = findViewById(R.id.btnToProfile);
        fab_add = findViewById(R.id.fab_add);

        btnToWebView.setOnClickListener(view -> {
            Intent moveActivity = new Intent(getApplicationContext(), WebViewActivity.class);
            startActivity(moveActivity);
        });

        btnToProfile.setOnClickListener(view -> {
            Intent moveActivity = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(moveActivity);
        });

        fab_add.setOnClickListener(view -> {
            Intent moveActivity = new Intent(getApplicationContext(), ManageExpenseData.class);
            moveActivity.putExtra("ACTION", "ADD");
            startActivity(moveActivity);
        });

        setupTotalExpense();
        setupRecyclerView();
    }

    public void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewExpense);

        RestClient.getService().getAllExpenseItem(USER_ID).enqueue(new Callback<AllExpenseResponse>() {
            @Override
            public void onResponse(Call<AllExpenseResponse> call, Response<AllExpenseResponse> response) {
                if (response.isSuccessful()) {
                    expenseItem = response.body().getData();

                    Log.i("responseExpense", expenseItem.toString());

                    adapter = new ExpenseAdapter(expenseItem, getApplicationContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<AllExpenseResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something Wrong, Check Log for Details", Toast.LENGTH_SHORT).show();
                Log.i("error", t.toString());
                Log.i("error", call.toString());
            }
        });
    }

    private void setupTotalExpense() {
        Helper helper = new Helper();
        yourExpenseTotal = findViewById(R.id.yourExpenseTotal);

        RestClient.getService().getAllExpenseCount(USER_ID).enqueue(new Callback<TotalExpenseResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<TotalExpenseResponse> call, Response<TotalExpenseResponse> response) {
                String expenseFromAPI = response.body().getTotalExpense();
                yourExpenseTotal.setText("Rp" + helper.convertNumber(expenseFromAPI));
            }

            @Override
            public void onFailure(Call<TotalExpenseResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error, Something Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
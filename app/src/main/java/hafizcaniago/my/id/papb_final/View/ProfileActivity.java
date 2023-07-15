package hafizcaniago.my.id.papb_final.View;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import hafizcaniago.my.id.papb_final.Api.RestClient;
import hafizcaniago.my.id.papb_final.Data.Response.User.ShowUserResponse;
import hafizcaniago.my.id.papb_final.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    TextInputEditText dateEditText;
    TextInputEditText edtEmail;
    TextInputEditText edtFullName;
    TextInputEditText edtWorkDepartment;
    TextInputEditText edtPassword;
    Button showDateToSelect;
    ImageButton btnBack;

    String USER_ID;
    String USER_FULLNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences prefs = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        USER_ID = prefs.getString("USER_ID", "111111");
        USER_FULLNAME = prefs.getString("USER_FULLNAME", "HAFIZ");

        edtEmail = findViewById(R.id.edtEmail);
        edtFullName = findViewById(R.id.edtFullName);
        edtWorkDepartment = findViewById(R.id.edtWorkDepartment);
        edtPassword = findViewById(R.id.edtPassword);

        setupBackButton();
        setupGenderAutoComplete();
        setupDateButton();
        setupProfileData();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setupGenderAutoComplete() {
        String[] genderList = new String[]{"Male", "Female", "Prefer Not to Say"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                genderList
        );

        autoCompleteTextView = findViewById(R.id.txtGender);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnTouchListener((view, motionEvent) -> {
            if (!autoCompleteTextView.isPopupShowing()) {
                autoCompleteTextView.showDropDown();
            }
            return false;
        });

        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> Toast.makeText(getApplicationContext(), adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show());
    }

    public void setupDateButton() {
        dateEditText = findViewById(R.id.edtDateText);
        showDateToSelect = findViewById(R.id.btnShowDate);

        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date Of Birth").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        showDateToSelect.setOnClickListener(view -> {
            datePicker.show(getSupportFragmentManager(), "Material_Date_Picker");
            datePicker.addOnPositiveButtonClickListener(selection -> dateEditText.setText(datePicker.getHeaderText()));
        });
    }

    private void setupBackButton() {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> super.onBackPressed());
    }

    private void setupProfileData() {
        RestClient.getService().getUserData(USER_ID).enqueue(new Callback<ShowUserResponse>() {
            @Override
            public void onResponse(Call<ShowUserResponse> call, Response<ShowUserResponse> response) {
                assert response.body() != null;
                Log.i("Api Response", response.body().toString());
                edtEmail.setText(response.body().getEmail());
                edtFullName.setText(response.body().getFullname());
                dateEditText.setText(response.body().getDob());
                autoCompleteTextView.setText(response.body().getGender());
                edtWorkDepartment.setText(response.body().getWorkDepartment());
            }

            @Override
            public void onFailure(Call<ShowUserResponse> call, Throwable t) {

            }
        });
    }
}
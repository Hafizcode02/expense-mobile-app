package hafizcaniago.my.id.papb_final;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    TextInputEditText dateEditText;
    Button showDateToSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupGenderAutoComplete();
        setupDateButton();
    }

    public void setupGenderAutoComplete() {
        String[] genderList = new String[]{"Male", "Female", "Prefer Not to Say"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                genderList
        );

        autoCompleteTextView = findViewById(R.id.txtGender);
        autoCompleteTextView.setAdapter(adapter);

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
}
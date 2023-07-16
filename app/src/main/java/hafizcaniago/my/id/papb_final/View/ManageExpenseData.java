package hafizcaniago.my.id.papb_final.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

import hafizcaniago.my.id.papb_final.Api.RestClient;
import hafizcaniago.my.id.papb_final.Data.Body.BodyPostExpenseData;
import hafizcaniago.my.id.papb_final.Data.Response.Expense.PostExpenseResponse;
import hafizcaniago.my.id.papb_final.Data.Response.Expense.ShowExpenseResponse;
import hafizcaniago.my.id.papb_final.Helper.Helper;
import hafizcaniago.my.id.papb_final.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageExpenseData extends AppCompatActivity {

    AutoCompleteTextView expenseType;
    AutoCompleteTextView txtPaymentMethod;
    TextView headerNavbar;
    TextInputEditText edtAmount;
    TextInputEditText edtDateText;
    TextInputEditText detail;

    Button btnSave;
    Button btnDelete;
    Button btnShowDate;
    ImageButton btnBack;

    String USER_ID;
    String USER_FULLNAME;

    String action;
    String id;

    Helper helper = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expense_data);

        action = getIntent().getStringExtra("ACTION");
        id = getIntent().getStringExtra("ID");

        expenseType = findViewById(R.id.txtExpenseType);
        txtPaymentMethod = findViewById(R.id.txtPaymentMethod);
        edtAmount = findViewById(R.id.edtAmount);
        edtDateText = findViewById(R.id.edtDateText);
        detail = findViewById(R.id.txtDetail);

        SharedPreferences prefs = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        USER_ID = prefs.getString("USER_ID", "111111");
        USER_FULLNAME = prefs.getString("USER_FULLNAME", "HAFIZ");

        setupBackButton();
        setupDateButton();
        setupButtonBasedOnAction();
        setupExpenseTypeAutoComplete();
        setupPaymentMethodAutoComplete();
        saveExpenseData();
        showExpenseData();
    }

    private boolean checkAllIsNotEmpty() {
        if (expenseType.getText().toString().length() == 0) {
            expenseType.setError("Please Select");
            return false;
        } else if (txtPaymentMethod.getText().toString().length() == 0) {
            txtPaymentMethod.setError("Please Select");
            return false;
        } else if (edtAmount.getText().toString().length() == 0) {
            edtAmount.setError("Please Insert total expenses");
            return false;
        } else if (edtDateText.getText().toString().length() == 0) {
            edtDateText.setError("Please Insert");
            return false;
        } else if (detail.getText().toString().length() == 0) {
            detail.setError("Please Insert");
            return false;
        }

        return true;
    }

    public void setupDateButton() {
        edtDateText = findViewById(R.id.edtDateText);
        btnShowDate = findViewById(R.id.btnShowDate);

        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Transaction Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        btnShowDate.setOnClickListener(view -> {
            datePicker.show(getSupportFragmentManager(), "Material_Date_Picker");
            datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                @Override
                public void onPositiveButtonClick(Long selection) {
                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                    calendar.setTimeInMillis(selection);
                    SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
                    String formattedDate  = format.format(calendar.getTime());
                    edtDateText.setText(formattedDate);
                }
            });
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupExpenseTypeAutoComplete() {
        String[] genderList = new String[]{"Electronic", "Digital", "Health", "Food", "Transportation", "Utility", "Fashion", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.drop_down_item, genderList);

        expenseType = findViewById(R.id.txtExpenseType);
        expenseType.setAdapter(adapter);

        expenseType.setOnTouchListener((view, motionEvent) -> {
            if (!expenseType.isPopupShowing()) {
                expenseType.showDropDown();
            }
            return false;
        });

        expenseType.setOnItemClickListener((adapterView, view, i, l) -> Toast.makeText(getApplicationContext(), adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupPaymentMethodAutoComplete() {
        String[] genderList = new String[]{"CASH", "DEBT", "E-WALLET"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.drop_down_item, genderList);

        txtPaymentMethod = findViewById(R.id.txtPaymentMethod);
        txtPaymentMethod.setAdapter(adapter);

        txtPaymentMethod.setOnTouchListener((view, motionEvent) -> {
            if (!txtPaymentMethod.isPopupShowing()) {
                txtPaymentMethod.showDropDown();
            }
            return false;
        });

        txtPaymentMethod.setOnItemClickListener((adapterView, view, i, l) -> Toast.makeText(getApplicationContext(), adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show());
    }

    private void setupButtonBasedOnAction() {
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        headerNavbar = findViewById(R.id.headerNavbar);

        if (action.equals("EDIT")) {
            btnDelete.setVisibility(View.VISIBLE);
            btnDelete.setEnabled(true);
            btnSave.setText(R.string.update_text);
            headerNavbar.setText(R.string.edit_expense);
        } else {
            btnSave.setText(R.string.save);
            btnDelete.setVisibility(View.GONE);
            btnDelete.setEnabled(false);
            headerNavbar.setText(R.string.add_expense);
        }
    }

    private void setupBackButton() {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            super.onBackPressed();
        });
    }

    private void showExpenseData() {
        if (action.equals("EDIT")) {
            RestClient.getService().getExpenseByID(id).enqueue(new Callback<ShowExpenseResponse>() {
                @Override
                public void onResponse(Call<ShowExpenseResponse> call, Response<ShowExpenseResponse> response) {
                    if (response.isSuccessful()) {
                        expenseType.setText(response.body().getType());
                        edtAmount.setText(response.body().getPrice());
                        try {
                            edtDateText.setText(helper.convertDate(response.body().getDate(), "GET"));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        txtPaymentMethod.setText(response.body().getPaymentMethod());
                        detail.setText(response.body().getDetail());
                    }
                }

                @Override
                public void onFailure(Call<ShowExpenseResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Something Wrong, Please Check Log", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void saveExpenseData() {
        btnSave.setOnClickListener(view -> {
            if (checkAllIsNotEmpty()) {
                if (action.equals("ADD")) {
                    BodyPostExpenseData bodyPostExpenseData = new BodyPostExpenseData();
                    bodyPostExpenseData.setIdUser(Integer.parseInt(USER_ID));
                    bodyPostExpenseData.setType(expenseType.getText().toString());
                    bodyPostExpenseData.setPrice(Integer.parseInt(Objects.requireNonNull(edtAmount.getText()).toString()));
                    bodyPostExpenseData.setPaymentMethod(txtPaymentMethod.getText().toString());
                    try {
                        bodyPostExpenseData.setDate(helper.convertDate(edtDateText.getText().toString(), "SEND"));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    bodyPostExpenseData.setDetail(detail.getText().toString());

                    RestClient.getService().postExpense(bodyPostExpenseData).enqueue(new Callback<PostExpenseResponse>() {
                        @Override
                        public void onResponse(Call<PostExpenseResponse> call, Response<PostExpenseResponse> response) {
                            assert response.body() != null;
                            if (response.body().getMessage().equals("Data Created Successfully")) {
                                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                                Intent moveActivity = new Intent(getApplicationContext(), MainActivity.class);
                                moveActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(moveActivity);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Data Failed to Save", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<PostExpenseResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Something Wrong, Please Check Log", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (action.equals("EDIT")) {
                    //
                }
            }
        });
    }
}
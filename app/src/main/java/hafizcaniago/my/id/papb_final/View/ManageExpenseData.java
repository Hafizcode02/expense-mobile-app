package hafizcaniago.my.id.papb_final.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hafizcaniago.my.id.papb_final.R;

public class ManageExpenseData extends AppCompatActivity {

    AutoCompleteTextView expenseType;
    AutoCompleteTextView txtPaymentMethod;
    TextView headerNavbar;

    Button btnSave;
    Button btnDelete;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expense_data);

        setupBackButton();
        setupButtonBasedOnAction();
        setupExpenseTypeAutoComplete();
        setupPaymentMethodAutoComplete();
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
        String action = getIntent().getStringExtra("ACTION");
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

    private void setupBackButton()
    {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            super.onBackPressed();
        });
    }
}
package hafizcaniago.my.id.papb_final.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.List;

import hafizcaniago.my.id.papb_final.Data.Response.Expenses.DataItem;
import hafizcaniago.my.id.papb_final.Helper.Helper;
import hafizcaniago.my.id.papb_final.R;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    Helper helper = new Helper();
    private List<DataItem> dataExpense;
    private Context mContext;

    public ExpenseAdapter(List<DataItem> dataExpense, Context mContext) {
        this.dataExpense = dataExpense;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder holder, int position) {
        holder.detailExpense.setText(dataExpense.get(position).getDetail());
        holder.expensePrice.setText("Rp" + helper.convertNumber(dataExpense.get(position).getPrice()));
        try {
            holder.expenseDate.setText(helper.convertDate(dataExpense.get(position).getDate(), "GET"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        holder.expenseType.setText(dataExpense.get(position).getPaymentMethod());
    }

    @Override
    public int getItemCount() {
        return dataExpense.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView detailExpense;
        TextView expensePrice;
        TextView expenseDate;
        TextView expenseType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            detailExpense = itemView.findViewById(R.id.detailExpense);
            expensePrice = itemView.findViewById(R.id.ExpensePrice);
            expenseDate = itemView.findViewById(R.id.expenseDate);
            expenseType = itemView.findViewById(R.id.expenseType);
        }
    }
}

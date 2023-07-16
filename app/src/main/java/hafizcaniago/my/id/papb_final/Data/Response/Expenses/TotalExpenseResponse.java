package hafizcaniago.my.id.papb_final.Data.Response.Expenses;

import com.google.gson.annotations.SerializedName;

public class TotalExpenseResponse{

	@SerializedName("totalExpense")
	private String totalExpense;

	public String getTotalExpense(){
		return totalExpense;
	}
}
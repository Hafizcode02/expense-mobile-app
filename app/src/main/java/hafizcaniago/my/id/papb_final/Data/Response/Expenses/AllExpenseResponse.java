package hafizcaniago.my.id.papb_final.Data.Response.Expenses;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AllExpenseResponse{

	@SerializedName("data")
	private List<DataItem> data;

	public List<DataItem> getData(){
		return data;
	}
}
package hafizcaniago.my.id.papb_final.Data.Response.Expense;

import com.google.gson.annotations.SerializedName;

public class PostExpenseResponse{

	@SerializedName("message")
	private String message;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}
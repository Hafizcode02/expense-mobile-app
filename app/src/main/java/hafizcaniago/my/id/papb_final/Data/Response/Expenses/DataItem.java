package hafizcaniago.my.id.papb_final.Data.Response.Expenses;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("date")
	private String date;

	@SerializedName("price")
	private String price;

	@SerializedName("id")
	private String id;

	@SerializedName("detail")
	private String detail;

	@SerializedName("type")
	private String type;

	@SerializedName("payment_method")
	private String paymentMethod;

	public String getDate(){
		return date;
	}

	public String getPrice(){
		return price;
	}

	public String getId(){
		return id;
	}

	public String getDetail(){
		return detail;
	}

	public String getType(){
		return type;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}
}
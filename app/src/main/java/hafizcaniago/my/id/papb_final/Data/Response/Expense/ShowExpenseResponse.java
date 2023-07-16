package hafizcaniago.my.id.papb_final.Data.Response.Expense;

import com.google.gson.annotations.SerializedName;

public class ShowExpenseResponse{

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

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return detail;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}
}
package hafizcaniago.my.id.papb_final.Data.Body;

import com.google.gson.annotations.SerializedName;

public class BodyPostExpenseData{

	@SerializedName("date")
	private String date;

	@SerializedName("price")
	private int price;

	@SerializedName("id_user")
	private int idUser;

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

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
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
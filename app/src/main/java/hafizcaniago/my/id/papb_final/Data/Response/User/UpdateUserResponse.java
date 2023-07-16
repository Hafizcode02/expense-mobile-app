package hafizcaniago.my.id.papb_final.Data.Response.User;

import com.google.gson.annotations.SerializedName;

public class UpdateUserResponse{

	@SerializedName("message")
	private String message;

	public String getMessage(){
		return message;
	}
}
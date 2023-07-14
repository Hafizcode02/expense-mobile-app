package hafizcaniago.my.id.papb_final.Data.Response.Login;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("id")
	private String id;

	@SerializedName("fullname")
	private String fullname;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getFullname(){
		return fullname;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"id = '" + id + '\'' + 
			",fullname = '" + fullname + '\'' + 
			"}";
		}
}
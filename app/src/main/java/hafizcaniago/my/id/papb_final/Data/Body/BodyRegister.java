package hafizcaniago.my.id.papb_final.Data.Body;

import com.google.gson.annotations.SerializedName;

public class BodyRegister{

	@SerializedName("password")
	private String password;

	@SerializedName("fullname")
	private String fullname;

	@SerializedName("email")
	private String email;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getFullname(){
		return fullname;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"BodyRegister{" + 
			"password = '" + password + '\'' + 
			",fullname = '" + fullname + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
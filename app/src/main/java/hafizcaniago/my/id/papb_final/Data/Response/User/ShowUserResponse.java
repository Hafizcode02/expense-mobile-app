package hafizcaniago.my.id.papb_final.Data.Response.User;

import com.google.gson.annotations.SerializedName;

public class ShowUserResponse{

	@SerializedName("gender")
	private String gender;

	@SerializedName("dob")
	private String dob;

	@SerializedName("id")
	private String id;

	@SerializedName("fullname")
	private String fullname;

	@SerializedName("email")
	private String email;

	@SerializedName("work_department")
	private String workDepartment;

	public String getGender(){
		return gender;
	}

	public String getDob(){
		return dob;
	}

	public String getId(){
		return id;
	}

	public String getFullname(){
		return fullname;
	}

	public String getEmail(){
		return email;
	}

	public String getWorkDepartment(){
		return workDepartment;
	}

	@Override
 	public String toString(){
		return 
			"ShowUserResponse{" + 
			"gender = '" + gender + '\'' + 
			",dob = '" + dob + '\'' + 
			",id = '" + id + '\'' + 
			",fullname = '" + fullname + '\'' + 
			",email = '" + email + '\'' + 
			",work_department = '" + workDepartment + '\'' + 
			"}";
		}
}
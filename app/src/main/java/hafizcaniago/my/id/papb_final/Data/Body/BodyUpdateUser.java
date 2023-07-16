package hafizcaniago.my.id.papb_final.Data.Body;

import com.google.gson.annotations.SerializedName;

public class BodyUpdateUser{

	@SerializedName("password")
	private String password;

	@SerializedName("gender")
	private String gender;

	@SerializedName("dob")
	private String dob;

	@SerializedName("fullname")
	private String fullname;

	@SerializedName("work_department")
	private String workDepartment;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getDob(){
		return dob;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getFullname(){
		return fullname;
	}

	public void setWorkDepartment(String workDepartment){
		this.workDepartment = workDepartment;
	}

	public String getWorkDepartment(){
		return workDepartment;
	}
}
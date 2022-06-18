package net.manyahl.apiratelimitdemo.users;

public class User {
	private long id;
	private String fullName;
	private String email;
	
	public User() {}
	
	public User(long id, String fullName, String email) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	};
	
	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + "]";
	}

}

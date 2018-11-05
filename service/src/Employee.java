import org.simpleframework.http.Query;

public class Employee extends User{

	String name;
	String dateOfBirth;
	String career;
	String workPermit;
	String salary;
	String expirationDate;

	public Employee(String cpf, String password, String name, String dateOfBirth, String career, String workPermit, String salary, String expirationDate) {
		super(cpf, password);
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.career = career;
		this.workPermit = workPermit;
		this.salary = salary;
		this.expirationDate = expirationDate;
	}
	
	public Employee(Query query) {
		super(query.get("cpf"), query.get("password"));
		this.name = query.get("name");
		this.dateOfBirth = query.get("birth");
		this.career = query.get("career");
		this.workPermit = query.get("workPermit");
		this.salary = query.get("salary");
		this.expirationDate = query.get("expirationDate");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getWorkPermit() {
		return workPermit;
	}

	public void setWorkPermit(String workPermit) {
		this.workPermit = workPermit;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String getFormatedData() {
		return this.user + ":" + this.password + ":" + this.name + ":" + this.dateOfBirth + ":" + this.career + ":" + this.workPermit + ":" + this.salary + ":" + this.expirationDate;
	}
	
}

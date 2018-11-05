import org.simpleframework.http.Query;

public class Pacient extends User{
	String name;
	String dateOfBirth;
	String plan;
	String planType;
	String planCode;
	String expirationDate;
	
	public Pacient(String cpf, String password, String name, String dateOfBirth, String plan, String planType, String planCode, String expirationDate) {
		super(cpf, password);
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.plan = plan;
		this.planType = planType;
		this.planCode = planCode;
		this.expirationDate = expirationDate;
	}
	
	public Pacient(Query query) {
		super(query.get("cpf"), query.get("password"));
		this.name = query.get("name");
		this.dateOfBirth = query.get("birth");
		this.plan = query.get("plan");
		this.planType = query.get("planType");
		this.planCode = query.get("planCode");
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

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String getFormatedData() {
		return this.user + ":" + this.password + ":" + this.name + ":" + this.dateOfBirth + ":" + this.plan + ":" + this.planCode + ":" + this.planType + ":" + this.expirationDate;
	}
	
}

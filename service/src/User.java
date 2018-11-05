import java.io.Serializable;

public abstract class User implements Serializable{
	protected String user;
	protected String password;
	protected DAO manager;
	
	public User(String user, String password) {
		setUser(user);
		setPassword(password);
		manager = new UserDAO("users.txt");
	}
	
	public void save() {
		manager.save(this);
	}
	
	public String read(String user) {
		return manager.get(user);
	}
	
	public void update() {
		manager.update(this);
	}
	
	public void delete() {
		manager.delete(this);
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public abstract String getFormatedData();
}

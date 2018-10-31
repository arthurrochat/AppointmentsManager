import java.util.ArrayList;

public class UserDAO implements DAO<User>{
	
	private FileSystem fs = new FileSystem();
	
	public User get(String id) {
		String data = "";
		ArrayList<String> users = fs.read();
		for(String user : users) {
			data = getIdFromRawContent(user).equals(id) ? user : data;
		}
		return createUserByRawContent(data);
	}
	
	public void save(User user) {
		fs.write(formatData(user.getUser(), user.getPassword()));
	}
	
	public void update(User user) {
		String row = "";
		String id = user.getUser();
		ArrayList<String> users = fs.read();
		for(int i = 0; i < users.size(); i++) {
			if(getIdFromRawContent(users.get(i)).equals(id)) {
				users.remove(i);
				users.add(i, formatData(user.getUser(), user.getPassword()));
				fs.write(users);
				return;
			}
		}	
	}
	
	public void delete(User user) {
		String row = "";
		String id = user.getUser();
		ArrayList<String> users = fs.read();
		for(int i = 0; i < users.size(); i++) {
			if(getIdFromRawContent(users.get(i)).equals(id)) {
				users.remove(i);
				fs.write(users);
				return;
			}
		}
			
	}
	
	public String getIdFromRawContent(String data) {
		return data.split(":")[0];
	}
	
	public User createUserByRawContent(String data) {
		String[] parsedData = data.split(":");
		return new User(parsedData[0], parsedData[1]);
	}
	
	public String formatData(String user, String password) {
		return user + ":" + password; 
	}
	
	

}

import java.util.ArrayList;

public class UserDAO implements DAO<User>{
	
	private FileSystem fs = new FileSystem();
	private String path; 
	
	UserDAO(String path){
		this.path = path;
	}
	
	public String get(String id) {
		String data = "";
		ArrayList<String> users = fs.read(path);
		for(String user : users) {
			data = getIdFromRawContent(user).equals(id) ? user : data;
		}
		return data;
	}
	
	public void save(User user) {
		fs.write(user.getFormatedData(), path);
	}
	
	public void update(User user) {
		String row = "";
		String id = user.getUser();
		ArrayList<String> users = fs.read(path);
		for(int i = 0; i < users.size(); i++) {
			if(getIdFromRawContent(users.get(i)).equals(id)) {
				users.remove(i);
				users.add(i, user.getFormatedData());
				fs.write(users, path);
				return;
			}
		}	
	}
	
	public void delete(User user) {
		String row = "";
		String id = user.getUser();
		ArrayList<String> users = fs.read(path);
		for(int i = 0; i < users.size(); i++) {
			if(getIdFromRawContent(users.get(i)).equals(id)) {
				users.remove(i);
				fs.write(users, path);
				return;
			}
		}
			
	}
	
	public String getIdFromRawContent(String data) {
		return data.split(":")[0];
	}
	
	

}

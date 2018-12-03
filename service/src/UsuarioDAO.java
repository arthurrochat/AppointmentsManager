import java.util.ArrayList;
import org.json.JSONObject;

public class UsuarioDAO implements DAO<Usuario>{
	
	private FileSystem fs = new FileSystem();
	private String path; 
	
	UsuarioDAO(String path){
		this.path = path;
	}
	
	public String get(String id) {
		String data = "";
		ArrayList<String> users = fs.read(path);
		for(String user : users) {
			if(user.length() != 0 && getIdFromRawContent(user).equals(id)) {
				data = user;
			}
		}
		return data;
	}
	
	public void save(String user) {
		fs.write(user, path);
	}
	
	public void update(Usuario user) {
		String row = "";
		String id = user.getUser();
		ArrayList<String> users = fs.read(path);
		for(int i = 0; i < users.size(); i++) {
			String userToCompare = users.get(i);
			if(userToCompare.length() != 0 && getIdFromRawContent(userToCompare).equals(id)) {
				users.remove(i);
				users.add(i, user.toJson().toString());
				fs.write(users, path);
				return;
			}
		}	
	}
	
	public void delete(Usuario user) {
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
		JSONObject json = new JSONObject(data);
		
		return (String)json.get("user");
	}
}

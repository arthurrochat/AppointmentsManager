import java.util.ArrayList;
import org.json.JSONObject;

public class UserDAO implements DAO<Usuario>{
	
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
	
	public void save(Usuario user) {
		fs.write(user.toJson().toString(), path);
	}
	
	public void update(Usuario user) {
		String row = "";
		String id = user.getCPF();
		ArrayList<String> users = fs.read(path);
		for(int i = 0; i < users.size(); i++) {
			if(getIdFromRawContent(users.get(i)).equals(id)) {
				users.remove(i);
				users.add(i, user.toJson().toString());
				fs.write(users, path);
				return;
			}
		}	
	}
	
	public void delete(Usuario user) {
		String row = "";
		String id = user.getCPF();
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
		
		return (String)json.get("cpf");
	}
}

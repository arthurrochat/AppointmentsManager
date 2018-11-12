import java.util.ArrayList;
import org.json.JSONObject;

public class AgendaDAO implements DAO<Agenda>{
	
	private FileSystem fs = new FileSystem();
	private String path; 
	
	AgendaDAO(String path){
		this.path = path;
	}
	
	public String get(String id) {
		String data = "";
		ArrayList<String> consults = fs.read(path);
		for(String consult : consults) {
			data = getIdFromRawContent(consult).equals(id) ? consult : data;
		}
		return data;
	}
	
	public String getByDate(String date) {
		String data = "";
		ArrayList<String> consults = fs.read(path);
		for(String consult : consults) {
			data = getDateFromRawContent(consult).equals(date) ? consult : data;
		}
		return data;
	}
	
	public void save(Agenda consult) {
		fs.write(consult.toJson().toString(), path);
	}
	
	public void update(Agenda consult) {
		String row = "";
		String id = consult.getCPF();
		ArrayList<String> users = fs.read(path);
		for(int i = 0; i < users.size(); i++) {
			if(getIdFromRawContent(users.get(i)).equals(id)) {
				users.remove(i);
				users.add(i, consult.toJson().toString());
				fs.write(users, path);
				return;
			}
		}	
	}
	
	public void delete(Agenda consult) {
		String row = "";
		String id = consult.getCPF();
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
	
	public String getDateFromRawContent(String data) {
		JSONObject json = new JSONObject(data);
		
		return (String)json.get("date");
	}
}

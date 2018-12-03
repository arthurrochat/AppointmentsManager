import java.util.ArrayList;
import org.json.JSONObject;

public class AgendaDAO implements DAO<Agenda>{
	
	private FileSystem<String> fs = new FileSystem();
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
	
	public ArrayList<String> getByDate(String day, String month) {
		ArrayList<String> consults = fs.read(path);
		ArrayList<String> consultsToReturn = new ArrayList();
		
		for(String consult : consults) {
			if(!getDayFromRawContent(consult).equals(day) && !getMonthFromRawContent(consult).equals(month)) {
				consultsToReturn.add(consult);
			}
		}
		return consultsToReturn;
	}
	
	public void save(Agenda consult) {
		fs.write(consult.toJson().toString(), path);
	}
	
	public void update(Agenda consult) {
		String row = "";
		String id = consult.getName();
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
		String id = consult.getName();
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
	
	public String getDayFromRawContent(String data) {
		JSONObject json = new JSONObject(data);
		
		return (String)json.get("day");
	}
	
	public String getMonthFromRawContent(String data) {
		JSONObject json = new JSONObject(data);
		
		return (String)json.get("month");
	}
	
	public String getTimeFromRawContent(String data) {
		JSONObject json = new JSONObject(data);
		
		return (String)json.get("time");
	}
	
	public ArrayList<String> getAllSchedules() {
		return fs.read(path);
	}
	
	@Override
	public void save(String data) {}
}

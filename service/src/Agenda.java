import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

public class Agenda {
	private String name;
	private String day;
	private String month;
	private String time;
	private AgendaDAO manager = new AgendaDAO("consults.txt");
	private static String path = "agendamentos.txt";
	
	public Agenda(String name, String day, String month, String time) {
		this.name = name;
		this.day = day;
		this.month = month;
		this.time = time;
	}
	
	public Agenda(JSONObject json) {
		this.name = ((String)json.get("name"));
		this.day = ((String)json.get("day"));
		this.month = ((String)json.get("month"));
		this.time = ((String)json.get("time"));
	}
	
	public Agenda() {
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean agendar() {
		ArrayList<String> consult = manager.getByDate(this.day, this.month);
		if(consult.isEmpty()) {
			manager.save(this);
			return true;
		} else {
			return false;
		}
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("name", this.name);
		obj.put("day", this.day);
		obj.put("month", this.month);
		obj.put("time", this.time);
		return obj;
	}
	
	public JSONArray getSheduleByDate() {
		ArrayList<String> schedules = manager.getByDate(this.day, this.month);
		JSONArray arraySchedule = new JSONArray();
		for(String schedule : schedules) {
			arraySchedule.put(new JSONObject(schedule));
		}
		return arraySchedule;
	}
	
	public JSONArray getAllSchedules() {
		ArrayList<String> schedules = manager.getAllSchedules();
		JSONArray arraySchedule = new JSONArray();
		for(String schedule : schedules) {
			arraySchedule.put(new JSONObject(schedule));
		}
		return arraySchedule;
	}
	
}

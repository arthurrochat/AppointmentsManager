import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public class Agenda {
	private String cpf;
	private String date;
	private AgendaDAO manager;
	
	public Agenda(String cpf, String date, String path) {
		this.cpf = cpf;
		this.date = date;
		manager = new AgendaDAO(path);
	}
	
	public Agenda(JSONObject json) {
		this.cpf = (String)json.get("cpf");
		this.date = (String)json.get("data");
	}
	
	public boolean agendar() {
		String consult = manager.getByDate(this.date);
		if(consult == "") {
			manager.save(this);
			return true;
		} else {
			return false;
		}
	}
	
	public String getCPF() {
		return cpf;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("cpf", this.cpf);
		obj.put("date", this.date);
		return obj;
	}
}

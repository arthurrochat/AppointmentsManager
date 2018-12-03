import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public class AgendaService {
	public static String agendar(Request request) {
		Query query = request.getQuery();
		JSONObject JSONResposta = new JSONObject();
		Agenda consulta = new Agenda(query.get("name"), query.get("day"), query.get("month"), query.get("time"));
		
		if(consulta.agendar()) {
			JSONResposta.put("status", 0);
			System.out.println("Consulta marcada com sucesso");
		}
		else {
			JSONResposta.put("status", 2);
			System.out.println("Falha ao marcar consulta");
		}
		
		return JSONResposta.toString();
	}
	
	public static String getAllSchedules() {
		Agenda schedule = new Agenda();
		JSONObject JSONResposta = new JSONObject();
		JSONResposta.put("status", 0);
		JSONResposta.put("consults", schedule.getAllSchedules());
		return JSONResposta.toString();
	}
	
	public static String getScheduleByDate(Request request) {
		Query query = request.getQuery();
		Agenda schedule = new Agenda(query.get("name"), query.get("day"), query.get("month"), query.get("time"));
		return schedule.getSheduleByDate().toString();
	}
	
}

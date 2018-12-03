import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public class UsuarioService {
	public static String addUser(Request request) {
		Query query = request.getQuery();
		JSONObject JSONResposta = new JSONObject();
		int type = query.getInteger("tipo");
		Usuario user = (type == 1) ? new Paciente(query) : new Funcionario(query);
		user.save(user.toString());
		JSONResposta.put("status", 0);
		return JSONResposta.toString();
	}
	
	public static String updateUser(Request request) {
		Query query = request.getQuery();
		JSONObject JSONResposta = new JSONObject();
		DAO manager = new UsuarioDAO("users.txt");
		int type = query.getInteger("tipo");
		Usuario user = (type == 1) ? new Paciente(query) : new Funcionario(query);
		manager.update(user);
		JSONResposta.put("status", 0);
		return JSONResposta.toString();
	}
	
	public static String getUser(Request request) {
		Query query = request.getQuery();
		JSONObject JSONResposta = new JSONObject();
		DAO manager = new UsuarioDAO("users.txt");
		String data = manager.get(query.get("user"));
		if(data != "") {
			JSONResposta = new JSONObject(data);
			JSONResposta.put("status", 0);
		}
		else {
			JSONResposta.put("status", 1);
		}
		return JSONResposta.toString();
	}
	
	public static String login(Request request) {
		Query query = request.getQuery();
		JSONObject JSONResposta = new JSONObject();
		DAO manager = new UsuarioDAO("users.txt");
		
		String data = manager.get(query.get("user"));
		
		if(data == "") {
			JSONResposta.put("status", 2);
		} 
		else {
			JSONObject json = new JSONObject(data);
			if(((String)json.get("senha")).equals(query.get("password"))){
				JSONResposta.put("status", 0);
			} 
			else {
				JSONResposta.put("status", 1);
			}
		}
		return JSONResposta.toString();
	}
	
	public static String trocaSenha(Request request) {
		Query query = request.getQuery();
		JSONObject JSONResposta = new JSONObject();
		
		DAO manager = new UsuarioDAO("users.txt");
		String data = manager.get(query.get("cpf"));
		String msg = "Senha invalida";
		
		if(data != "") {
			JSONObject json = new JSONObject(data);
			int type = (int)json.getInt("tipo");
			Usuario user = (type == 1) ? new Paciente(json) : new Funcionario(json);
			
			if(user.alteraSenha(query.get("senha"), query.get("novasenha"))) {
				manager.update(user);
				JSONResposta.put("status", 0);
				msg = "Senha alterada com sucesso";
			}
			else
				JSONResposta.put("status", 1);
		}
		else 
			JSONResposta.put("status", 2);
		
		System.out.println(msg);
		
		return JSONResposta.toString();
	}
}

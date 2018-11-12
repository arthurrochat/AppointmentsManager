import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public class UsuarioService {
	public static String addUsuario(Request request) {
		Query query = request.getQuery();
		JSONObject JSONResposta = new JSONObject();
		
		int type = query.getInteger("tipo");
		Usuario user = (type == 1) ? new Paciente(query) : new Funcionario(query);
		user.save();
		
		System.out.println("Cadastro realizada com sucesso");
		
		JSONResposta.put("status", 0);
		
		return JSONResposta.toString();
	}
	
	public static String atualizaUsuario(String cpf, JSONObject novosDados) {
		System.out.println("Atualizar Usuario");
		return "";
	}
	
	public static String getUsuario(Request request) {
		Query query = request.getQuery();
		JSONObject JSONResposta = new JSONObject();
		
		DAO manager = new UserDAO("users.txt");
		String data = manager.get(query.get("cpf"));
		String msg = "Usuario não encontrado";
		
		if(data != "") {
			JSONResposta = new JSONObject(data);
			JSONResposta.remove("senha");
			JSONResposta.put("status", 0);
			msg = "Busca realizada com sucesso";
		}
		else 
			JSONResposta.put("status", 1);
		
		System.out.println(msg);
		
		return JSONResposta.toString();
	}
	
	public static String validaUsuario(Request request) {
		Query query = request.getQuery();
		JSONObject JSONResposta = new JSONObject();
		
		DAO manager = new UserDAO("users.txt");
		String data = manager.get(query.get("login"));
		String msg = "Usuario não encontrado";
		
		if(data != "") {
			JSONObject json = new JSONObject(data);
			if(((String)json.get("senha")).equals(query.get("senha"))){
				JSONResposta.put("status", 0);
				JSONResposta.put("nome", json.get("nome"));
				JSONResposta.put("tipo", json.get("tipo"));
				msg = "Login realizado com sucesso";
			}
			else
				JSONResposta.put("status", 1);
				
		}
		else 
			JSONResposta.put("status", 2);
		
		System.out.println(msg);
		
		return JSONResposta.toString();
	}
	
	public static String trocaSenha(Request request) {
		Query query = request.getQuery();
		JSONObject JSONResposta = new JSONObject();
		
		DAO manager = new UserDAO("users.txt");
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

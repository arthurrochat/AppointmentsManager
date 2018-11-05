import java.io.PrintStream;

import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;

public class Server implements Container {
	
	@Override
	public void handle(Request request, Response response) {
		try {
			
			String path = request.getPath().getPath();
			Query query = request.getQuery();
			
			if(path.startsWith("/signUp")) {
				String type = query.get("type");
				User user = (type == "pacient") ? new Pacient(query) : new Employee(query);
				user.save();
			}
			if(path.startsWith("/login")) {
				DAO manager = new UserDAO("users.txt");
				String data = manager.get(query.get("cpf"));
				PrintStream body = response.getPrintStream();
				String msg = "Usuario não encontrado";
				if(data != "") {
					msg = "Login realizado com sucesso";
				}
				System.out.println(msg);
				body.println(msg);
				body.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

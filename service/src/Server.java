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
			if (path.startsWith("/signUp")) {
				System.out.println("Batata");
				Query c = request.getQuery();
				String user = c.get("user");
				System.out.println(user);
				UserDAO x = new UserDAO();
				User user1 = new User("3", "4");
				x.update(user1);
				x.delete(user1);
				//boolean logged = arquivo.Login("../users.txt", query.get("user"), query.get("password"));
				//json = usuarioService.adicionarUsuario(request);
				//mensagem = "lala";
				//this.enviaResposta(Status.CREATED, response, mensagem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

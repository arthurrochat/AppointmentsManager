import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class Servidor implements Container {
	static UsuarioService usuarioService;

	public void handle(Request request, Response response) {
		try {
			String path = request.getPath().getPath();
			//String method = request.getMethod();
			
			if (path.startsWith("/adicionarUsuario")/* && "POST".equals(method)*/) {
				System.out.println("REQUISI��O DE CADASTRO");
				this.enviaResposta(Status.CREATED, response, UsuarioService.addUser(request));
			}
			else if (path.startsWith("/login")/* && "POST".equals(method)*/) {
				System.out.println("REQUISI��O  DE LOGIN");
				this.enviaResposta(Status.CREATED, response, UsuarioService.login(request));
			}
			else if(path.startsWith("/getUser")/* && "POST".equals(method)*/) {
				System.out.println("REQUISI��O DE DOWNLOAD");
				this.enviaResposta(Status.CREATED, response, UsuarioService.getUser(request));
			}
			else if(path.startsWith("/trocaSenha")/* && "POST".equals(method)*/) {
				System.out.println("REQUISI��O DE ATUALIZA��O DE SENHA");
				this.enviaResposta(Status.CREATED, response, UsuarioService.trocaSenha(request));
			}
			else if(path.startsWith("/updateUser")/* && "POST".equals(method)*/) {
				System.out.println("REQUISI��O DE ATUALIZA��O DE SENHA");
				this.enviaResposta(Status.CREATED, response, UsuarioService.updateUser(request));
			}
			else if(path.startsWith("/setConsult")/* && "POST".equals(method)*/) {
				System.out.println("REQUISI��O DE AGENDAMENTO");
				this.enviaResposta(Status.CREATED, response, AgendaService.agendar(request));
			}
			else if(path.startsWith("/getScheduleByDate")/* && "POST".equals(method)*/) {
				System.out.println("REQUISI��O DE DOWNLOAD");
				this.enviaResposta(Status.CREATED, response, AgendaService.getScheduleByDate(request));
			}
			else if(path.startsWith("/getAllSchedules")/* && "POST".equals(method)*/) {
				System.out.println("REQUISI��O DE DOWNLOAD");
				this.enviaResposta(Status.CREATED, response, AgendaService.getAllSchedules());
			}
			else {
				this.naoEncontrado(response, path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void naoEncontrado(Response response, String path) throws Exception {
		JSONObject error = new JSONObject();
		error.put("error", "N�o encontrado.");
		error.put("path", path);
		enviaResposta(Status.NOT_FOUND, response, error.toString());
	}

	private void enviaResposta(Status status, Response response, String str) throws Exception {

		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();

		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "Controle de EstoqueService (1.0)");
		response.setValue("Access-Control-Allow-Origin", "*");
		response.setDate("Date", time);
		response.setDate("Last-Modified", time);
		response.setStatus(status);

		if (str != null)
			body.println(str);
		body.close();
	}

	public static void main(String args[]) throws IOException {
		usuarioService = new UsuarioService();

		int porta = 8000;

		// Configura uma conex�o soquete para o servidor HTTP.
		Container container = new Servidor();
		ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
		Connection conexao = new SocketConnection(servidor);
		SocketAddress endereco = new InetSocketAddress(porta);
		conexao.connect(endereco);

		System.out.println("Tecle ENTER para interromper o servidor...");
		System.in.read();

		conexao.close();
		servidor.stop();

	}

}
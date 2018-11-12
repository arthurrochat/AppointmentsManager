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
				System.out.println("REQUISIÇÃO DE CADASTRO");
				this.enviaResposta(Status.CREATED, response, UsuarioService.addUsuario(request));
			}
			else if (path.startsWith("/testaLogin")/* && "POST".equals(method)*/) {
				System.out.println("REQUISIÇÃO  DE LOGIN");
				this.enviaResposta(Status.CREATED, response, UsuarioService.validaUsuario(request));
			}
			else if(path.startsWith("/CarregaUsuario")/* && "POST".equals(method)*/) {
				System.out.println("REQUISIÇÃO DE DOWNLOAD");
				this.enviaResposta(Status.CREATED, response, UsuarioService.getUsuario(request));
			}
			else if(path.startsWith("/AlterarSenha")/* && "POST".equals(method)*/) {
				System.out.println("REQUISIÇÃO DE ATUALIZAÇÃO DE SENHA");
				this.enviaResposta(Status.CREATED, response, UsuarioService.trocaSenha(request));
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
		error.put("error", "Não encontrado.");
		error.put("path", path);
		enviaResposta(Status.NOT_FOUND, response, error.toString());
	}

	private void enviaResposta(Status status, Response response, String str) throws Exception {

		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();

		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "Controle de EstoqueService (1.0)");
		response.setValue("Access-Control-Allow-Origin", "null");
		response.setDate("Date", time);
		response.setDate("Last-Modified", time);
		response.setStatus(status);

		if (str != null)
			body.println(str);
		body.close();
	}

	public static void main(String args[]) throws IOException {
		usuarioService = new UsuarioService();

		int porta = 7200;

		// Configura uma conexão soquete para o servidor HTTP.
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
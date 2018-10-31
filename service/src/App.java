import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class App {

	public static void main(String[] args) {
		connect();
	}
	
	public static void connect() {
		try {
			int port = 7200;
	
			Container container = new Server();
			ContainerSocketProcessor server = new ContainerSocketProcessor(container);
			Connection connection = new SocketConnection(server);
			SocketAddress address = new InetSocketAddress(port);
			connection.connect(address);
	
			System.out.println("Tecle ENTER para interromper o servidor...");
			System.in.read();
	
			connection.close();
			server.stop();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

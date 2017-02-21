import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerSocket {

	int portNumber = 4200;
    
	public static void startServer() throws IOException{
		ServerSocket listener = new ServerSocket(portNumber);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                    out.println("Can you hear me?");
                } finally {
                    socket.close();
                }
            }
        }
		finally {
            listener.close();
        }
	}
	
	public static void main(String[] args) throws IOException {        
		startServer();
		System.out.println("Server is listening on port " + portNumber);
    }
}
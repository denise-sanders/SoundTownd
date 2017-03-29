import java.net.*;
import java.io.*;
import java.util.*;

public class ClientSocket {

        /*
		 *  This main method was made for testing.
		 */
		public static void main (String[] args){
			ClientSocket socket = new ClientSocket();	
		}
		
		public int portNumber = 4200;
		
		private Socket client;
        private PrintWriter out;        
        private BufferedReader in;      

        private Scanner scanner = new Scanner(System.in);

        public ClientSocket() {
                try {
                        /* Opens socket, and read/write channels for socket */
                        openConnections();
                }
                catch(Throwable o) {
                        System.out.println("Unable to connect to server");
                }
        }

        /**
         * Checks if there are messages to read in the buffer
         */
        public boolean bufferedReaderReady(){
                try {
                        return in.ready();
                }
                catch (Throwable o){
                        throw new Error ("Couldn't check bufferedReader");
                }
        }

        /**
         * called by wherever to send info to server
         */
        public void  writeToServer(String command){
                out.print(command);
                out.flush();
        }

        /**
         * called by wherever to read messages from server
         */
        public String readFromServer(){
                try {
                        return in.readLine();
                }
                catch (Throwable o){

                }
                return "Could not read from Server";
        }

        /**
         * Opens the Socket and read/write from the socket to communicate with server
         * Called at the beginning of the Connection() constructor in Connection.java
         */
        private void openConnections(){
                try {
                        client = new Socket(InetAddress.getLocalHost(),portNumber);
                        out = new PrintWriter(client.getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                        System.out.println("Client-side connections opened on port " + portNumber);
                }
                catch (Throwable o) {
                        System.out.println("Failed to open client side connections. :(");
                }
        }

        /**
         * Closes the Socket and read/write from the socket to properly free system resources
         * Called at the end of the main() method in Main.java
         */
        public void closeConnections() {
                try {
                        out.close();
                        in.close();
                        client.close();

                        System.out.println("Client-side connections closed :)");
                }
                catch (Throwable o) {
                        System.out.println("Failed to close client side connections. :(");
                }
        }

        /**
         * Helper function to get user input from keyboard
         */
        private String userInput(){
                return scanner.next();
        }
}
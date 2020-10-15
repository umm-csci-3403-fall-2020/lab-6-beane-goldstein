package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import jdk.internal.util.xml.impl.Input;

public class EchoServer {
	
	
	public static final int PORT_NUMBER = 6013; 
	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		while (true) {
			Socket client = serverSocket.accept();

			ConnectionRunnable clientConnection = new ConnectionRunnable(client);
			Thread clientThread = new Thread(clientConnection);
			clientThread.start();

			// Put your code here.
			// This should do very little, essentially:
			//   * Construct an instance of your runnable class
			//   * Construct a Thread with your runnable
			//      * Or use a thread pool
			//   * Start that thread
		}
	}

	public class ConnectionRunnable implements Runnable {
		
		private Socket socket;
		private InputStream byteInputStream;
		private OutputStream byteOutputStream;


		public ConnectionRunnable(Socket sock) {
			socket = sock;
			byteInputStream = socket.getInputStream();
			byteOutputStream = socket.getOutputStream();
		}

		@Override
		public void run() {
			int inputByte;

			while (true) {
				inputByte = byteInputStream.read(); //gives -1 if there's nothing left to read
				if (inputByte != -1){
					byteOutputStream.write(inputByte);
				} else {
					break;
				}
			}
			socket.shutdownOutput();

		}
	}
}
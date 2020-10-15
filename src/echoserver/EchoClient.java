package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	private void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);

		OutputWriter writer = new OutputWriter(socket);
		InputReader reader = new InputReader(socket);

		Thread writeThread = new Thread(writer);
		Thread readThread = new Thread(reader);

	}

	public class OutputWriter implements Runnable{
		OutputStream output;
		Socket outputSock;
		public OutputWriter(Socket sock) throws IOException{
			outputSock = sock;
			output = outputSock.getOutputStream();
		}

		@Override
		public void run() {
			try {

			}catch (IOException ioe){
				System.out.println("Caught an unexpected exception.");
			}
		}
	}

	public class InputReader implements Runnable{
		InputStream input;
		Socket inputSock;
		public InputReader(Socket sock) throws IOException{
			inputSock = sock;
			input = inputSock.getInputStream();
		}

		@Override
		public void run() {
			try	{

			}catch (IOException ioe){
				System.out.println("Caught and unexpected exception.");
			}
		}
	}

}
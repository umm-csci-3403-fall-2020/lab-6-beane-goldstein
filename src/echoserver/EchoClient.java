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
		InputStream socketInputStream = socket.getInputStream();
		OutputStream socketOutputStream = socket.getOutputStream();

		OutputWriter writer = new OutputWriter(socketOutputStream);
		InputReader reader = new InputReader(socketInputStream);

		Thread writeThread = new Thread(writer);
		Thread readThread = new Thread(reader);

	}

	public class OutputWriter implements Runnable{
		OutputStream output;
		Socket sock;
		public OutputWriter(OutputStream output){

		}

		@Override
		public void run() {

		}
	}

	public class InputReader implements Runnable{
		InputStream input;
		Socket sock;

		public InputReader(InputStream input){

		}

		@Override
		public void run() {

		}
	}

}
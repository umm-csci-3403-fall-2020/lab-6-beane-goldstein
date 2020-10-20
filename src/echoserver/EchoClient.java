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

	public class InputReader implements Runnable{
		OutputStream output;
		Socket outputSock;
		public InputReader(Socket sock) throws IOException{
			outputSock = sock;
			output = outputSock.getOutputStream();
		}

		@Override
		public void run() {
			int input;
			try {
				while ((input = System.in.read()) != -1){ //while go until it hits -1 or basically null
					//writing work
					output.write(input); //writes the data to be sent to the server
				}
				output.flush(); //flushes the stream and forces bytes to written out
				outputSock.shutdownOutput();

			}catch (IOException ioe){
				System.out.println("Caught an unexpected exception.");
			}
		}
	}

	public class OutputWriter implements Runnable{
		InputStream input;
		Socket inputSock;
		public OutputWriter(Socket sock) throws IOException{
			inputSock = sock;
			input = inputSock.getInputStream();
		}

		@Override
		public void run() {
			int output;
			try	{
				while ((output = System.in.read()) != -1) {
					System.out.write(input.read()); //what should be sent back to the client

				}
				inputSock.close();

			}catch (IOException ioe){
				System.out.println("Caught an unexpected exception.");
			}
		}
	}

}
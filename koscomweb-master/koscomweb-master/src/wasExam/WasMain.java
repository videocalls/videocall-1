package wasExam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class WasMain {

	public static void main(String[] args) {
		try (ServerSocket listener = new ServerSocket(8888);) {
			System.out.println("client를 기다림");
			while (true) {
				Socket client = listener.accept();
				new Thread(()->{
					handleSocket(client);
				}).start();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void handleSocket(Socket client) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));){
			String line = null;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

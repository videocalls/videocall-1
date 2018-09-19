package wasExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class WasMain2 {

	public static void main(String[] args) {
		try (ServerSocket listener = new ServerSocket(8888);) {
			System.out.println("client를 기다림");
			while (true) {
				Socket client = listener.accept();
				new Thread(() -> {
					//System.out.println(client);
					handleSocket(client);
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void handleSocket(Socket client) {
		String line=null;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));){
			while((line = br.readLine())!=null) {
				System.out.println(line);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package kr.co.koscom.web;

import java.util.HashMap;
import java.util.Map;

public class Database {
	private static Map<String, user> users = new HashMap<String, user>();
	public static void addUser(user user1) {
		users.put(user1.getUserId(), user1);
	}
}



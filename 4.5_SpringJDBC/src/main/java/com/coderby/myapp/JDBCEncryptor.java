package com.coderby.myapp;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JDBCEncryptor {

	public static void main(String[] args) {
        StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
        enc.setPassword("rktwlsrud");
        System.out.println(enc.encrypt("oracle.jdbc.driver.OracleDriver"));
        System.out.println(enc.encrypt("jdbc:oracle:thin:@localhost:1521:xe"));
        System.out.println(enc.encrypt("hr"));//id
        System.out.println(enc.encrypt("hr"));//pw
	}
}
package kr.co.koscom.oppf.cmm.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class CommonUtil {
	public static String generateVtAccountNo() {
        SecureRandom secureRandom = null;
	    try {
	        secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        	return null;
        }
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
	
		return String.format("%s%09d00001", sdf.format(new Date()), secureRandom.nextInt(999999999));
	}

	public static String generateRealAccountNo() {
        SecureRandom secureRandom = null;
	    try {
	        secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        	return null;
        }
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
	
		return String.format("%s%04d", sdf.format(new Date()), secureRandom.nextInt(9999));
	}
	


	public static String checkServerType(String type) {
		// type opr1 or opr2 N 그 외의 경우 Y
		String rtnType = "N";
		if("oppr1".equals(type) || "oppr2".equals(type)){
			rtnType = "N";
		} else {
			rtnType = "Y";
		}
		return rtnType;
	}

	public static String getLocalServerIp(){
		try
		{
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
			{
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
				{
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress())
					{
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		}
		catch (SocketException ex) {}
		return null;
	}
}

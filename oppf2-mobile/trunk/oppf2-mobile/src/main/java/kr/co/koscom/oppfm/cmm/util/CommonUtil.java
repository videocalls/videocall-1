package kr.co.koscom.oppfm.cmm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtil {

    /**
	 * generateVtAccountNo
	 * 가상계좌번호 생성
	 * @return
	 */
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

	/**
	 * generateRealAccountNo
	 * 실계좌번호 생성
	 * 
	 * @return
	 */
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
	
	/**
	 * getLocalServerIp()
	 * 로컬 서버 IP 정보를 가져온다.
	 * 
	 * @return
	 */
//	public static String getLocalServerIp(){
//		try
//		{
//			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
//			{
//				NetworkInterface intf = en.nextElement();
//				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
//				{
//					InetAddress inetAddress = enumIpAddr.nextElement();
//					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress())
//					{
//					    log.debug("inetAddress.getHostAddress().toString() :: " + inetAddress.getHostAddress().toString());
//						return inetAddress.getHostAddress().toString();
//					}
//				}
//			}
//		}
//		catch (SocketException ex) {}
//		return null;
//	}
	
    /**
     * 현재 프로파일 가져오기
     * 
     * @return
     */
    public static String currentProfile() {
        // log.debug("System.getProperty('spring.profiles.active')="+System.getProperty("spring.profiles.active"));
        String profile = System.getProperty("spring.profiles.active");

        if (profile == null) {
            profile = "local";
        }
        log.debug("System.getProperty('spring.profiles.active')=" + profile);

        return profile;
    }
    
    /**
     * 객체가 Null 인지 확인한다.
     *
     * @param object the object
     * @return Null인경우 true / Null이 아닌경우 false
     */
    public static boolean isNull(Object object) {
        return ((object == null) || object.equals(null));
    }

    
    /**
     * tomcat ROOT 경로를 가져온다.
     * @return
     */
    public static String getRealPath() {
        String realPaht = "";
        try {

//            log.debug( CommonUtil.class.getResource("").getPath() );
            String filePath = ResourceUtils.getFile("classpath:config/config-common.properties").getPath();
//            log.debug(filePath);
            
            realPaht = filePath.substring(0 ,(filePath.indexOf("WEB-INF")-1) );
            
//            log.debug(realPaht);

        } catch (Exception e) {
            log.debug("read string from file error \n {}" ,e);
        } finally {
        }   
        return realPaht;
    }

    /**
     * file의 내용을 반환.
     * @param fileName
     * @return
     */
    public static String readStringFromFile(String fileName) {
        String returnBody = null;
        BufferedReader bufferedReader = null;
        FileInputStream fis = null;
        try {

            File file = new File(getRealPath() + fileName);
            
            log.debug(file.getPath());
//            log.debug(file.getAbsolutePath());
            
//            bufferedReader = new BufferedReader(new FileReader(file));
            fis = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fis ,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            returnBody = sb.toString();
        } catch (Exception e) {
            log.debug("read string from file error \n {}" ,e);
        } finally {
            if(bufferedReader != null) try{bufferedReader.close();}catch(IOException e){}
            if(fis != null) try{fis.close();}catch(IOException e){}
        }
        return returnBody;
    }
    
    /**
     * file의 내용을 반환.
     * @param classPathFileName  ex(classpath:config/config-common.properties)
     * @return
     */
    public static String readStringFromResourceFile(String classPathFileName) {
        String returnBody = null;
        BufferedReader bufferedReader = null;
        FileInputStream fis = null;
        try {

            File file = ResourceUtils.getFile(classPathFileName);

            log.debug(file.getPath());
//            log.debug(file.getAbsolutePath());
            fis = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            returnBody = sb.toString();
        } catch (Exception e) {
            log.debug("read string from file error \n {}", e);
        } finally {
                if(bufferedReader != null) try{bufferedReader.close();}catch(IOException e){}
                if(fis != null) try{fis.close();}catch(IOException e){}
        }
        return returnBody;
    }

    public static Long isNullToLong(Long value){
        Long returnLong = 0L;
        if(value != null)
            returnLong = value;
        return  returnLong;
    }

    /**
     증권사별 계좌 조회 API 요청 IP 정보 처리
     * @param endPoint
     * @return
     */
    public static String getAssetType(String endPoint){
        String assetType = "ALL";

        //대신증권 IP 예외처리
        if(endPoint.indexOf("ebest") > 0){
            assetType = "CASH";
        }

        return assetType;
    }

    /**
     증권사별 계좌 조회 API 요청 구분 처리
     * @param endPoint
     * @return
     */
    public static String getRequestIp(String endPoint, String serverNslookupIp){
//        String returnIp = CommonUtil.getLocalServerIp();
        String returnIp = serverNslookupIp;

        //대신증권 IP 예외처리
        if(endPoint.indexOf("daishin") > 0){
            returnIp = "192168010001";
        }

        return returnIp;
    }

}

package kr.co.koscom.oppf.cmm.service;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : OppfProperties.java
* @Comment  : properties값들을 파일로부터 읽어와   
* 			  Globals클래스의 정적변수로 로드시켜주는 클래스로 문자열 정보 기준으로 
* 			    사용할 전역변수를 시스템 재시작으로 반영할 수 있도록 한다.
* @author   : 신동진
* @since    : 2016.04.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.28  신동진        최초생성
*
*/
@Slf4j
public class OppfProperties{

	//프로퍼티값 로드시 에러발생하면 반환되는 에러문자열
	public static final String ERR_CODE =" EXCEPTION OCCURRED";
	public static final String ERR_CODE_FNFE =" EXCEPTION(FNFE) OCCURRED";
	public static final String ERR_CODE_IOE =" EXCEPTION(IOE) OCCURRED";

	//파일구분자
    static final char FILE_SEPARATOR     = File.separatorChar;

	//프로퍼티 파일의 물리적 위치
    public static final String RELATIVE_PATH_PREFIX = OppfProperties.class.getResource("").getPath()
    + System.getProperty("file.separator") + ".." 
    + System.getProperty("file.separator") + ".."
    + System.getProperty("file.separator") + ".."
    + System.getProperty("file.separator") + ".."
    + System.getProperty("file.separator") + ".."
    + System.getProperty("file.separator") + ".." + System.getProperty("file.separator");

    //resources\props\globals.properties
    public static final String GLOBALS_PROPERTIES_FILE = RELATIVE_PATH_PREFIX 
    + "resources" + System.getProperty("file.separator") + "props" 
    + System.getProperty("file.separator") + "globals.properties";

    
	/**
	 * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다(Globals.java 전용)
	 * @param keyName String
	 * @return String
	*/
	public static String getProperty(String keyName){
		String value = ERR_CODE;
		value="99";
		debug(GLOBALS_PROPERTIES_FILE + " : " + keyName);
		FileInputStream fis = null;
		try{
			Properties props = new Properties();
			fis  = new FileInputStream(GLOBALS_PROPERTIES_FILE);
			props.load(new java.io.BufferedInputStream(fis));
			//한글 깨짐 방지
//			value = new String(props.getProperty(keyName).trim().getBytes("ISO-8859-1"), "UTF-8");
			value = props.getProperty(keyName).trim();
		}catch(FileNotFoundException fne){
			debug(fne);
		}catch(IOException ioe){
			debug(ioe);
		}catch(Exception e){
			debug(e);
		}finally{
			try {
				if (fis != null) fis.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return value;
	}

	/**
	 * 주어진 프로파일의 내용을 파싱하여 (key-value) 형태의 구조체 배열을 반환한다.
	 * @param property String
	 * @return ArrayList
	 */
	@SuppressWarnings("unused")
	public static ArrayList<Map<String, String>> loadPropertyFile(String property){

		// key - value 형태로 된 배열 결과
		ArrayList<Map<String, String>> keyList = new ArrayList<Map<String, String>>();

		String src = property.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		FileInputStream fis = null;
		try
		{

			File srcFile = new File(src);
			if (srcFile.exists()) {

				java.util.Properties props = new java.util.Properties();
				fis  = new FileInputStream(src);
				props.load(new java.io.BufferedInputStream(fis));
				fis.close();

				int i = 0;
				Enumeration<?> plist = props.propertyNames();
				if (plist != null) {
					while (plist.hasMoreElements()) {
						Map<String, String> map = new HashMap<String, String>();
						String key = (String)plist.nextElement();
						map.put(key, props.getProperty(key));
						keyList.add(map);
					}
				}
			}
		} catch (Exception ex){
			debug("EX:"+ex);
		} finally {
			try {
				if (fis != null) fis.close();
			} catch (Exception ex) {
				debug("EX:"+ex);//ex.printStackTrace();
			}
		}

		return keyList;
	}

	/**
	 * 시스템 로그를 출력한다.
	 * @param obj Object
	 */
	private static void debug(Object obj) {
		if (obj instanceof java.lang.Exception) {
			log.debug("[OppfProperties.log debug] : {} ", ((Exception)obj).getMessage());
		}
	}
}


package kr.co.koscom.oppf.cmm.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils {

    private static final Logger logger = Logger.getLogger(JsonUtils.class);

    ObjectMapper mapper;

	public JsonUtils() {
		mapper = new ObjectMapper();
	}

	public String getJsonString(Object obj) throws Exception {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("Json 문자열 생성 에러 :: " + e);
			throw new Exception("Json 문자열 생성 에러");
		}
	}

	public <T> T readValue(String jsonString, Class<T> valueType) throws Exception {
		try {
			return mapper.readValue(jsonString, valueType);
		} catch (JsonParseException e) {
            logger.error("Json 문자열 생성 에러 :: " + e);
			throw new Exception("Json 문자열 생성 에러");
		} catch (JsonMappingException e) {
            logger.error("JsonMappingException :: " + e);
			throw new Exception("JsonMappingException :: " + e);
		} catch (IOException e) {
            logger.error("IOException :: " + e);
			throw new Exception("IOException :: " + e);
		}
	}

	public static void printByte(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(i + " [" + bytes[i] + "]");
		}
	}

	public static void main(String[] aa) throws UnsupportedEncodingException {
		String str = "가나다";

		//System.out.println("euc_kr bytes");
		byte[] eucByte = str.getBytes("euc_kr");
		JsonUtils.printByte(eucByte);

		//System.out.println("utf-8 bytes");
		byte[] utfByte = str.getBytes("utf-8");
		JsonUtils.printByte(utfByte);
		
		//System.out.println("euc byte to utf String : ["+new String(eucByte,"utf-8")+"]");
		
		//System.out.println("utf byte to euc String : ["+new String(utfByte,"euc_kr")+"]");
		
		//System.out.println("euc byte to String : ["+new String(eucByte)+"]");
		
		//System.out.println("utf byte to String : ["+new String(utfByte)+"]");
		
		
		//System.out.println("euc byte to euc String : ["+new String(eucByte,"euc_kr")+"]");
		
		//System.out.println("utf byte to utf String : ["+new String(utfByte,"utf-8")+"]");
		
		
	}
}

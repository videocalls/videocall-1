package kr.co.koscom.oppfm.cmm.eversafe;

import java.security.GeneralSecurityException;

import kr.co.everspin.eversafe.keypad.SecureTextDecoder;
import kr.co.koscom.oppfm.cmm.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * eversafe keypad 관련 유틸
 * 
 * @author unionman
 *
 */

@Slf4j
public class EversafeKeypadUtil {

    /**
     * BasicDecoderSecureBuffer
     * 
     * @param secureBuffer
     * @return
     */
    public static String BasicDecoderSecureBuffer(byte[] secureBuffer) {

        try {
            SecureTextDecoder.BasicDecoder().originalTextForSecureBuffer(secureBuffer);
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }

    /**
     * BasicDecoderSecureText
     * 
     * @param secureText
     * @return
     */
    public static String BasicDecoderSecureText(String secureText) {
        try {
            return SecureTextDecoder.BasicDecoder().originalTextForSecureText(secureText);
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }

    /**
     * SeedDecoderSecureBuffer
     * 
     * @param secureBuffer
     * @return
     */
    public static String SeedDecoderSecureBuffer(byte[] secureBuffer) {
        byte[] seedKey = null;

        try {
            SecureTextDecoder.SeedDecoder(seedKey).originalTextForSecureBuffer(secureBuffer);
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }

    /**
     * SeedDecoderSecureText
     * 
     * @param secureText
     * @return
     */
    public static String SeedDecoderSecureText(String secureText) {
        byte[] seedKey = null;
        try {
            return SecureTextDecoder.SeedDecoder(seedKey).originalTextForSecureText(secureText);
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }

    /**
     * RSADecoderSecureBuffer
     * 
     * @param secureBuffer
     * @return
     */
    public static String RSADecoderSecureBuffer(byte[] secureBuffer) {
        String seedKey = "";

        try {
            SecureTextDecoder.RSADecoder(seedKey).originalTextForSecureBuffer(secureBuffer);
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 보안 키패드 복호화
     * 
     * @param secureText
     * @return
     * @throws GeneralSecurityException 
     */
    public static String RSADecoderSecureText(String secureText) throws GeneralSecurityException {

        String rsaPrivateKey = CommonUtil.readStringFromResourceFile("classpath:config/security/rsaprivatekey.txt");
//        String rsaPublicKey = CommonUtil.readStringFromResourceFile("classpath:config/security/rsapublickey.txt");
        String originalText = "";
        
//        try {
            SecureTextDecoder secureTextDecoder =  SecureTextDecoder.RSADecoder(rsaPrivateKey);
            originalText = secureTextDecoder.originalTextForSecureText(secureText);

//            return SecureTextDecoder.RSADecoder(rsaPrivateKey).originalTextForSecureText(secureText);
//        } catch (GeneralSecurityException e) {
//            // TODO Auto-generated catch block
//            log.debug( e.getMessage() );
//            e.printStackTrace();
//        }

        return originalText;
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        /**
         * yobi scm
         * http://10.10.10.104/oppf2/oppf2-MobileApp/issue/3#comment-1730
         */
        
        try {
            System.out.println(RSADecoderSecureText(
                    "AAAAAEPqUuBRqTg6teknT1zNjDt4HDL+ZfAu6md4bkyEnFepEV20Km3taMEWQga38YmGMJ5gtfSPZB8ft3MEpr51ASehYmVzMpGIJiRsjFjGKKIpfdbvn5dRucX31hqS5H9uRoZDCtsvBEBGMSsW0tcGBgCO/Q9YTmXI7wDd2ZnLpb0WLG2Ahk3xARB1nHXgzPIID63wvoWV5gVBaBCXrqnpGvAJIySu0FNYYBqskBRhIWuo0JryyOHCj/moALLJPh7QQJ1G00Y+9734fsH26ENiTso="
                    ));
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            log.debug( e.getMessage() );
        }
        
        
        
    }
    

}

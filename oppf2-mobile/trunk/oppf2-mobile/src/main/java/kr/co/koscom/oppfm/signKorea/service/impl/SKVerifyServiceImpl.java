package kr.co.koscom.oppfm.signKorea.service.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.util.CommonUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.signKorea.dao.SignKoreaMapper;
import kr.co.koscom.oppfm.signKorea.model.CustomerCertDnReq;
import kr.co.koscom.oppfm.signKorea.model.SKCustomerInfoRes;
import kr.co.koscom.oppfm.signKorea.model.SKSignedDataReq;
import kr.co.koscom.oppfm.signKorea.model.SKVerifyExtReq;
import kr.co.koscom.oppfm.signKorea.model.SKVerifyReq;
import kr.co.koscom.oppfm.signKorea.model.SKVerifyVO;
import kr.co.koscom.oppfm.signKorea.service.SKVerifyService;
import lombok.extern.slf4j.Slf4j;

/**
 * SKVerifyServiceImpl
 * <p>
 * Created by Yoojin Han on 2017-05-26.
 */

@Slf4j
@Service
public class SKVerifyServiceImpl implements SKVerifyService{
    
    final int defaultVerifydPort = 24098;
    
    @Autowired
    MessageUtil messageUtil;
    
    @Autowired
    SignKoreaMapper signKoreaMapper;

    @Value("#{config['Globals.skverifyd.ip']}")
    String globalsSkverifydIp;
    
    /* (non-Javadoc)
     * @see kr.co.koscom.oppfm.signKorea.service.SKVerifyService#verifySign(kr.co.koscom.oppfm.signKorea.model.SKVerifyReq)
     */
    @Override
    public SKVerifyVO verifySign(SKVerifyReq skVerifyReq) throws UnsupportedEncodingException{
        
        SKVerifyVO sKVerifyVO = new SKVerifyVO();
        int rs = 0;
        int rs2 = 0;
        String signKoreaMsg = "";
        
        SKSignedDataReq pSKSignedDataReq = new SKSignedDataReq();
        SKVerifyExtReq  pSKVerifyExtInfo  = new SKVerifyExtReq();
        
        
        //1.ip설정
        String verifydIp = globalsSkverifydIp;
        if(!OppfStringUtil.isEmpty(skVerifyReq.getDemonIp())){
            verifydIp = skVerifyReq.getDemonIp();
        }
        log.debug("1.ip설정:verifydIp : {} ", verifydIp);
        
        //2.포트설정
        int verifydPort = 7900;
        if(!OppfStringUtil.isEmpty(skVerifyReq.getDemonIp())){
            verifydPort = skVerifyReq.getDemonPort();
        }
        log.debug("2.포트설정:verifydPort : {} ", verifydPort);
        
        //3.data설정
        String signData = skVerifyReq.getSignData();
        log.debug("3.data설정:signData : {} ", signData);
        
        String datas = "";
        if(!OppfStringUtil.isEmpty(signData)){
            pSKSignedDataReq.setOpcode((byte) 46);
            datas = signData;
            pSKSignedDataReq.setSigned_data(datas.getBytes());
        }
        log.debug("3.data설정:datas : {} ", datas);
        
        
        //4.검증데몬으로 검증 요청함수(ip,port,검증할데이터입력클래스,검증후인증서세부정보클래스)
        rs = verifySignExt(
                                 verifydIp
                                ,verifydPort
                                ,pSKSignedDataReq
                                ,pSKVerifyExtInfo
                            );
        log.debug("4.검증데몬으로검증요청함수:rs(OCSP) : {} ", rs);
        
        //5.검증결과처리
        String rsMsg = "";
        if(rs == 0){
            rsMsg = "OCSP 성공";
            
        //OCSP 검증 실패 CRL 검증 전환처리
        }else if(rs == 1){
            log.debug("4. pSKSignedDataReq.errmsg : {} ", pSKSignedDataReq.getErrmsg());
            
            int errcode = Integer.parseInt(pSKSignedDataReq.getErrmsg().substring(0,4));
            
            signKoreaMsg += "\nOCSP code="+errcode;
            
            //OCSP 통신오류일 경우 CRL 검증으로 자동 전환
            if(4000 < errcode && errcode < 5000){
                pSKSignedDataReq.setOpcode((byte) 18);
                
                //4.검증데몬으로 검증 요청함수(ip,port,검증할데이터입력클래스,검증후인증서세부정보클래스)
                rs2 = verifySignExt(
                                     verifydIp
                                    ,verifydPort
                                    ,pSKSignedDataReq
                                    ,pSKVerifyExtInfo
                                );
                if(rs2 == 0){
                    rsMsg = "CRL 성공";
                    
                    log.debug("4. pSKVerifyExtInfo.Issuer : {} ", pSKVerifyExtInfo.getIssuer());
                    log.debug("4. pSKVerifyExtInfo.policy : {} ", pSKVerifyExtInfo.getPolicy());
                    log.debug("4. pSKVerifyExtInfo.Subject : {} ", pSKVerifyExtInfo.getSubject());
                    
                //검증실패
                }else if(rs2 == 1){
                    
                    signKoreaMsg += "\nCRL code="+errcode;
                    
                //CRL 전환 처리에서의 데몬 통신 실패
                }else{
                    
                }
                
                rs = rs2;
                
            //검증실패
            }else{
                
            }
            
        }else{
            if(!OppfStringUtil.isEmpty(pSKSignedDataReq.getErrmsg())){
                
                rsMsg = OppfStringUtil.getEncdDcd(pSKSignedDataReq.getErrmsg(),"euc-kr","UTF-8");
                rsMsg = new String(pSKSignedDataReq.getErrmsg().getBytes("MS949"), "UTF-8");
                log.debug("rsMsg : {} ", rsMsg);
                rsMsg = new String(pSKSignedDataReq.getErrmsg().getBytes("euc-kr"), "UTF-8");
                log.debug("rsMsg : {} ", rsMsg);
                rsMsg = new String(pSKSignedDataReq.getErrmsg().getBytes("8859_1"), "UTF-8");
                log.debug("rsMsg : {} ", rsMsg);
                rsMsg = new String(pSKSignedDataReq.getErrmsg().getBytes("ISO_8859_1"), "UTF-8");
                log.debug("rsMsg : {} ", rsMsg);
                rsMsg = new String(pSKSignedDataReq.getErrmsg().getBytes("KSC5601"), "UTF-8");
                log.debug("rsMsg : {} ", rsMsg);
                rsMsg = new String(pSKSignedDataReq.getErrmsg().getBytes("euc-kr"), "UTF-8");
                log.debug("rsMsg : {} ", rsMsg);
                rsMsg = new String(pSKSignedDataReq.getErrmsg().getBytes("euc-kr"), "UTF-8");
                log.debug("rsMsg : {} ", rsMsg);
                rsMsg = new String(pSKSignedDataReq.getErrmsg().getBytes("ISO-8859-1"), "UTF-8");
                log.debug("rsMsg : {} ", rsMsg);
                
            }
            log.debug("5.rs:공인인증서유효성검증실패:pSKSignedDataReq.getErrmsg() : {} ", pSKSignedDataReq.getErrmsg());
            log.debug("5.rs:공인인증서유효성검증실패:status_flag : {} ", skVerifyReq.getSkSignedDataReq().getStatus_flag());
            log.debug("5.rs:공인인증서유효성검증실패:errormsg : {} ", skVerifyReq.getSkSignedDataReq().getErrmsg());
        }
        
        log.debug("4.검증데몬으로검증요청함수:rs(CRL) : {} ", rs);
        log.debug("4.검증데몬으로검증요청함수:rsMsg : {} ", rsMsg);
        
        sKVerifyVO.setRs(rs);
        sKVerifyVO.setSignKoreaMsg(signKoreaMsg);
        sKVerifyVO.setRsMsg(rsMsg);
        
        return sKVerifyVO;
    }

    /**
     * @Method Name        : verifySign
     * @Method description : 공인인증서 서버 유효성검증 처리
     * @param              : String,int,SKSignedDataReq
     * @return             : int
     * @throws             : Exception
     */
    @Override
    public int verifySignExt(String verifydIp, int verifydPort, SKSignedDataReq pSKSignedDataReq,
            SKVerifyExtReq pSKVerifyExtReq) {

        /**
         * spring.profiles.active=local 에서 호출시 성공처리
         */
        if( OppfStringUtil.equals(CommonUtil.currentProfile(), "local")  ) {
            log.debug("****** spring.profiles.active=local 에서 호출시 성공처리!!! ******");
            return 0;
        }
        /**
         * 로컬에서 호출시 성공처리
         */
        
        
        int sk_verify_ok = 0;       // SK_VERIFY_OK
        int sk_verify_not_ok = 1;   // SK_VERIFY_NOT_OK
        int sk_verify_not_ok2 = 2;

        Socket              sock = null;
        DataOutputStream    out  = null;
        DataInputStream     in   = null;

        if(verifydIp.equals(""))
            verifydIp = "127.0.0.1";

        if(verifydPort == 0)
            verifydPort = defaultVerifydPort;

        try
        {
            sock= new Socket(verifydIp, verifydPort);
            out = new DataOutputStream(sock.getOutputStream());
            in  = new DataInputStream(sock.getInputStream());
        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host: " + verifydIp );
            return -1;
        }
        catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to: " + verifydIp);
            return -2;
        }

        try
        {
            // send SK_VERIFY_REQ

            int data_length;
            if( pSKSignedDataReq.signed_data.length == 0)
            {
                out.close(); in.close(); sock.close();
                return -5; /* data is null */
            }

            switch (pSKSignedDataReq.opcode)
            {
                case 17 : case 18 : case 37 : case 60 : default :
                    data_length = pSKSignedDataReq.signed_data.length + 1;
                    break;
            }

            {
                byte blen[] = new byte[4];
                blen[0] = (byte) (data_length >> 24 & 0x000000ff);
                blen[1] = (byte) (data_length >> 16 & 0x000000ff);
                blen[2] = (byte) (data_length >>  8 & 0x000000ff);
                blen[3] = (byte) (data_length       & 0x000000ff);

                out.write(blen, 0, 4);
            }
            out.flush();
            out.writeByte(pSKSignedDataReq.opcode);
            out.flush();

            switch (pSKSignedDataReq.opcode)
            {
                case 15 : default :
                    out.write(pSKSignedDataReq.signed_data, 0, pSKSignedDataReq.signed_data.length);
                    break;
            }
            out.flush();

            int ret_data_length = in.readInt();
            byte ret_opcode = in.readByte();

            if(ret_opcode != pSKSignedDataReq.opcode)
            {
                System.err.println("recv SK_VERIFY_REP error 1");
                System.err.println("ret_opcode" + ret_opcode);
                System.err.println("pSKSignedDataReq.opcode" + pSKSignedDataReq.opcode);
                System.err.println("recv SK_VERIFY_REP error 1");
                out.close();
                in.close();
                sock.close();
                return -3;
            }

            byte ret_status_flag = in.readByte();
            pSKSignedDataReq.status_flag  = ret_status_flag;

            if(ret_status_flag == sk_verify_not_ok)
            {
                /* add error process */
                byte[] errmsg = new byte[256];
                in.readFully(errmsg, 0, ret_data_length -2 );
                String ret_errmsg = new String(errmsg);
                pSKSignedDataReq.errmsg = ret_errmsg.trim();
                out.close();
                in.close();
                sock.close();
                return 1;   /* not ok */
            }

            if(ret_status_flag == sk_verify_not_ok2)
            {
                switch(pSKSignedDataReq.opcode)
                {
                    case 24 :
                    {
                        byte[] ret_plaintext = new byte[ret_data_length - 2];
                        in.readFully(ret_plaintext, 0, ret_data_length - 2);
                        StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                        int st_count = st.countTokens();
                        pSKVerifyExtReq.version = new String(st.nextToken());
                        pSKVerifyExtReq.serial = new String(st.nextToken());
                        pSKVerifyExtReq.issuer = new String(st.nextToken());
                        pSKVerifyExtReq.subject = new String(st.nextToken());
                        pSKVerifyExtReq.from = new String(st.nextToken());
                        pSKVerifyExtReq.to = new String(st.nextToken());
                        pSKVerifyExtReq.policy = new String(st.nextToken());
                        pSKVerifyExtReq.distributionPoints = new String(st.nextToken());
                        pSKVerifyExtReq.authorityKeyId = new String(st.nextToken());
                        pSKVerifyExtReq.subjectKeyId = new String(st.nextToken());
                        pSKVerifyExtReq.publicKey = new String(st.nextToken());
                        String plain_tmp = new String(st.nextToken(""));
                        String plain = plain_tmp.substring(1);
                        pSKSignedDataReq.plaintext = plain.getBytes();
                        pSKSignedDataReq.signer_dn = pSKVerifyExtReq.subject;
                        break;
                     }
                }
                out.close();
                in.close();
                sock.close();
                return 2;
            }
            switch(pSKSignedDataReq.opcode)
            {
                case 17 : case 18 : case 37 : case 60 : case 38 :
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKVerifyExtReq.version = new String(st.nextToken());
                    pSKVerifyExtReq.serial = new String(st.nextToken());
                    pSKVerifyExtReq.issuer = new String(st.nextToken());
                    pSKVerifyExtReq.subject = new String(st.nextToken());
                    pSKVerifyExtReq.from = new String(st.nextToken());
                    pSKVerifyExtReq.to = new String(st.nextToken());
                    pSKVerifyExtReq.policy = new String(st.nextToken());
                    pSKVerifyExtReq.distributionPoints = new String(st.nextToken());
                    pSKVerifyExtReq.authorityKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.subjectKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.publicKey = new String(st.nextToken());
                    String plain_tmp = new String(st.nextToken(""));
                    String plain = plain_tmp.substring(1);
                    pSKSignedDataReq.plaintext = plain.getBytes();
                    pSKSignedDataReq.signer_dn = pSKVerifyExtReq.subject;
                    break;
                }
                case 23 :
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKVerifyExtReq.subject = new String(st.nextToken());
                    String plain_tmp = new String(st.nextToken(""));
                    String plain = plain_tmp.substring(1);
                    pSKSignedDataReq.plaintext = plain.getBytes();
                    pSKSignedDataReq.signer_dn = pSKVerifyExtReq.subject;
                    break;
                }

                case 24 : case 26 :case 28 : case 44 : case 46 :case 48 :
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKVerifyExtReq.version = new String(st.nextToken());
                    pSKVerifyExtReq.serial = new String(st.nextToken());
                    pSKVerifyExtReq.issuer = new String(st.nextToken());
                    pSKVerifyExtReq.subject = new String(st.nextToken());
                    pSKVerifyExtReq.from = new String(st.nextToken());
                    pSKVerifyExtReq.to = new String(st.nextToken());
                    pSKVerifyExtReq.policy = new String(st.nextToken());
                    pSKVerifyExtReq.distributionPoints = new String(st.nextToken());
                    pSKVerifyExtReq.authorityKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.subjectKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.publicKey = new String(st.nextToken());
                    String plain_tmp = new String(st.nextToken(""));
                    String plain = plain_tmp.substring(1);
                    pSKSignedDataReq.plaintext = plain.getBytes();
                    pSKSignedDataReq.signer_dn = pSKVerifyExtReq.subject;
                    break;
                }
                case 25 :  case 27 :case 29 : case 45 : case 47  :case 49 : case 77 :
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKVerifyExtReq.version = new String(st.nextToken());
                    pSKVerifyExtReq.serial = new String(st.nextToken());
                    pSKVerifyExtReq.issuer = new String(st.nextToken());
                    pSKVerifyExtReq.subject = new String(st.nextToken());
                    pSKVerifyExtReq.from = new String(st.nextToken());
                    pSKVerifyExtReq.to = new String(st.nextToken());
                    pSKVerifyExtReq.policy = new String(st.nextToken());
                    pSKVerifyExtReq.distributionPoints = new String(st.nextToken());
                    pSKVerifyExtReq.authorityKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.subjectKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.publicKey = new String(st.nextToken());
                    String plain_tmp = new String(st.nextToken(""));
                    String plain = plain_tmp.substring(1);
                    pSKSignedDataReq.plaintext = plain.getBytes();
                    pSKSignedDataReq.signer_dn = pSKVerifyExtReq.subject;
                    break;
                }
                case 40:
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);

                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();

                    pSKSignedDataReq.signer_dn = new String(st.nextToken());
                    break;
                }
                case 41:
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKSignedDataReq.signer_dn = new String(st.nextToken());
                    break;
                }
                case 42:
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKSignedDataReq.signer_dn = new String(st.nextToken());
                    break;
                }
                case 61:
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKSignedDataReq.signer_dn = new String(st.nextToken());
                    break;
                }
                case 62: /* Case62 is added 2007.04.02 by Hotspirit */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKSignedDataReq.signer_dn = new String(st.nextToken());
                    break;
                }
                case 63: /* Case63 is added 2006.02.15 by Hotspirit */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKSignedDataReq.signer_dn = new String(st.nextToken());
                    break;
                }
                case 64: /* Case64 is added 2006.12.08 by Hotspirit */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKSignedDataReq.signer_dn = new String(st.nextToken());
                    break;
                }
                case 91: /* Case64 is added 2006.12.08 by Hotspirit */
                {
                    if( ret_data_length > 2 + 128 + 140 ) /* 2048 ? */
                    {
                        byte[] signer_dn = new byte[256];
                        in.readFully(signer_dn, 0, 256);
                        byte[] ret_publicKey = new byte[ret_data_length -2 - 256];
                        in.readFully(ret_publicKey, 0, ret_data_length -2 - 256);
                        pSKSignedDataReq.status_flag  = ret_status_flag;
                        pSKSignedDataReq.signer_dn    = new String(signer_dn);
                        pSKSignedDataReq.publickey    = ret_publicKey;
                    }
                    else
                    {
                        byte[] signer_dn = new byte[128];
                        in.readFully(signer_dn, 0, 128);
                        byte[] ret_publicKey = new byte[ret_data_length -2 - 128];
                        in.readFully(ret_publicKey, 0, ret_data_length -2 - 128);
                        pSKSignedDataReq.status_flag  = ret_status_flag;
                        pSKSignedDataReq.signer_dn    = new String(signer_dn);
                        pSKSignedDataReq.publickey    = ret_publicKey;
                    }
                    break;
                }
                case 50 : /* add by hotspirit 2006.07.21 */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKSignedDataReq.signer_dn = new String(st.nextToken());
                    String plain = new String(st.nextToken());
                    pSKSignedDataReq.plaintext = plain.getBytes();
                    break;
                }
                case 52 : /* add by hotspirit 2007.11.06 */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKVerifyExtReq.version = new String(st.nextToken());
                    pSKVerifyExtReq.serial = new String(st.nextToken());
                    pSKVerifyExtReq.issuer = new String(st.nextToken());
                    pSKVerifyExtReq.subject = new String(st.nextToken());
                    pSKVerifyExtReq.from = new String(st.nextToken());
                    pSKVerifyExtReq.to = new String(st.nextToken());
                    pSKVerifyExtReq.policy = new String(st.nextToken());
                    pSKVerifyExtReq.distributionPoints = new String(st.nextToken());
                    pSKVerifyExtReq.authorityKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.subjectKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.publicKey = new String(st.nextToken());
                    String plain = new String(st.nextToken());
                    pSKSignedDataReq.plaintext = plain.getBytes();
                    pSKSignedDataReq.signer_dn = pSKVerifyExtReq.subject;
                    break;
                }
                case 51 : /* add by hotspirit 2007.11.06 */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKVerifyExtReq.version = new String(st.nextToken());
                    pSKVerifyExtReq.serial = new String(st.nextToken());
                    pSKVerifyExtReq.issuer = new String(st.nextToken());
                    pSKVerifyExtReq.subject = new String(st.nextToken());
                    pSKVerifyExtReq.from = new String(st.nextToken());
                    pSKVerifyExtReq.to = new String(st.nextToken());
                    pSKVerifyExtReq.policy = new String(st.nextToken());
                    pSKVerifyExtReq.distributionPoints = new String(st.nextToken());
                    pSKVerifyExtReq.authorityKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.subjectKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.publicKey = new String(st.nextToken());
                    String plain = new String(st.nextToken());
                    pSKSignedDataReq.plaintext = plain.getBytes();
                    pSKSignedDataReq.signer_dn = pSKVerifyExtReq.subject;
                    break;
                }
                case 101: /* SignKorea 내부용  PKCS#7 서명  리턴은 서명데이터, 서명에 사용된 인증서 값*/
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    /*in.read(ret_plaintext, 0, ret_data_length - 2);*/
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    pSKSignedDataReq.signer_dn = new String(st.nextToken());
                    pSKSignedDataReq.certificate = new String(st.nextToken());
                    break;
                }
                case 102 : /* SignKorea 내부용  PKCS#7 CRL 검증  */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    //응답에서 원문만
                    byte[] plain = new byte[ret_plaintext.length - 4591];
                    System.arraycopy(ret_plaintext, 4591, plain, 0, plain.length);
                    pSKSignedDataReq.plaintext = plain;
                    byte[] info = new byte[4591];
                    System.arraycopy(ret_plaintext, 0, info, 0, 4591);
                    StringTokenizer st = new StringTokenizer(new String(info), "$");
                    int st_count = st.countTokens();
                    pSKVerifyExtReq.version = new String(st.nextToken());
                    pSKVerifyExtReq.serial = new String(st.nextToken());
                    pSKVerifyExtReq.issuer = new String(st.nextToken());
                    pSKVerifyExtReq.subject = new String(st.nextToken());
                    pSKVerifyExtReq.from = new String(st.nextToken());
                    pSKVerifyExtReq.to = new String(st.nextToken());
                    pSKVerifyExtReq.policy = new String(st.nextToken());
                    pSKVerifyExtReq.distributionPoints = new String(st.nextToken());
                    pSKVerifyExtReq.authorityKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.subjectKeyId = new String(st.nextToken());
                    pSKVerifyExtReq.publicKey = new String(st.nextToken());
                    pSKSignedDataReq.signer_dn = pSKVerifyExtReq.subject;
                    break;
                }
            }
            out.close();
            in.close();
            sock.close();

        }
        catch (NoSuchElementException ne)
        {
            ne.printStackTrace();
            //System.out.println("error: " + ne.toString());
            return -4;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            //System.out.println(e.toString());
            return -4;
        }
        return 0;
    }
    


    /**
     * DN 값으로 회원ID 조회 getCustomerId
     */
    @Override
    public String getCustomerId(String customerDn) {
        try {
            SKCustomerInfoRes skCustomerInfoRes = signKoreaMapper.selectCustomerId(customerDn, null);
            
            // 결과값 없을 경우 ( ex.비회원 ) 
            if(skCustomerInfoRes == null){
                return null;
            
            // 결과값 있을 경우 
            }else{
                String customerId = skCustomerInfoRes.getCustomerId();
                log.debug("customerId : {}", customerId);
                return customerId;
            }
        }catch(Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    /* (non-Javadoc)
     * @see kr.co.koscom.oppfm.signKorea.service.SKVerifyService#getCustomerDn(java.lang.String)
     */
    @Override
    public String getCustomerDn(String customerRegNo) {
        SKCustomerInfoRes skCustomerInfoRes = signKoreaMapper.selectCustomerId(null, customerRegNo);
        if( skCustomerInfoRes == null) {
            skCustomerInfoRes = new SKCustomerInfoRes();
        }
        return skCustomerInfoRes.getCustomerDn();
    }
    
    @Override
    public int updateCustomerDn(CustomerCertDnReq customerCertDnReq) {
        int resultCnt = signKoreaMapper.updateCustomerDn(customerCertDnReq);
        if( resultCnt == 0 ) {
            resultCnt = signKoreaMapper.insertCustomerDn(customerCertDnReq);
        }
        return resultCnt;
    }
    
}

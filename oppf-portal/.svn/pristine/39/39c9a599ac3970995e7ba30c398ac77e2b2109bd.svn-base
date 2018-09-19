package kr.co.koscom.oppf.cmm.signkorea.service.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.cmm.signkorea.service.SKCipherInfo;
import kr.co.koscom.oppf.cmm.signkorea.service.SKIvsmcCert;
import kr.co.koscom.oppf.cmm.signkorea.service.SKSignedDataInfo;
import kr.co.koscom.oppf.cmm.signkorea.service.SKVerifyExtInfo;
import kr.co.koscom.oppf.cmm.signkorea.service.SKVerifyService;
import lombok.extern.slf4j.Slf4j;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SKVerifyServiceImpl.java
* @Comment  : 공통메뉴 관리를 위한 Service Impl 클래스
* @author   : 이환덕
* @since    : 2016.05.24
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  이환덕        최초생성
*
*/
@Slf4j
@Service("SKVerifyServiceImpl")
public class SKVerifyServiceImpl implements SKVerifyService{
    
    final int default_verifyd_port = 24098;
    
    public int verifySign(String verifyd_ip, int verifyd_port, SKSignedDataInfo result){

        int sk_verify_ok    = 0;        // SK_VERIFY_OK
        int sk_verify_not_ok= 1;    // SK_VERIFY_NOT_OK
        int ret_data_length = 0;

        Socket              sock= null;
        DataOutputStream    out = null;
        DataInputStream     in  = null;

        if(verifyd_ip.equals(""))
            verifyd_ip  = "127.0.0.1";

        if(verifyd_port == 0)
            verifyd_port = default_verifyd_port;

        try{
            sock = new Socket(verifyd_ip, verifyd_port);
            // read timeout
            sock.setSoTimeout(10000);
            out = new DataOutputStream(sock.getOutputStream());
            in  = new DataInputStream(sock.getInputStream());
            
        }catch (UnknownHostException e){
            log.error("Don't know about host: " + verifyd_ip );
            return -1;
        }catch (IOException e){
            log.error("Couldn't get I/O for the connection to: " + verifyd_ip);
            return -2;
        }

        try{
            // send SK_VERIFY_REQ

            int data_length;
            String gubun_flag = "$";
            String nullchar = "0000";
            byte[]  pub = new byte[281];
            byte[]  sign= new byte[281];
            switch (result.opcode){
                case 10 : case 11 : case 12 : case 13 : case 14: case 15: default :
                    data_length = result.signed_data.length + 1;
                    if( data_length == 1 ){
                        out.close();
                        in.close();
                        sock.close();
                        return -5;
                    }
                    break;
                case 16 :
                    if( (result.signed_data.length == 0 ) || (result.publickey.length == 0)){
                        out.close();
                        in.close();
                        sock.close();
                        return -5;
                    }
                    data_length = result.signed_data.length + 1 + result.publickey.length + 1;
                    break;
                case 19 :
                    if( (result.signed_data.length == 0 ) || (result.plaintext.length == 0) || (result.publickey.length == 0) || (result.publickey.length < 138)){
                        out.close();
                        in.close();
                        sock.close();
                        return -5;
                    }
                    /*data_length = result.signed_data.length + 1 + result.plaintext.length + 1 + 140 + 1;*/

                    if( result.publickey.length > 140){// 2048 Data
                        data_length = 256 + 1 + result.plaintext.length + 1 + 270 + 1;
                    }else{ // 1024 Data
                        data_length = 128 + 1 + result.plaintext.length + 1 + 140 + 1;
                    }
                    break;
                    
                case 20: case 21: case 22:
                    if( (result.signed_data.length == 0 ) || (result.plaintext.length == 0) || (result.publickey.length == 0)){
                        out.close();
                        in.close();
                        sock.close();
                        return -5;
                    }
                    data_length =
                    result.signed_data.length + 1 + result.plaintext.length + 1 + result.publickey.length + 1;
                    break;
                    
                case 9 :
                    data_length =
                    //result.signer_dn.length() +
                    230 + result.plaintext.length + 1;
                    break;
                    
                case 8 :
                    data_length = result.publickey.length + 1;
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
            out.writeByte(result.opcode);
            out.flush();
            switch (result.opcode) {
                case 10 : case 11 : case 12 : case 13: case 14: case 15: default :
                    out.write(result.signed_data, 0, result.signed_data.length);
                    break;
                case 16 :
                    out.write(result.publickey, 0, result.publickey.length);
                    out.write(gubun_flag.getBytes(), 0, 1);
                    out.write(result.signed_data, 0, result.signed_data.length);
                    break;
                case 19 :
                    /*
                    if( result.publickey.length < 140 )
                    {
                        System.arraycopy(result.publickey, 0, pub, 0, result.publickey.length);
                        System.arraycopy(nullchar.getBytes(), 0, pub, result.publickey.length, 140- result.publickey.length);
                        out.write(pub, 0, 140);
                    }
                    */
                    if( result.publickey.length > 140 )  // 2048 pubkey
                    {
                        if( result.publickey.length < 270 )
                        {
                            System.arraycopy(result.publickey, 0, pub, 0, result.publickey.length);
                            System.arraycopy(nullchar.getBytes(), 0, pub, result.publickey.length, 270 - result.publickey.length);
                            out.write(pub, 0, 270);
                        }
                        else
                        {
                            out.write(result.publickey, 0, result.publickey.length);
                        }
                    }else{
                        if( result.publickey.length < 140 )  // 1024 pubkey
                        {
                            System.arraycopy(result.publickey, 0, pub, 0, result.publickey.length);
                            System.arraycopy(nullchar.getBytes(), 0, pub, result.publickey.length, 140- result.publickey.length);
                            out.write(pub, 0, 140);
                        }
                        else
                        {
                            out.write(result.publickey, 0, result.publickey.length);
                        }

                    }
                    out.write(gubun_flag.getBytes(), 0, 1);
                    out.write(result.signed_data, 0, result.signed_data.length);
                    out.write(gubun_flag.getBytes(), 0, 1);
                    out.write(result.plaintext, 0, result.plaintext.length);
                    break;
                case 20 : case 21 :  case 22 :
                    out.write(result.publickey, 0, result.publickey.length);
                    out.write(gubun_flag.getBytes(), 0, 1);
                    out.write(result.signed_data, 0, result.signed_data.length);
                    out.write(gubun_flag.getBytes(), 0, 1);
                    out.write(result.plaintext, 0, result.plaintext.length);
                    break;
                case 9 :
                    for ( int i=result.signer_dn.length(); i < 230; i ++)
                    {
                        result.signer_dn = result.signer_dn + " ";
                    }
                    out.write(result.plaintext, 0, result.plaintext.length);
                    out.write(result.signer_dn.getBytes(), 0, 230);
                    break;
                case 8 :
                    out.write(result.publickey, 0, result.publickey.length);
            }
            out.flush();

            // recv SK_VERIFY_REP
            // MSG : length + data

            try{
                ret_data_length = in.readInt();
            }catch (EOFException e){
                e.printStackTrace();
                //System.out.println(e.toString());
                return -5;
            }
            // opcode(1) + status_flag(1) + signer_dn (256) + plaintext

            byte ret_opcode = in.readByte();

            if(ret_opcode != result.opcode)
            {
                log.error("recv SK_VERIFY_REP error 1");
                out.close(); in.close(); sock.close();
                return -3;
            }

            byte ret_status_flag = in.readByte();
            result.status_flag  = ret_status_flag;
            if(ret_status_flag != sk_verify_ok)
            {
                /* add error process */
                byte[] errmsg = new byte[256];
                in.readFully(errmsg, 0, ret_data_length -2 );
                String ret_errmsg = new String(errmsg);
                result.errmsg = ret_errmsg.trim();
                out.close(); in.close(); sock.close();
                return 1;   /* not ok */
            }

            switch(result.opcode)
            {
                case 10 : case 11 : case 30 : default :
                {
                    byte[] signer_dn = new byte[256];
                    in.readFully(signer_dn, 0, 256);
                    String ret_signer_dn = new String(signer_dn);
                    byte[] ret_plaintext = new byte[ret_data_length -2 - 256];
                    in.readFully(ret_plaintext, 0, ret_data_length -2 - 256);
                    result.status_flag  = ret_status_flag;
                    result.signer_dn    = ret_signer_dn.trim();
                    result.plaintext    = ret_plaintext;
                    break;
                }
                case 12 : case 13 : case 32 : case 33 : case 14 : case 34 : case 15 : case 35 :
                {
                    byte[] ret_data = new byte[ret_data_length -2];
                    in.readFully(ret_data, 0, ret_data_length -2);
                    StringTokenizer st = new StringTokenizer(new String(ret_data), "$");
                    int st_count = st.countTokens();
                    String signerdn_tmp = new String(st.nextToken());
                    String pubkey_tmp = new String(st.nextToken());
                    String plain_tmp = new String(st.nextToken());
                    String plain = plain_tmp.substring(0);
                    result.signer_dn = signerdn_tmp;
                    result.publickey = pubkey_tmp.getBytes();
                    result.plaintext = plain.getBytes();
                    /*
                    byte[] signer_dn = new byte[256];
                    in.readFully(signer_dn, 0, 256);
                    String ret_signer_dn = new String(signer_dn);
                    byte[] ret_publickey = new byte[540];
                    in.readFully(ret_publickey, 0, 540);
                    byte[] ret_plaintext = new byte[ret_data_length -2 - 256 - 540];
                    in.readFully(ret_plaintext, 0, ret_data_length -2 - 256 - 540);
                    result.status_flag  = ret_status_flag;
                    result.signer_dn    = ret_signer_dn.trim();
                    result.publickey    = ret_publickey;
                    result.plaintext    = ret_plaintext;
                    */
                    break;
                }
                case 16 :
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    result.status_flag  = ret_status_flag;
                    result.plaintext    = ret_plaintext;
                    break;
                }
                case 8 : case 9 :  case 19: case 20: case 21: case 22:
                {
                    result.status_flag  = ret_status_flag;
                    break;
                }
            }

            out.close();
            in.close();
            sock.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            //System.out.println(e.toString());
            return -4;
        }
        return 0;
    }

    public int setValidateOp(String verifyd_ip, int verifyd_port, String Op){
        byte                opcode;
        Socket              sock = null;
        DataOutputStream    out  = null;

        if(verifyd_ip.equals(""))
            verifyd_ip = "127.0.0.1";

        if(verifyd_port == 0)
            verifyd_port = default_verifyd_port;

        opcode = 99;

        try
        {
            sock = new Socket(verifyd_ip, verifyd_port);
            out  = new DataOutputStream(sock.getOutputStream());
        }
        catch (UnknownHostException e)
        {
            log.error("Don't know about host: " + verifyd_ip );
            return -1;
        }
        catch (IOException e)
        {
            log.error("Couldn't get I/O for the connection to: " + verifyd_ip);
            return -2;
        }
        try
        {
            // send SK_VERIFY_REQ
            int data_length = 2;
            {
                byte blen[] = new byte[4];
                blen[0] = (byte) (data_length >> 24 & 0x000000ff);
                blen[1] = (byte) (data_length >> 16 & 0x000000ff);
                blen[2] = (byte) (data_length >>  8 & 0x000000ff);
                blen[3] = (byte) (data_length       & 0x000000ff);
                out.write(blen, 0, 4);
            }
            out.flush();
            out.writeByte(opcode);
            out.flush();
            out.write(Op.getBytes(), 0, 1);
            out.close();
            sock.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
           //System.out.println(e.toString());
            return -4;
        }
        return 0;
    }

    public int cipher(String demon_ip, int demon_port, SKCipherInfo cipherInfo)
    {
        int sk_verify_not_ok = 1;   // SK_VERIFY_NOT_OK

        byte    signed_data[];
        String  pSignDataPEM  = "M";

        Socket              sock= null;
        DataOutputStream    out = null;
        DataInputStream     in  = null;

        if(demon_ip.equals(""))
            demon_ip = "127.0.0.1";

        if(demon_port == 0)
            demon_port = default_verifyd_port;

        try
        {
            sock= new Socket(demon_ip, demon_port);
            out = new DataOutputStream(sock.getOutputStream());
            in  = new DataInputStream(sock.getInputStream());
        }
        catch (UnknownHostException e)
        {
            log.error("Don't know about host: " + demon_ip );
            return -1;
        }
        catch (IOException e)
        {
            log.error("Couldn't get I/O for the connection to: " + demon_ip);
            return -2;
        }

        try
        {
            if(cipherInfo.opcode == 77)
            {
                cipherInfo.envelopedData = pSignDataPEM.getBytes();
            }
            // send SK_VERIFY_REQ
            int data_length = 0;

            if(cipherInfo.opcode == 121)
            {
                data_length = cipherInfo.sid.length() + 1;
                byte blen[] = new byte[4];
                blen[0] = (byte) (data_length >> 24 & 0x000000ff);
                blen[1] = (byte) (data_length >> 16 & 0x000000ff);
                blen[2] = (byte) (data_length >>  8 & 0x000000ff);
                blen[3] = (byte) (data_length       & 0x000000ff);
                out.write(blen, 0, 4);
                out.flush();
                out.writeByte(cipherInfo.opcode);
                out.flush();
                out.write( (cipherInfo.sid).getBytes(), 0, cipherInfo.sid.length());
                out.flush();
            }
            else
            {
                if( cipherInfo.envelopedData.length == 0)
                {
                    out.close(); in.close(); sock.close();
                    return -5; /* data is null */
                }
                {
                    data_length = cipherInfo.envelopedData.length + 1;
                    byte blen[] = new byte[4];
                    blen[0] = (byte) (data_length >> 24 & 0x000000ff);
                    blen[1] = (byte) (data_length >> 16 & 0x000000ff);
                    blen[2] = (byte) (data_length >>  8 & 0x000000ff);
                    blen[3] = (byte) (data_length       & 0x000000ff);
                    out.write(blen, 0, 4);
                }
                out.flush();
                out.writeByte(cipherInfo.opcode);
                out.flush();
                out.write(cipherInfo.envelopedData, 0, cipherInfo.envelopedData.length);
                out.flush();
            }

            int ret_data_length = in.readInt();
            byte ret_opcode = in.readByte();

            byte ret_status_flag = in.readByte();
            /*result.status_flag    = ret_status_flag;*/

            if(ret_status_flag == sk_verify_not_ok)
            {
                byte[] errmsg = new byte[256];
                in.readFully(errmsg, 0, ret_data_length -2 );
                String ret_errmsg = new String(errmsg);
                cipherInfo.errmsg = ret_errmsg.trim();
                out.close();
                in.close();
                sock.close();
                return 1;   /* not ok */
            }

            switch(cipherInfo.opcode)
            {
                case 77 : //get sid
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    cipherInfo.sid = new String(st.nextToken());
                    cipherInfo.cert = new String(st.nextToken());
                    break;
                }
                case 78 :  //develop
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    cipherInfo.sid       = new String(st.nextToken());
                    cipherInfo.plaintext = new String(st.nextToken());
                    break;
                }
                case 79 :  //decrypt
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    cipherInfo.sid = new String(st.nextToken());
                    String plain_tmp = new String(st.nextToken(""));
                    cipherInfo.decryptedData = plain_tmp.substring(1);
                    break;
                }
                case 80 : // encrypt
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    cipherInfo.sid = new String(st.nextToken());
                    cipherInfo.encryptedData = new String(st.nextToken());
                    break;
                }
                case 75 : case 76 :// envelop
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    cipherInfo.sid = new String(st.nextToken());
                    cipherInfo.envelopdata = new String(st.nextToken());
                    break;
                }
                case 85 : // envelop
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    cipherInfo.sid = new String(st.nextToken());
                    cipherInfo.encryptedData = new String(st.nextToken());
                    break;
                }
                case 121 : /* Only KSD E_VOTE Cert DownLoad */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    cipherInfo.cert = new String(st.nextToken());
                    break;
                }
                case 122 : /* Only KSD E_VOTE Envelop */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    cipherInfo.encryptedData = new String(st.nextToken());
                    break;
                }
                case 123 : /* Only KSD E_VOTE Develop */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    cipherInfo.plaintext = new String(st.nextToken());
                    break;
                }
                default :
                    break;
            }
            out.close();
            in.close();
            sock.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            //System.out.println(e.toString());
            return -4;
        }
        return 0;
    }
    public int verifySignCipher(String verifyd_ip, int verifyd_port, SKSignedDataInfo result, SKVerifyExtInfo ext)
    {
        int sk_verify_ok = 0;       // SK_VERIFY_OK
        int sk_verify_not_ok = 1;   // SK_VERIFY_NOT_OK
        int sk_verify_not_ok2 = 2;

        Socket              sock= null;
        DataOutputStream    out = null;
        DataInputStream     in  = null;

        if(verifyd_ip.equals(""))
            verifyd_ip = "127.0.0.1";

        if(verifyd_port == 0)
            verifyd_port = default_verifyd_port;

        try
        {
            sock= new Socket(verifyd_ip, verifyd_port);
            out = new DataOutputStream(sock.getOutputStream());
            in  = new DataInputStream(sock.getInputStream());
        }
        catch(UnknownHostException e)
        {
            log.error("Don't know about host: " + verifyd_ip );
            return -1;
        }
        catch(IOException e)
        {
            log.error("Couldn't get I/O for the connection to: " + verifyd_ip);
            return -2;
        }

        try
        {
            // send SK_VERIFY_REQ
            int data_length;

            if( result.signed_data.length == 0)
            {
                out.close(); in.close(); sock.close();
                return -5; /* data is null */
            }

            {
                data_length = result.signed_data.length + 1;
                byte blen[] = new byte[4];
                blen[0] = (byte) (data_length >> 24 & 0x000000ff);
                blen[1] = (byte) (data_length >> 16 & 0x000000ff);
                blen[2] = (byte) (data_length >>  8 & 0x000000ff);
                blen[3] = (byte) (data_length       & 0x000000ff);
                out.write(blen, 0, 4);
            }
            out.flush();
            out.writeByte(result.opcode);
            out.flush();
            out.write(result.signed_data, 0, result.signed_data.length);
            out.flush();

            int ret_data_length = in.readInt();
            byte ret_opcode = in.readByte();

            if(ret_opcode != result.opcode)
            {
                out.close(); in.close(); sock.close();
                return -3;
            }

            byte ret_status_flag = in.readByte();
            result.status_flag  = ret_status_flag;

            if(ret_status_flag == sk_verify_not_ok)
            {
                /* add error process */
                byte[] errmsg = new byte[256];
                in.readFully(errmsg, 0, ret_data_length -2 );
                String ret_errmsg = new String(errmsg);
                result.errmsg = ret_errmsg.trim();

                out.close();
                in.close();
                sock.close();
                return 1;   /* not ok */
            }

            switch(result.opcode)
            {
                case 81 : // decrypt & fullsignverify
                case 83 : // decrypt & fullsignverify From crl
                case 84 : // decrypt & fullsignverify From crl & 신원확인
                case 86 : // decrypt & fullsignverify From OCSP & 신원확인
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);

                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    result.sid = new String(st.nextToken());
                    ext.Version = new String(st.nextToken());
                    ext.Serial = new String(st.nextToken());
                    ext.Issuer = new String(st.nextToken());
                    ext.Subject = new String(st.nextToken());
                    ext.From = new String(st.nextToken());
                    ext.To = new String(st.nextToken());
                    ext.policy = new String(st.nextToken());
                    ext.distributionPoints = new String(st.nextToken());
                    ext.authorityKeyId = new String(st.nextToken());
                    ext.subjectKeyId = new String(st.nextToken());
                    ext.publicKey = new String(st.nextToken());

                    String plain_tmp = new String(st.nextToken(""));
                    String plain = plain_tmp.substring(1);

                    result.plaintext = plain.getBytes();
                    result.signer_dn = ext.Subject;
                    break;
                }
                case 82 :  //decrypt & shortsignverify
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    result.sid       = new String(st.nextToken());

                    String plain_tmp = new String(st.nextToken(""));
                    String plain = plain_tmp.substring(1);

                    result.plaintext = plain.getBytes();
                    break;
                }
            }

            out.close();
            in.close();
            sock.close();

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
     * @Method Name        : verifySignExt
     * @Method description : 검증데몬으로 검증 요청함수(ip,port,검증할데이터입력클래스,검증후인증서세부정보클래스)
     * @param              : String,int,SKSignedDataInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int verifySignExt(String verifyd_ip, int verifyd_port, SKSignedDataInfo result, SKVerifyExtInfo ext)
    {

        String serverType = OppfProperties.getProperty("Globals.server.type");
        if(serverType.equals("loc")) {
            return 0;
        }

        int sk_verify_ok = 0;       // SK_VERIFY_OK
        int sk_verify_not_ok = 1;   // SK_VERIFY_NOT_OK
        int sk_verify_not_ok2 = 2;

        Socket              sock = null;
        DataOutputStream    out  = null;
        DataInputStream     in   = null;

        if(verifyd_ip.equals(""))
            verifyd_ip = "127.0.0.1";

        if(verifyd_port == 0)
            verifyd_port = default_verifyd_port;

        try
        {
            sock= new Socket(verifyd_ip, verifyd_port);
            out = new DataOutputStream(sock.getOutputStream());
            in  = new DataInputStream(sock.getInputStream());
        }
        catch (UnknownHostException e)
        {
            log.error("Don't know about host: " + verifyd_ip );
            return -1;
        }
        catch (IOException e)
        {
            log.error("Couldn't get I/O for the connection to: " + verifyd_ip);
            return -2;
        }

        try
        {
            // send SK_VERIFY_REQ

            int data_length;
            if( result.signed_data.length == 0)
            {
                out.close(); in.close(); sock.close();
                return -5; /* data is null */
            }

            switch (result.opcode)
            {
                case 17 : case 18 : case 37 : case 60 : default :
                    data_length = result.signed_data.length + 1;
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
            out.writeByte(result.opcode);
            out.flush();

            switch (result.opcode)
            {
                case 15 : default :
                    out.write(result.signed_data, 0, result.signed_data.length);
                    break;
            }
            out.flush();

            int ret_data_length = in.readInt();
            byte ret_opcode = in.readByte();

            if(ret_opcode != result.opcode)
            {
                log.error("recv SK_VERIFY_REP error 1");
                log.error("ret_opcode" + ret_opcode);
                log.error("result.opcode" + result.opcode);
                log.error("recv SK_VERIFY_REP error 1");
                out.close();
                in.close();
                sock.close();
                return -3;
            }

            byte ret_status_flag = in.readByte();
            result.status_flag  = ret_status_flag;

            if(ret_status_flag == sk_verify_not_ok)
            {
                /* add error process */
                byte[] errmsg = new byte[256];
                in.readFully(errmsg, 0, ret_data_length -2 );
                String ret_errmsg = new String(errmsg);
                result.errmsg = ret_errmsg.trim();
                out.close();
                in.close();
                sock.close();
                return 1;   /* not ok */
            }

            if(ret_status_flag == sk_verify_not_ok2)
            {
                switch(result.opcode)
                {
                    case 24 :
                    {
                        byte[] ret_plaintext = new byte[ret_data_length - 2];
                        in.readFully(ret_plaintext, 0, ret_data_length - 2);
                        StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                        int st_count = st.countTokens();
                        ext.Version = new String(st.nextToken());
                        ext.Serial = new String(st.nextToken());
                        ext.Issuer = new String(st.nextToken());
                        ext.Subject = new String(st.nextToken());
                        ext.From = new String(st.nextToken());
                        ext.To = new String(st.nextToken());
                        ext.policy = new String(st.nextToken());
                        ext.distributionPoints = new String(st.nextToken());
                        ext.authorityKeyId = new String(st.nextToken());
                        ext.subjectKeyId = new String(st.nextToken());
                        ext.publicKey = new String(st.nextToken());
                        String plain_tmp = new String(st.nextToken(""));
                        String plain = plain_tmp.substring(1);
                        result.plaintext = plain.getBytes();
                        result.signer_dn = ext.Subject;
                        break;
                     }
                }
                out.close();
                in.close();
                sock.close();
                return 2;
            }
            switch(result.opcode)
            {
                case 17 : case 18 : case 37 : case 60 : case 38 :
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    ext.Version = new String(st.nextToken());
                    ext.Serial = new String(st.nextToken());
                    ext.Issuer = new String(st.nextToken());
                    ext.Subject = new String(st.nextToken());
                    ext.From = new String(st.nextToken());
                    ext.To = new String(st.nextToken());
                    ext.policy = new String(st.nextToken());
                    ext.distributionPoints = new String(st.nextToken());
                    ext.authorityKeyId = new String(st.nextToken());
                    ext.subjectKeyId = new String(st.nextToken());
                    ext.publicKey = new String(st.nextToken());
                    String plain_tmp = new String(st.nextToken(""));
                    String plain = plain_tmp.substring(1);
                    result.plaintext = plain.getBytes();
                    result.signer_dn = ext.Subject;
                    break;
                }
                case 23 :
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    ext.Subject = new String(st.nextToken());
                    String plain_tmp = new String(st.nextToken(""));
                    String plain = plain_tmp.substring(1);
                    result.plaintext = plain.getBytes();
                    result.signer_dn = ext.Subject;
                    break;
                }

                case 24 : case 26 :case 28 : case 44 : case 46 :case 48 :
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    ext.Version = new String(st.nextToken());
                    ext.Serial = new String(st.nextToken());
                    ext.Issuer = new String(st.nextToken());
                    ext.Subject = new String(st.nextToken());
                    ext.From = new String(st.nextToken());
                    ext.To = new String(st.nextToken());
                    ext.policy = new String(st.nextToken());
                    ext.distributionPoints = new String(st.nextToken());
                    ext.authorityKeyId = new String(st.nextToken());
                    ext.subjectKeyId = new String(st.nextToken());
                    ext.publicKey = new String(st.nextToken());
                    String plain_tmp = new String(st.nextToken(""));
                    String plain = plain_tmp.substring(1);
                    result.plaintext = plain.getBytes();
                    result.signer_dn = ext.Subject;
                    break;
                }
                case 25 :  case 27 :case 29 : case 45 : case 47  :case 49 : case 77 :
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    ext.Version = new String(st.nextToken());
                    ext.Serial = new String(st.nextToken());
                    ext.Issuer = new String(st.nextToken());
                    ext.Subject = new String(st.nextToken());
                    ext.From = new String(st.nextToken());
                    ext.To = new String(st.nextToken());
                    ext.policy = new String(st.nextToken());
                    ext.distributionPoints = new String(st.nextToken());
                    ext.authorityKeyId = new String(st.nextToken());
                    ext.subjectKeyId = new String(st.nextToken());
                    ext.publicKey = new String(st.nextToken());
                    String plain_tmp = new String(st.nextToken(""));
                    String plain = plain_tmp.substring(1);
                    result.plaintext = plain.getBytes();
                    result.signer_dn = ext.Subject;
                    break;
                }
                case 40:
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);

                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();

                    result.signer_dn = new String(st.nextToken());
                    break;
                }
                case 41:
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    result.signer_dn = new String(st.nextToken());
                    break;
                }
                case 42:
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    result.signer_dn = new String(st.nextToken());
                    break;
                }
                case 61:
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    result.signer_dn = new String(st.nextToken());
                    break;
                }
                case 62: /* Case62 is added 2007.04.02 by Hotspirit */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    result.signer_dn = new String(st.nextToken());
                    break;
                }
                case 63: /* Case63 is added 2006.02.15 by Hotspirit */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    result.signer_dn = new String(st.nextToken());
                    break;
                }
                case 64: /* Case64 is added 2006.12.08 by Hotspirit */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    result.signer_dn = new String(st.nextToken());
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
                        result.status_flag  = ret_status_flag;
                        result.signer_dn    = new String(signer_dn);
                        result.publickey    = ret_publicKey;
                    }
                    else
                    {
                        byte[] signer_dn = new byte[128];
                        in.readFully(signer_dn, 0, 128);
                        byte[] ret_publicKey = new byte[ret_data_length -2 - 128];
                        in.readFully(ret_publicKey, 0, ret_data_length -2 - 128);
                        result.status_flag  = ret_status_flag;
                        result.signer_dn    = new String(signer_dn);
                        result.publickey    = ret_publicKey;
                    }
                    break;
                }
                case 50 : /* add by hotspirit 2006.07.21 */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    result.signer_dn = new String(st.nextToken());
                    String plain = new String(st.nextToken());
                    result.plaintext = plain.getBytes();
                    break;
                }
                case 52 : /* add by hotspirit 2007.11.06 */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    ext.Version = new String(st.nextToken());
                    ext.Serial = new String(st.nextToken());
                    ext.Issuer = new String(st.nextToken());
                    ext.Subject = new String(st.nextToken());
                    ext.From = new String(st.nextToken());
                    ext.To = new String(st.nextToken());
                    ext.policy = new String(st.nextToken());
                    ext.distributionPoints = new String(st.nextToken());
                    ext.authorityKeyId = new String(st.nextToken());
                    ext.subjectKeyId = new String(st.nextToken());
                    ext.publicKey = new String(st.nextToken());
                    String plain = new String(st.nextToken());
                    result.plaintext = plain.getBytes();
                    result.signer_dn = ext.Subject;
                    break;
                }
                case 51 : /* add by hotspirit 2007.11.06 */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    ext.Version = new String(st.nextToken());
                    ext.Serial = new String(st.nextToken());
                    ext.Issuer = new String(st.nextToken());
                    ext.Subject = new String(st.nextToken());
                    ext.From = new String(st.nextToken());
                    ext.To = new String(st.nextToken());
                    ext.policy = new String(st.nextToken());
                    ext.distributionPoints = new String(st.nextToken());
                    ext.authorityKeyId = new String(st.nextToken());
                    ext.subjectKeyId = new String(st.nextToken());
                    ext.publicKey = new String(st.nextToken());
                    String plain = new String(st.nextToken());
                    result.plaintext = plain.getBytes();
                    result.signer_dn = ext.Subject;
                    break;
                }
                case 101: /* SignKorea 내부용  PKCS#7 서명  리턴은 서명데이터, 서명에 사용된 인증서 값*/
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    /*in.read(ret_plaintext, 0, ret_data_length - 2);*/
                    StringTokenizer st = new StringTokenizer(new String(ret_plaintext), "$");
                    int st_count = st.countTokens();
                    result.signer_dn = new String(st.nextToken());
                    result.Certificate = new String(st.nextToken());
                    break;
                }
                case 102 : /* SignKorea 내부용  PKCS#7 CRL 검증  */
                {
                    byte[] ret_plaintext = new byte[ret_data_length - 2];
                    in.readFully(ret_plaintext, 0, ret_data_length - 2);
                    //응답에서 원문만
                    byte[] plain = new byte[ret_plaintext.length - 4591];
                    System.arraycopy(ret_plaintext, 4591, plain, 0, plain.length);
                    result.plaintext = plain;
                    byte[] info = new byte[4591];
                    System.arraycopy(ret_plaintext, 0, info, 0, 4591);
                    StringTokenizer st = new StringTokenizer(new String(info), "$");
                    int st_count = st.countTokens();
                    ext.Version = new String(st.nextToken());
                    ext.Serial = new String(st.nextToken());
                    ext.Issuer = new String(st.nextToken());
                    ext.Subject = new String(st.nextToken());
                    ext.From = new String(st.nextToken());
                    ext.To = new String(st.nextToken());
                    ext.policy = new String(st.nextToken());
                    ext.distributionPoints = new String(st.nextToken());
                    ext.authorityKeyId = new String(st.nextToken());
                    ext.subjectKeyId = new String(st.nextToken());
                    ext.publicKey = new String(st.nextToken());
                    result.signer_dn = ext.Subject;
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

    public int certstat( String ivsp_ip, int ivsp_port, SKIvsmcCert cert)
    {
        byte opcode = 7;

        String sk_ok = "000";
        int read_time_out = 15000;  /* 15 sec */

        Socket           sock = null;
        DataOutputStream out  = null;
        DataInputStream  in   = null;

        if(cert.serial_no.equals(""))
        {
            return cert.set_err(300, "NO REQUEST DATA");
        }

        try
        {
            sock = new Socket(ivsp_ip, ivsp_port);
            // read timeout
            sock.setSoTimeout(read_time_out);

            out = new DataOutputStream(sock.getOutputStream());
            in  = new DataInputStream(sock.getInputStream());
        }
        catch (UnknownHostException e)
        {
            return cert.set_err(300, "IVSP CONNECT ERR");
        }
        catch (IOException e)
        {
            return cert.set_err(300, "IVSP CONNECT ERR");
        }

        try
        {
            // msg := opcode + data
            String send_data;
            String send_msg;
            String recv_data;
            String recv_msg;
            byte[] byte_data;
            byte[] byte_msg;
            int send_msg_len;
            int send_data_len;
            int recv_msg_len;
            int recv_data_len;

            String ret_code;

            send_data = cert.serial_no + "$" + cert.ssn + "$";
            byte_data = send_data.getBytes();
            send_data_len = byte_data.length;
            send_msg_len = send_data_len + 1;
            out.writeInt(send_msg_len);
            out.writeByte(opcode);
            out.write(byte_data, 0, send_data_len);
            out.flush();


            recv_msg_len = in.readInt();
            byte_msg = new byte[recv_msg_len];
            in.readFully(byte_msg, 0, recv_msg_len);

            out.close();
            in.close();
            sock.close();

            recv_data_len = recv_msg_len - 1;
            recv_data = new String (byte_msg, 1, recv_data_len);

            StringTokenizer st = new StringTokenizer(recv_data, "$");
            int st_count = st.countTokens();
            if(st_count < 1)
            {
                return cert.set_err(300, "DATA PARSING ERR");
            }

            ret_code = st.nextToken();
            if(ret_code.equals(sk_ok))
            {
                if(st_count != 3)
                {
                    return cert.set_err(300, "DATA PARSING ERR");
                }
                cert.status_flag = new String(st.nextToken());
                cert.ssnmatch = new String(st.nextToken());
                return 0;
            }
            else
            {
                if(st_count != 2)
                {
                    return cert.set_err(300, "DATA PARSING ERR");
                }
                Integer tmp_code = new Integer(ret_code);
                cert.err_code = tmp_code.intValue();
                cert.err_msg = new String(st.nextToken());
                return cert.err_code;
            }
        }
        catch (IOException e)
        {
            return cert.set_err(300, "RECV MSG ERR");
        }
    }

    public int dnquery(String ivsp_ip, int ivsp_port, SKIvsmcCert cert)
    {
        byte opcode = 6;
        int read_time_out = 15000;  /* 15 sec */

        String sk_ok = "000";

        Socket           sock = null;
        DataOutputStream out  = null;
        DataInputStream  in   = null;

        if(cert.dn.equals("") || cert.ssn.equals("")){
            return cert.set_err(300, "NO REQUEST DATA");
        }

        try{
            sock = new Socket(ivsp_ip, ivsp_port);
            // read timeout
            sock.setSoTimeout(read_time_out);

            out = new DataOutputStream(sock.getOutputStream());
            in  = new DataInputStream(sock.getInputStream());
        }catch(UnknownHostException e){
            return cert.set_err(300, "IVSP CONNECT ERR");
        }catch(IOException e){
            return cert.set_err(300, "IVSP CONNECT ERR");
        }

        try{
            // msg := opcode + data
            String send_data;
            String send_msg;
            String recv_data;
            String recv_msg;
            byte[] byte_data;
            byte[] byte_msg;
            int send_msg_len;
            int send_data_len;
            int recv_msg_len;
            int recv_data_len;

            String ret_code;

            send_data = cert.dn + "$" + cert.ssn + "$";
            byte_data = send_data.getBytes();
            send_data_len = byte_data.length;
            send_msg_len = send_data_len + 1;
            out.writeInt(send_msg_len);
            out.writeByte(opcode);
            out.write(byte_data, 0, send_data_len);
            out.flush();

            recv_msg_len = in.readInt();
            byte_msg = new byte[recv_msg_len];
            in.readFully(byte_msg, 0, recv_msg_len);

            out.close();
            in.close();
            sock.close();

            recv_data_len = recv_msg_len - 1;
            recv_data = new String (byte_msg, 1, recv_data_len);

            StringTokenizer st = new StringTokenizer(recv_data, "$");
            int st_count = st.countTokens();
            if(st_count < 1){
                return cert.set_err(300, "DATA PARSING ERR");
            }

            ret_code = st.nextToken();
            if(ret_code.equals(sk_ok)){
                if(st_count != 2){
                    return cert.set_err(300, "VERIFY TOKEN ERR");
                }
                cert.ssnmatch = new String(st.nextToken());
                return 0;
                
            }else{
                if(st_count != 2){
                    return cert.set_err(300, "DATA PARSING ERR");
                }
                Integer tmp_code = new Integer(ret_code);
                cert.err_code = tmp_code.intValue();
                cert.err_msg = new String(st.nextToken());
                return cert.err_code;
            }
        }catch (IOException e){
            return cert.set_err(300, "RECV MSG ERR");
        }
    }
    
}

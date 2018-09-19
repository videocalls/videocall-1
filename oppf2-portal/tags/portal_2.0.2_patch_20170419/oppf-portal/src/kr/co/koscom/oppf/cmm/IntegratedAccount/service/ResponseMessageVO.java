package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import org.springframework.http.ResponseEntity;

/**
 * @author lee
 * @date 2017-02-16
 */
@SuppressWarnings("serial")
public class ResponseMessageVO{
    /** Successful Response Message */
    public static final ResponseMessageVO SUCCESS = new ResponseMessageVO(200, "Success.");
    public static final ResponseMessageVO NO_CONTENT = new ResponseMessageVO(204, "정상적으로 처리했으나, 해당계좌가 없음.");
    public static final ResponseMessageVO BAD_REQUEST = new ResponseMessageVO(400, "Bad Request.");

    private int code = 200;
    private String message;

    public ResponseMessageVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
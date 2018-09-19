package kr.co.koscom.oppf.spt.direct.internal.exception;

public enum InternalApiExceptionCode {

    BAD_REQUEST(1001),
    NOT_FOUND_USER (1002),
    EXIST_USER(1003),
    UNKNOWN_ERROR (1099),
   ;
   
   
   private int code;
   InternalApiExceptionCode(int code) {
       this.code = code;
   }
   
   public int getCode() {
       return this.code;
    }
}

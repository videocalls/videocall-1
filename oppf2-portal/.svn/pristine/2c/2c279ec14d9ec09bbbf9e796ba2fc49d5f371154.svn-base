package kr.co.koscom.oppf.spt.direct.common.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.koscom.oppf.spt.direct.internal.exception.InternalApiException;
import kr.co.koscom.oppf.spt.direct.internal.model.InternalResponse;

@ControllerAdvice
public class RestGlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
    @ExceptionHandler(InternalApiException.class)
    public @ResponseBody InternalResponse handleCommonApiException(HttpServletRequest request, InternalApiException ex) {
        
        return new InternalResponse(ex.getExceptionCode().getCode(), ex.getMessage());
    }
    
    // TODO : APIException handling 추가
}

package kr.co.koscom.oppfm.cmm.exception;

import kr.co.koscom.oppfm.cmm.model.CommonHeader;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import javax.servlet.http.HttpServletResponse;

/**
 * ExceptionControllerAdvice
 * <p>
 * Created by chungyeol.kim on 2017-04-20.
 */
@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @Autowired
    private MessageUtil messageUtil;
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CommonResponse defaultException(HttpServletResponse response, Exception e) {
        log.error(e.toString());
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.INTERNAL_SERVER_ERROR, new String[] {""});
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public @ResponseBody CommonResponse methodNotAllowedException(HttpServletResponse response, HttpRequestMethodNotSupportedException e) {
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.METHOD_NOT_ALLOWED, null);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CommonResponse handleMissingServletRequestParameterException(HttpServletResponse response, MissingServletRequestParameterException e) {
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CommonResponse handleMethodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException e) {
        String message = "";
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            message = error.getDefaultMessage();
            break;
        }

        // 메세지 구분
        String description = null;
        String code = null;
        if(!message.equals("")) {
            String[] messageArray = message.split(";");
            code = messageArray[0];
            if(messageArray.length >= 2) {
                message = messageArray[1];
                if(messageArray.length >= 3) {
                    description = messageArray[2];
                }
            }
        }

        // 헤더 셋팅
        CommonHeader commonHeader = new CommonHeader();
        commonHeader.setCode(code);
//        commonHeader.setId("400");
        commonHeader.setMessage(message);
        commonHeader.setDescription(description);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setHeader(commonHeader);

        return commonResponse;
    }

    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CommonResponse CommonExceptionHandler(HttpServletResponse response, CommonException e) {
        return CommonResponseUtil.commonResponseData(messageUtil, e.getCode(), e.getArgs());
    }

    @ExceptionHandler(ExternalInterfaceException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CommonResponse ExternalInterfaceException(HttpServletResponse response, ExternalInterfaceException e) {
        CommonResponse commonResponse = CommonResponseUtil.commonResponseData(messageUtil, e.getCode(), e.getArgs());
        log.info("ExternalInterfaceException LOG :::::");
        log.info("Error Message ::::: {}", commonResponse.getHeader().getMessage());
        log.info("External Interface Response ::::: {}", e.getResponse());
        return commonResponse;
    }

    @ExceptionHandler(InternalInterfaceException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CommonResponse InternalInterfaceException(HttpServletResponse response, InternalInterfaceException e) {
        CommonResponse commonResponse = CommonResponseUtil.commonResponseData(messageUtil, e.getCode(), e.getArgs());
        log.info("InternalInterfaceException LOG :::::");
        log.info("Error Message ::::: {}", commonResponse.getHeader().getMessage());
        log.info("Internal Interface Response ::::: {}", e.getResponse());
        return commonResponse;
    }
}

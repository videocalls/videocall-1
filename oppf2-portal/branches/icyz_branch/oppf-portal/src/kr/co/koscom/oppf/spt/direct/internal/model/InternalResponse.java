package kr.co.koscom.oppf.spt.direct.internal.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response Message Entity
 * 
 * @author Xenomity
 *
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class InternalResponse implements Serializable {
	/** Successful Response Message */
	public static final InternalResponse SUCCESS = new InternalResponse(1000, "Success.");
	
	private final String category = "op-port";
	private int code = 1000;
	private String message;
	
	public InternalResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public int getCode() {
//		return code;
//	}
//
//	public void setCode(int code) {
//		this.code = code;
//	}
//	
//	public String getCategory() {
//		return category;
//	}
}

package kr.co.koscom.oppfm.cmm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * CommonVO
 * <p>
 * Created by chungyeol.kim on 2017-04-27.
 */
@Data
public abstract class CommonVO implements Serializable {
    @JsonIgnore
    private String createDate; //생성일
    @JsonIgnore
    private String createId;   //생성자 ID
    @JsonIgnore
    private String updateDate; //수정일
    @JsonIgnore
    private String updateId;   //수정자 ID
}

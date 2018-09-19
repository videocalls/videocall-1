package kr.co.koscom.oppfm.cmm.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * CommonResponse
 * <p>
 * Created by chungyeol.kim on 2017-04-20.
 */
@Data
public class CommonResponse extends CommonVO {
    private static final long serialVersionUID = 1638663586857148668L;

    private CommonHeader header;
    private Map<String,Object> body;

    public Map<String,Object> getBody() {
        if(body == null) body = new HashMap<>();
        return body;
    }
}

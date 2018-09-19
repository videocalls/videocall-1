package kr.co.koscom.oppf.spt.direct.company.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Company implements Serializable {
    private String comAlias;
    private String comName;
    private String comId;
    private String agreementVer;
    private boolean realAccYn;
    private String apiEndpoint;
}

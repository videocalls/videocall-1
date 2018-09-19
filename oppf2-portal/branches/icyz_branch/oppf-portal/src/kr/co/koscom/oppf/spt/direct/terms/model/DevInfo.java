package kr.co.koscom.oppf.spt.direct.terms.model;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class DevInfo implements Serializable {

    private String ipAddr;
    private String macAddr;;
}

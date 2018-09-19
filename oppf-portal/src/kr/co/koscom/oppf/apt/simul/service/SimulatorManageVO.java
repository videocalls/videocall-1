package kr.co.koscom.oppf.apt.simul.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

import java.util.List;

/**
 * Created by LSH on 2017. 3. 6..
 */
@SuppressWarnings("serial")
public class SimulatorManageVO  extends ComDefaultListVO {
    private String senderCompId;
    private String senderCompName;
    private int excutionRate;
    private int rejectRate;
    private String companyId;
    private String createName;
    private String updateName;

    /**
     * search
     * */
    private List<String> fixBuySide;            //senderCompId
    private String fixBuySideAllYn;

    private String searchDateType;              //검색날짜 타입 : 등록일, 수정일
    private String searchDateFrom;              //작날짜
    private String searchDateTo;                //끝날짜


    public String getSenderCompId() {
        return senderCompId;
    }

    public void setSenderCompId(String senderCompId) {
        this.senderCompId = senderCompId;
    }

    public String getSenderCompName() {
        return senderCompName;
    }

    public void setSenderCompName(String senderCompName) {
        this.senderCompName = senderCompName;
    }

    public int getExcutionRate() {
        return excutionRate;
    }

    public void setExcutionRate(int excutionRate) {
        this.excutionRate = excutionRate;
    }

    public int getRejectRate() {
        return rejectRate;
    }

    public void setRejectRate(int rejectRate) {
        this.rejectRate = rejectRate;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public List<String> getFixBuySide() {
        return fixBuySide;
    }

    public void setFixBuySide(List<String> fixBuySide) {
        this.fixBuySide = fixBuySide;
    }

    public String getFixBuySideAllYn() {
        return fixBuySideAllYn;
    }

    public void setFixBuySideAllYn(String fixBuySideAllYn) {
        this.fixBuySideAllYn = fixBuySideAllYn;
    }

    public String getSearchDateType() {
        return searchDateType;
    }

    public void setSearchDateType(String searchDateType) {
        this.searchDateType = searchDateType;
    }

    public String getSearchDateFrom() {
        return searchDateFrom;
    }

    public void setSearchDateFrom(String searchDateFrom) {
        this.searchDateFrom = searchDateFrom;
    }

    public String getSearchDateTo() {
        return searchDateTo;
    }

    public void setSearchDateTo(String searchDateTo) {
        this.searchDateTo = searchDateTo;
    }
}

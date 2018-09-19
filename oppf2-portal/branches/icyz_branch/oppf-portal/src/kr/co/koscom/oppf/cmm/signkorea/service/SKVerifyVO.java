package kr.co.koscom.oppf.cmm.signkorea.service;

public class SKVerifyVO{

    //결과
    public int ret = 0;
    
    //데몬 프로세스가 실행되고 있는 PORT
    public String demonIp = "127.0.0.1";
    
    //데몬 프로세스가 실행되고 있는 서버 IP
    public int demonPort = 7900; 
    
    //클라이언트에서 넘어온 전자서명데이터
    //public String signData = "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";
    public String signData;
    
    
    //클라이언트에서 넘어온 R값
    //public String rValue = "H8Py0IU2rQVfRj51qO/NdCNcbXs=";
    public String rValue;
    
    //클라이언트에서 넘어온 신원확인정보 값
    //public String userSsn = "1298520172";
    public String userSsn;
    
    //서명검증 및 신원확인 CRL op코드(signData + "$" + rValue + "$" + userSsn)
    public String datas = "";
    
    public SKSignedDataInfo sKSignedDataInfo;
    
    public SKVerifyExtInfo sKVerifyExtInfo;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getDemonIp() {
        return demonIp;
    }

    public void setDemonIp(String demonIp) {
        this.demonIp = demonIp;
    }

    public int getDemonPort() {
        return demonPort;
    }

    public void setDemonPort(int demonPort) {
        this.demonPort = demonPort;
    }

    public String getSignData() {
        return signData;
    }

    public void setSignData(String signData) {
        this.signData = signData;
    }

    public String getrValue() {
        return rValue;
    }

    public void setrValue(String rValue) {
        this.rValue = rValue;
    }

    public String getUserSsn() {
        return userSsn;
    }

    public void setUserSsn(String userSsn) {
        this.userSsn = userSsn;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public SKSignedDataInfo getsKSignedDataInfo() {
        return sKSignedDataInfo;
    }

    public void setsKSignedDataInfo(SKSignedDataInfo sKSignedDataInfo) {
        this.sKSignedDataInfo = sKSignedDataInfo;
    }

    public SKVerifyExtInfo getsKVerifyExtInfo() {
        return sKVerifyExtInfo;
    }

    public void setsKVerifyExtInfo(SKVerifyExtInfo sKVerifyExtInfo) {
        this.sKVerifyExtInfo = sKVerifyExtInfo;
    }
    
}
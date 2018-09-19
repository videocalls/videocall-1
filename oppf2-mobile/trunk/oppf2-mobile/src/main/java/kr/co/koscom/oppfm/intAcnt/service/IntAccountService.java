package kr.co.koscom.oppfm.intAcnt.service;

import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.intAcnt.model.IntAccountReq;
import kr.co.koscom.oppfm.member.model.MemberVerifyRes;

/**
 * FileName : IntAccountService
 *
 * Description : IntAccountService
 *
 * Created by LHT on 2017. 5. 16..
 */
public interface IntAccountService {

    /**
     * getOauthInfo
     * */
    CommonResponse getIntAccountList(IntAccountReq intAccountReq);

    /**
     * getIntAccountAppInfo
     * */
    CommonResponse getIntAccountAppInfo();

    /**
     * getBalanceList
     * */
    CommonResponse getBalanceList(IntAccountReq intAccountReq, MemberVerifyRes memberVerifyRes) throws Exception;

    /**
     * getTransactionList
     * */
    CommonResponse getTransactionList(IntAccountReq intAccountReq, MemberVerifyRes memberVerifyRes) throws Exception;

    /**
     * getPortfolioList
     * */
    CommonResponse getPortfolioList(IntAccountReq intAccountReq, MemberVerifyRes memberVerifyRes) throws Exception;

    /**
     * getInterestList
     * */
    CommonResponse getInterestList(IntAccountReq intAccountReq, MemberVerifyRes memberVerifyRes) throws Exception;

}

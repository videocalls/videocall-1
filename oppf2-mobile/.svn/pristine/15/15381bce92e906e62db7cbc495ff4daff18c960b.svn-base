package kr.co.koscom.oppfm.intAcnt.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.cmm.annotation.CheckAuth;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.intAcnt.model.AppCompanyProfileRes;
import kr.co.koscom.oppfm.intAcnt.model.IntAccountBalanceRes;
import kr.co.koscom.oppfm.intAcnt.model.IntAccountReq;
import kr.co.koscom.oppfm.intAcnt.model.IntAccountResultRes;
import kr.co.koscom.oppfm.intAcnt.service.IntAccountService;
import kr.co.koscom.oppfm.member.dao.MemberMapper;
import kr.co.koscom.oppfm.member.model.MemberVerifyRes;
import kr.co.koscom.oppfm.oauth.model.OauthAppRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * FileName : IntAccountController
 *
 * Description : IntAccountController
 *
 * Created by LHT on 2017. 5. 19..
 */
@Api(value="IntAccount-controller", description = "마이페이지 > 통합계좌조회")
@Slf4j
@RestController
public class IntAccountController {

    @Autowired
    IntAccountService intAccountService;
    @Autowired
    MemberMapper memberMapper;

    /**
     * getIntAccountList
     * */
    @CheckAuth
    @ApiOperation(value="통합계좌 연결 정보 조회", response = AppCompanyProfileRes.class)
    @RequestMapping(value = "/apis/integrated/accounts", method = RequestMethod.GET)
    public CommonResponse getIntAccountList(HttpServletRequest request){

        IntAccountReq intAccountReq = new IntAccountReq();
        //로그인정보
        intAccountReq.setCustomerRegNo(CookieUtil.getLoginInfo(request).getCustomerRegNo());

        return intAccountService.getIntAccountList(intAccountReq);
    }

    /**
     * getIntAccountList
     * */
    @CheckAuth
    @ApiOperation(value="통합계좌 APP 정보 조회", response = OauthAppRes.class)
    @RequestMapping(value = "/apis/integrated", method = RequestMethod.GET)
    public CommonResponse getIntAccountAppInfo(){

        return intAccountService.getIntAccountAppInfo();
    }

    /**
     * getBalanceList
     * */
    @CheckAuth
    @ApiOperation(value="통합계좌 자산현황 잔고 조회", response = IntAccountBalanceRes.class)
    @RequestMapping(value = "/apis/integrated/balance", method = RequestMethod.GET)
    public CommonResponse getBalanceList(IntAccountReq intAccountReq, HttpServletRequest request) throws Exception {

        MemberVerifyRes memberVerifyRes = memberMapper.selectMemberVerifyProfile(CookieUtil.getLoginInfo(request).getCustomerRegNo());

        if(memberVerifyRes == null){
            //회원 CI 정보 없음.
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_INT_VERYFY, null);
        }
        return intAccountService.getBalanceList(intAccountReq, memberVerifyRes);
    }

    /**
     * getTransactionList
     * */
    @CheckAuth
    @ApiOperation(value="통합계좌 자산현황 거래내역 조회", response = IntAccountResultRes.class)
    @RequestMapping(value = "/apis/integrated/transaction", method = RequestMethod.GET)
    public CommonResponse getTransactionList(IntAccountReq intAccountReq, HttpServletRequest request) throws Exception {

        MemberVerifyRes memberVerifyRes = memberMapper.selectMemberVerifyProfile(CookieUtil.getLoginInfo(request).getCustomerRegNo());

        if(memberVerifyRes == null){
            //회원 CI 정보 없음.
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_INT_VERYFY, null);
        }else if(null == intAccountReq.getSearchDateFrom() || null == intAccountReq.getSearchDateTo()){
            //거래내역 검색 기간 정보 필수
            throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, null);
        }

        return intAccountService.getTransactionList(intAccountReq, memberVerifyRes);
    }

    /**
     * getPortfolioList
     * */
    @CheckAuth
    @ApiOperation(value="통합계좌 자산현황 포토폴리오 조회", response = IntAccountResultRes.class)
    @RequestMapping(value = "/apis/integrated/portfolio", method = RequestMethod.GET)
    public CommonResponse getPortfolioList(IntAccountReq intAccountReq, HttpServletRequest request) throws Exception {

        MemberVerifyRes memberVerifyRes = memberMapper.selectMemberVerifyProfile(CookieUtil.getLoginInfo(request).getCustomerRegNo());

        if(memberVerifyRes == null){
            //회원 CI 정보 없음.
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_INT_VERYFY, null);
        }

        return intAccountService.getPortfolioList(intAccountReq, memberVerifyRes);
    }

    /**
     * getInterestList
     * */
    @CheckAuth
    @ApiOperation(value="통합계좌 자산현황 관심종목 조회", response = IntAccountResultRes.class)
    @RequestMapping(value = "/apis/integrated/interest", method = RequestMethod.GET)
    public CommonResponse getInterestList(IntAccountReq intAccountReq, HttpServletRequest request) throws Exception {

        MemberVerifyRes memberVerifyRes = memberMapper.selectMemberVerifyProfile(CookieUtil.getLoginInfo(request).getCustomerRegNo());

        if(memberVerifyRes == null){
            //회원 CI 정보 없음.
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_INT_VERYFY, null);
        }

        return intAccountService.getInterestList(intAccountReq, memberVerifyRes);
    }

}

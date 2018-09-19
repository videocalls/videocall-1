package kr.co.koscom.oppfm.intro.service.impl;

import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.intro.dao.IntroManageMapper;
import kr.co.koscom.oppfm.intro.model.IntroFintechAppReq;
import kr.co.koscom.oppfm.intro.model.IntroFintechAppRes;
import kr.co.koscom.oppfm.intro.service.IntroManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName : IntroManageServiceImpl
 *
 * Description : App 소개 Service Implements
 *
 * Created by LSH on 2017. 4. 20..
 */
@Service
public class IntroManageServiceImpl implements IntroManageService {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private IntroManageMapper introManageMapper;

    /**
     * Introduce Fintech App List
     * */
    @Override
    public CommonResponse introFintechAppList(IntroFintechAppReq introFintechAppReq) {

        Integer totalCount = introManageMapper.introFintechAppCompanyListCount(introFintechAppReq);
        List<IntroFintechAppRes> appList = introManageMapper.introFintechAppList(introFintechAppReq);

        Map<String, Object> body = new HashMap<>();
        body.put("appList", appList);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body,
                                introFintechAppReq.getPageInfo(), totalCount, appList.size());
    }

    /**
     * Introduce Fintech App Company List
     * */
    @Override
    public List<IntroFintechAppRes> introFintechAppCompanyList(IntroFintechAppRes param) {
        return introManageMapper.introFintechAppCompanyList(param);
    }
}

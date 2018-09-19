package kr.co.koscom.oppf.spr.usr.mbrReg.service;

import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author lee
 * @date 2017-02-06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:resources/spring/appServlet/servlet-context.xml"})
public class MbrRegServiceTest {
    private static final String TEST_CUSTOMERID = "customerId";

    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;

    @Test
    public void test_selectCheckId() throws Exception {
        MbrRegVO mbrRegVO = new MbrRegVO();
        mbrRegVO.setCustomerId(TEST_CUSTOMERID);
        mbrRegService.selectCheckId(mbrRegVO);
    }
}

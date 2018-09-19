package kr.co.koscom.oppfm.otp.dao

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.otp.model.OtpReq

/**
 * ClassName : CommonOtpRequestMapper
 * *
 * Description : 
 *
 * Created by LSH on 2017. 5. 24..
 */
@Mapper
interface CommonOtpRequestMapper {

    @Select("""<script>
        select count(1) as totalcount
        from spt_customer_otp_profile
        where customer_reg_no = #{customerRegNo}
    </script>""")
    int cntCheckOtpProfile(OtpReq otpReq);

    @Update("""<script>
        update spt_customer_otp_profile set
            customer_otp_id = #{customerOtpId},
            customer_otp_status = 'G019002',        --  갱신
            otp_chg_date = sysdate(),
            update_date = sysdate(),
            update_id = #{customerRegNo}
        where customer_reg_no = #{customerRegNo}
    </script>""")
    int updateOtpProfile(OtpReq otpReq);

    @Insert("""<script>
        insert into spt_customer_otp_profile(
            customer_reg_no,
            customer_otp_id,
            customer_otp_status,
            otp_create_date,
            otp_chg_date,
            create_date,
            create_id,
            update_date,
            update_id
        )values(
            #{customerRegNo},
            #{customerOtpId},
            'G019001',      -- 등록
            sysdate(),
            sysdate(),
            sysdate(),
            #{customerRegNo},
            sysdate(),
            #{customerRegNo}
        )
    </script>""")
    int insertOtpProfile(OtpReq otpReq);

    @Insert("""<script>
          insert into spt_customer_otp_profile_hist(
            customer_reg_no,
            customer_otp_seq,
            customer_otp_id,
            customer_otp_status,
            otp_create_date,
            otp_chg_date,
            create_date,
            create_id
        )
        select  customer_reg_no,
                ifnull((
                    select ifnull(max(cast(customer_otp_seq as SIGNED)), 0) + 1
                    from spt_customer_otp_profile_hist
                    where customer_reg_no = a.customer_reg_no
                ), 1),
                customer_otp_id,
                customer_otp_status,
                otp_create_date,
                otp_chg_date,
                create_date,
                create_id
        from spt_customer_otp_profile a
        where customer_reg_no = #{customerRegNo}
    </script>""")
    int insertOtpProfileHist(OtpReq otpReq);

    @Update("""<script>
        update spt_customer_otp_profile set
            customer_otp_status = 'G019003',
            otp_chg_date = sysdate(),
            update_date = sysdate(),
            update_id = #{customerRegNo}
        where customer_reg_no = #{customerRegNo}
    </script>""")
    int deleteOtpProfile(OtpReq otpReq);

}
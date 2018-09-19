package kr.co.koscom.oppfm.signKorea.dao

import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.signKorea.model.CustomerCertDnReq
import kr.co.koscom.oppfm.signKorea.model.SKCustomerInfoRes

/**
 * SignKoreaMapper
 * <p>
 * Created by Yoojin Han on 2017-05-29.
 */

@Mapper
public interface SignKoreaMapper {
    
    /**
     * DN 값으로 회원ID 조회 getCustomerId
     * @param customerRegNo
     * @return
     */
    @Select("""<script>
        select
            b.customer_id           as customerId,
            b.temporary_member_yn   as temporaryMemberYn,
            a.customer_reg_no       as customerRegNo,
            a.customer_verify       as customerDn

        from (
            select 
                customer_reg_no,
                customer_verify
            from 
                spt_customer_verify_profile 
            where 1=1
            <if test="customerDn != null and customerDn != ''">
                and customer_verify = #{customerDn}
            </if>
            <if test="customerRegNo != null and customerRegNo != ''">
                and customer_reg_no = #{customerRegNo}
            </if>
                and customer_verify_type = 'G007002'
        ) a

        JOIN     spt_customer_info_profile AS b
          ON     a.customer_reg_no = b.customer_reg_no
         AND     b.temporary_member_yn = 'N' 
         AND     b.customer_reg_status = 'G005002'

    </script>""")
    public SKCustomerInfoRes selectCustomerId(@Param("customerDn") String customerDn, @Param("customerRegNo") String customerRegNo);
    

    /**
     * 공인인증서 정보 변경
     * @param customerCertDnReq
     * @return
     */
    @Update("""<script>
        update spt_customer_verify_profile set
            customer_verify      = #{customerDn}
            ,update_date         = sysdate()
            ,update_id           = #{customerRegNo}
        where   customer_reg_no         = #{customerRegNo}
        and     customer_verify_type    = 'G007002'
    </script>""")
    public int updateCustomerDn(CustomerCertDnReq customerCertDnReq);

    /**
     * 공인인증서 정보 등록
     * @param customerCertDnReq
     * @return
     */
    @Update("""<script>
        insert into spt_customer_verify_profile(
            customer_reg_no      /* 회원OP등록번호 */
           ,customer_verify_type /* 회원검증종류  */
           ,customer_verify      /* 회원검증값 */
           ,customer_verify_date /* 회원검증일시 */
           ,create_date          /* 생성일시 */
           ,create_id            /* 생성자 ID */
           ,update_date          /* 변경일시 */
           ,update_id            /* 변경자 ID */
        )values(
            #{customerRegNo}      /* 회원OP등록번호 */
           ,'G007002' /* 회원검증종류  */
           ,#{customerDn}     /* 회원검증값 */
           ,sysdate()            /* 회원검증일시 */
           ,sysdate()            /* 생성일시 */
           ,#{customerRegNo}      /* 생성자 ID */
           ,sysdate()            /* 변경일시 */
           ,#{customerRegNo}      /* 변경자 ID */
        )
    </script>""")
    public int insertCustomerDn(CustomerCertDnReq customerCertDnReq);
    
}

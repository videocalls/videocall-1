package kr.co.koscom.oppfm.cmm.dao

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.cmm.model.EmailReqRes
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.SelectKey
import org.apache.ibatis.annotations.Update

/**
 *
 * EmailMapper
 *
 * Created by chungyeol.kim on 2017-06-15.
 */
@Mapper
interface EmailMapper {

    @Select("""<script>
        select
             a.email_send_type       as emailSendType                                       /* 순번         */
            ,a.email_ver             as emailVer                                            /* FAQ 노출   */
            ,a.email_title           as emailTitle                                          /* 제목         */
            ,a.email_content         as emailContent                                        /* 내용         */
            ,ifnull(date_format(a.create_date, '%Y-%m-%d %H:%i'), '') as createDate         /* 생성 일시    */
            ,ifnull(a.create_id,'') as createId                                             /* 생성 관리자  */
            ,ifnull(date_format(a.update_date, '%Y-%m-%d %H:%i'), '') as updateDate         /* 변경 일시    */
            ,ifnull(a.update_id,'') as updateId                                             /* 변경 관리자  */
        from com_email_temp_info a
        where 1=1
        and a.email_send_type = #{emailSendType}
        <if test="emailVer != null and emailVer !=''">
            and a.email_ver = #{emailVer}
        </if>
        <if test="emailVer == null or emailVer == ''">
            and a.email_ver = (
                select max(email_ver) email_ver
                from com_email_temp_info
                where email_send_type = #{emailSendType}
            )
        </if>
    </script>""")
    EmailReqRes selectSendEmailInfo(EmailReqRes emailReqRes);

    @Insert("""<script>
        insert into com_email_send_info(
            <if test="emailRegNo != null and emailRegNo != ''">
                email_reg_no,         /*이메일 발송 생성 번호*/
            </if>
            <if test="emailSendType != null and emailSendType != ''">
                email_send_type,      /*이메일 유형          */
            </if>
            <if test="emailVer != null and emailVer != ''">
                email_ver,            /*이메일 버전          */
            </if>
            <if test="senderKind != null and senderKind != ''">
                sender_kind,          /*발신자 종류          */
            </if>
            <if test="senderId != null and senderId != ''">
                sender_id,            /*발신자 ID       */
            </if>
            <if test="receiverKind != null and receiverKind != ''">
                receiver_kind,        /*수신자 종류          */
            </if>
            <if test="receiverId != null and receiverId != ''">
                receiver_id,          /*수신자 ID       */
            </if>
            <if test="receiverEmail != null and receiverEmail != ''">
                receiver_email,       /*수신자 email    */
            </if>
            <if test="emailTitle != null and emailTitle != ''">
                email_title,          /*이메일 제목          */
            </if>
            <if test="emailContent != null and emailContent != ''">
                email_content,        /*이메일 내용          */
            </if>
            send_date,            /*최초 발신일자        */
            <if test="sendId != null and sendId != ''">
                send_id,              /*최초 발신자 ID    */
            </if>
            <if test="sendResult != null and sendResult != ''">
                send_result,           /*발송결과      */
            </if>
            send_result_date,       /*발송결과일자      */
            create_date,          /*생성 일시            */
            create_id,            /*생성자 ID       */
            update_date,          /*변경 일시            */
            update_id             /*변경자 ID        */
        )values(
        <if test="emailRegNo != null and emailRegNo != ''">
            #{emailRegNo},
        </if>
        <if test="emailSendType != null and emailSendType != ''">
            #{emailSendType},
        </if>
        <if test="emailVer != null and emailVer != ''">
            #{emailVer},
        </if>
        <if test="senderKind != null and senderKind != ''">
            #{senderKind},
        </if>
        <if test="senderId != null and senderId != ''">
            #{senderId},
        </if>
        <if test="receiverKind != null and receiverKind != ''">
            #{receiverKind},
        </if>
        <if test="receiverId != null and receiverId != ''">
            #{receiverId},
        </if>
        <if test="receiverEmail != null and receiverEmail != ''">
            #{receiverEmail},
        </if>
        <if test="emailTitle != null and emailTitle != ''">
            #{emailTitle},
        </if>
        <if test="emailContent != null and emailContent != ''">
            #{emailContent},
        </if>
        sysdate(),
        <if test="sendId != null and sendId != ''">
            #{sendId},
        </if>
        <if test="sendResult != null and sendResult != ''">
            #{sendResult},
        </if>
        sysdate(),
        sysdate(),
        #{createId},
        sysdate(),
        #{createId}
        )
    </script>""")
    @SelectKey(statement="select concat(date_format(sysdate(),'%Y%m%d'), lpad( (select ifnull( max(cast(right(email_reg_no,4) as signed)), 0)+1 from com_email_send_info where left(email_reg_no,8) = concat(date_format(sysdate(),'%Y%m%d'))) , 4, '0')) as emailRegNo"
            , keyProperty="emailRegNo", before=true, resultType=String.class)
    int inseretSendEmailInfo(EmailReqRes emailReqRes);

    @Update("""<script>
        update com_email_send_info set
            email_title = #{emailTitle},
            email_content = #{emailContent},
            re_send_count = ifnull(re_send_count,0)+1,
            re_send_date = sysdate(),
            re_send_id = #{reSendId},
            re_send_result = #{reSendResult},
            re_send_result_date = sysdate(),
            update_date = sysdate(),
            update_id = #{updateId}
        where email_reg_no = #{emailRegNo}
    </script>""")
    int updateSendEmailInfo(EmailReqRes emailReqRes);

    @Select("""<script>
        select
            email_reg_no as emailRegNo
        from com_email_send_info
        where 1=1
        and receiver_id = #{receiverId}
        and email_send_type = 'G016002'
    </script>""")
    String selectSendEmailInfoChk(EmailReqRes emailReqRes);

    @Select("""<script>
        select  a.customer_reg_no               as customerRegNo,
                a.service_reg_no                as serviceRegNo,
                a.terms_reg_no                  as termsRegNo,
                a.terms_auth_yn                 as termsAuthYn,
                a.subcompany_code_id            as subcompanyCodeId,
                sub.company_name_kor_alias      as subcompanyName,
                a.app_id                        as appId,
                a.app_name                      as appName,
                a.pubcompany_code_id            as pubcompanyCodeId,
                pub.company_name_kor_alias      as pubcompanyName,
                a.api_id                        as apiId,
                a.api_name                      as apiName,
                b.customer_vtaccount_no         as customerVtaccountNo,
                b.customer_vtaccount_alias      as customerVtaccountAlias,
                a.terms_start_date              as termsStartDate,
                a.terms_expire_date             as termsExpireDate
        from(
            select  a.*,
                    b.api_name,
                    c.company_code_id       as pubcompany_code_id,
                    c.exposure_order        as pubexposure_order
            from(
                select  a.*,
                        b.app_name,
                        b.company_code_id   as subcompany_code_id,
                        c.exposure_order    as subexposure_order
                from(
                    select  a.customer_reg_no,
                            a.service_reg_no,
                            a.app_id,
                            b.api_id,
                            b.customer_realaccount_no,
                            a.terms_reg_no,
                            a.terms_auth_yn,
                            a.terms_start_date,
                            a.terms_expire_date
                    from(
                        select  a.*,
                                b.terms_auth_yn,
                                date_format(b.terms_start_date, '%Y-%m-%d') as terms_start_date,
                                date_format(b.terms_expire_date, '%Y-%m-%d') as terms_expire_date
                        from(
                            select  customer_reg_no,
                                    service_reg_no,
                                    app_id,
                                    terms_reg_no
                            from spt_customer_service_profile
                            where customer_reg_no = #{customerRegNo}
                            and terms_reg_no  = #{termsRegNo}
                        ) a
                        left join spt_customer_service_terms_profile b
                        on a.customer_reg_no = b.customer_reg_no
                        and a.terms_reg_no = b.terms_reg_no
                    ) a, spt_customer_service_account_profile b
                    where a.customer_reg_no = b.customer_reg_no
                    and a.service_reg_no = b.service_reg_no
                    and a.app_id = b.app_id
                    and b.delete_date is null
                ) a
                left join com_app_view b on a.app_id = b.app_id
                left join com_app_info c on a.app_id = c.app_id
                where b.app_status = 'G022002'        /*app 상태 (CA 정보)*/
                and c.exposure_yn = 'Y'
            ) a
            left join com_api_view b on a.api_id = b.api_id
            left join com_api_info c on a.api_id = c.api_id
            where c.exposure_yn = 'Y'
            and c.api_account_yn = 'Y'
        ) a
        left join(
            select *
            from spt_customer_account_profile
            where customer_reg_no = #{customerRegNo}
        ) b
        on a.pubcompany_code_id = b.company_code_id
        and a.customer_realaccount_no = b.customer_realaccount_no
        join com_company_profile as pub on a.pubcompany_code_id = pub.company_code_id and pub.delete_date is null and pub.exposure_yn = 'Y'
        join com_company_profile as sub on a.subcompany_code_id = sub.company_code_id and sub.delete_date is null and sub.exposure_yn = 'Y'
        order by
            ifnull(sub.exposure_order, 'Z') asc, sub.company_name_kor_alias asc,
            ifnull(a.subexposure_order, 'Z') asc, a.app_name asc,
            ifnull(pub.exposure_order, 'Z') asc, pub.company_name_kor_alias asc,
            ifnull(a.pubexposure_order, 'Z') asc,  a.api_name asc
    </script>""")
    List<EmailReqRes> selectEmailTermsList(EmailReqRes emailReqRes);

    @Select("""<script>
        select  a.customer_name_kor           as customerName,
                dec_char_sel(
                    a.customer_email,
                    10,
                    'ARIA',
                    'spt_customer_info_profile',
                    'customer_email',
                    user(),
                    connection_id()
                )                             as customerEmail
        from spt_customer_info_profile a
        where a.customer_reg_no = #{customerRegNo}
    </script>""")
    EmailReqRes selectEmailCustomerInfo(EmailReqRes emailReqRes);

}
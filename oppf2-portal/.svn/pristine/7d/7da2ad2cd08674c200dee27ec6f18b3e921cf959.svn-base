#DROP FUNCTION auth_authorize_fn;
DELIMITER $$
USE `oppf`$$
CREATE FUNCTION oppf.`auth_authorize_fn`(
    p_api_id                    varchar(256),
    p_app_key                   varchar(256),
    p_customer_ci               varchar(256),
    p_customer_vtaccount_no     varchar(256),
    p_customer_id               varchar(256)
) 
RETURNS varchar(256)
CHARACTER SET utf8
DETERMINISTIC
BEGIN
    /**
    * 인증
    *
    * param info -------------------------------------------------------------
    * p_api_id : api_id
    * p_app_key : app_key
    * p_customer_ci : 고객 CI
    * p_customer_id : 서비스 포털(spt_customer_info_profile.customer_id) 로그인 ID
    * p_customer_vtaccount_no : 고객 가상계좌 번호
    * return info ------------------------------------------------------------
    * success : 성공
    * Authorization failed. xx..xx : 실패 및 msg -> 데이터가 없거나 정보가 없을 경우
    *
    * logic  -----------------------------------------------------------------
    * 1. 기본 parameter 확인
    * 1-1. api_id parameter 확인
    * -> 값이 없을 경우 fail : Authorization failed. API 조회 정보를 확인 하시기 바랍니다.
    * 
    * 1-2. app_key parameter 확인
    * -> 값이 없을 경우 fail : Authorization failed. APP 조회 정보를 확인 하시기 바랍니다.
    * 
    * 2. api_id를 이용하여 계좌 사용여부(api_account_yn), 사용자 확인여부(CI)(api_user_confirm_yn)의 값을 가져온다.
    * -> 값이 없을 경우 fail : Authorization failed. API 정보를 확인 하시기 바랍니다.
    * 
    * 3. customer_reg_no 추출
    * 3-1. 사용자 확인 여부가 Y 일때 p_customer_id나 p_customer_ci가 빈값인지 확인
    * -> 값이 없을 경우 fail : Authorization failed. 사용자 CI 정보를 확인하시기 바랍니다.
    * 3-2. 사용자 확인 여부가 Y이면 CI와 계정이 같은사용자 정보인지 확인 -> customer_reg_no 추출
    * -> 값이 없을 경우 fail : Authorization failed. 사용자 정보를 확인하시기 바랍니다.
    * 
    * 3-3 사용자 확인 여부가 N일때 p_customer_id가 빈값인지 확인
    * -> 값이 없을 경우 fail : Authorization failed. 사용자 ID 정보를 확인하시기 바랍니다.
    * 3-4. 사용자 확인 여부가 N이면 계정으로 customer_reg_no 추출
    * -> 값이 없을 경우 fail : Authorization failed. 사용자 정보를 확인하시기 바랍니다.
    * 
    * 4. 계좌 사용여부(api_account_yn) : Y 경우에만 처리
    * 
    * 4-1 계좌 사용여부(api_account_yn) : Y 경우 p_customer_ci 빈값 확인
    * -> 값이 없을 경우 fail : Authorization failed. 사용자 CI 정보를 확인하시기 바랍니다.
    * 4-2 계좌 사용여부(api_account_yn) : Y 경우 p_customer_vtaccount_no 빈값 확인
    * -> 값이 없을 경우 fail : Authorization failed. 사용자 가상계좌 정보를 확인하시기 바랍니다.
    * 4-3. 가상계좌 발급 여부 확인 -> customer_reg_no + p_customer_vtaccount_no로 가상계좌 발급여부 확인
    * -> 값이 없을 경우 fail : Authorization failed. 가상계좌 정보를 확인하시기 바랍니다.
    * 
    * 4-4. 서비스 신청 여부 확인 -> customer_reg_no + p_customer_vtaccount_no + p_api_id + p_app_key를 이용하여 서비스 신청 여부 확인
    * -> 값이 없을 경우 fail : Authorization failed. 서비스 신청 정보를 확인하시기 바랍니다.
    * 
    * 5. API/APP 계약 정보 확인 -> p_api_id + p_app_key를 이용하여 계약정보 확인
    * -> 값이 없을 경우 fail : Authorization failed. API/APP 계약 정보를 확인 하시기 바랍니다.
    */
    DECLARE v_result varchar(256);                  #결과
    DECLARE v_api_account_yn varchar(256);          #계좌 사용여부
    DECLARE v_api_user_confirm_yn varchar(256);     #사용자 확인여부(CI)
    DECLARE v_customer_reg_no varchar(12);          #회원 OP 등록 번호
    
    DECLARE v_chk int;                              #결과 cnt
    
    # 1. 기본 parameter 확인 ================================================================================
    # 1-1. api_id parameter 확인
    if ifnull(p_api_id, '') = '' then
        return 'Authorization failed. API 조회 정보를 확인 하시기 바랍니다.';
    end if;
    # 1-2. app_key parameter 확인
    if ifnull(p_app_key, '') = '' then
        return 'Authorization failed. APP 조회 정보를 확인 하시기 바랍니다.';
    end if;
    
    
    # 2. api_id를 이용하여 계좌 사용여부(api_account_yn), 사용자 확인여부(CI)(api_user_confirm_yn)의 값을 가져온다.========
    select  api_account_yn,
            api_user_confirm_yn
    into    v_api_account_yn,
            v_api_user_confirm_yn
    from com_api_info
    where api_id = p_api_id COLLATE utf8_general_ci
    ;
    
    # 결과값 확인 -> api 정보가 없을 경우
    if ifnull(v_api_account_yn, '') = '' or ifnull(v_api_user_confirm_yn, '') = '' then
        return 'Authorization failed. API 정보를 확인 하시기 바랍니다.';
    end if;
    
    # 3. customer_reg_no 추출 =================================================================================
    # 사용자 확인 여부가 Y이면 CI와 계정이 같은사용자 정보인지 확인 -> customer_reg_no 추출
    if v_api_user_confirm_yn = 'Y' then
        # 3-1. 사용자 확인 여부가 Y 일때 p_customer_id나 p_customer_ci가 빈값인지 확인
        if ifnull(p_customer_id, '') = '' or ifnull(p_customer_ci, '') = '' then
            return 'Authorization failed. 사용자 CI 정보를 확인하시기 바랍니다.';
        end if;
        
        # 3-2. 사용자 확인 여부가 Y이면 CI와 계정이 같은사용자 정보인지 확인 -> customer_reg_no 추출
        select  a.customer_reg_no
        into    v_customer_reg_no
        from spt_customer_info_profile a,
             spt_customer_verify_profile b
        where a.delete_date is null
        and a.customer_reg_status = 'G005002'
        and b.delete_date is null
        and b.customer_verify_type = 'G007001'
        and a.customer_reg_no = b.customer_reg_no
        /* 조건 */
        and a.customer_id = p_customer_id COLLATE utf8_general_ci  
        and b.customer_verify = p_customer_ci COLLATE utf8_general_ci
        ;
                
    # 사용자 확인 여부가 N이면 계정으로 customer_reg_no 추출
    else
        # 3-3 사용자 확인 여부가 N일때 p_customer_id가 빈값인지 확인
        if ifnull(p_customer_id, '') = '' then
            return 'Authorization failed. 사용자 ID 정보를 확인하시기 바랍니다.';
        end if;
        
        # 3-4. 사용자 확인 여부가 N이면 계정으로 customer_reg_no 추출
        select  a.customer_reg_no
        into    v_customer_reg_no
        from spt_customer_info_profile a,
             spt_customer_verify_profile b
        where a.delete_date is null
        and a.customer_reg_status = 'G005002'
        and b.delete_date is null
        and b.customer_verify_type = 'G007001'
        and a.customer_reg_no = b.customer_reg_no
        /* 조건 */
        and a.customer_id = p_customer_id COLLATE utf8_general_ci
        ;
    end if;
    
    # 결과값 확인
    if ifnull(v_customer_reg_no, '') = '' then
        return 'Authorization failed. 사용자 정보를 확인하시기 바랍니다.';
    end if;
    
    # 4. 계좌 사용여부(api_account_yn) : Y 경우에만 처리 =======================================================================
    if v_api_account_yn = 'Y' then
        # 4-1 계좌 사용여부(api_account_yn) : Y 경우 p_customer_ci 빈값 확인
        if ifnull(p_customer_ci, '') = '' then
            return 'Authorization failed. 사용자 CI 정보를 확인하시기 바랍니다.';
        end if;
        # 4-2 계좌 사용여부(api_account_yn) : Y 경우 p_customer_vtaccount_no 빈값 확인
        if ifnull(p_customer_vtaccount_no, '') = '' then
            return 'Authorization failed. 사용자 가상계좌 정보를 확인하시기 바랍니다.';
        end if;
    
        # 4-3. 가상계좌 발급 여부 확인 -> customer_reg_no + p_customer_vtaccount_no로 가상계좌 발급여부 확인
        select count(*) as cnt
        into   v_chk
        from spt_customer_account_profile
        where customer_vtaccount_status = 'G009002'
        and customer_vtaccount_expire_date is null
        and customer_reg_no = v_customer_reg_no COLLATE utf8_general_ci
        and customer_vtaccount_no = p_customer_vtaccount_no  COLLATE utf8_general_ci
        ;
        
        # 결과값 확인
        if ifnull(v_chk, 0) <= 0 then
            return 'Authorization failed. 가상계좌 정보를 확인하시기 바랍니다.';
        end if;
        
        # 4-4. 서비스 신청 여부 확인 -> customer_reg_no + p_customer_vtaccount_no + p_api_id + p_app_key를 이용하여 서비스 신청 여부 확인
        select count(*) as cnt
        into   v_chk
        from(
            /* 고객 + 약관 정보(서비스 신청 정보) */
            select  a.customer_reg_no,
                    a.customer_id,
                    a.customer_ci,
                    b.app_id,
                    b.app_key,
                    b.api_id,
                    b.customer_realaccount_no,
                    b.customer_vtaccount_no
            from (
                /* 사용자 정보 */
                select  a.customer_reg_no,
                        a.customer_id,
                        b.customer_verify as customer_ci
                from spt_customer_info_profile a,
                     spt_customer_verify_profile b
                where a.delete_date is null
                and a.customer_reg_status = 'G005002'
                /* spt_customer_verify_profile 조건 */
                and b.delete_date is null
                and b.customer_verify_type = 'G007001'
                and a.customer_reg_no = b.customer_reg_no
            ) a
            join (
                /* 사용자 약관동의 정보 */
                select  a.*,
                        b.customer_vtaccount_no
                from(
                    select  a.customer_reg_no,
                            b.service_reg_no,
                            a.terms_reg_no,
                            b.app_id,
                            (
                                select app_key
                                from com_app_view 
                                where app_id = b.app_id
                            ) as app_key,
                            c.api_id,
                            c.customer_realaccount_no,
                            (
                                select company_code_id
                                from com_api_info
                                where api_id = c.api_id
                            ) as pubcompany_code_id
                    from spt_customer_service_terms_profile a,
                         spt_customer_service_profile b,
                         spt_customer_service_account_profile c
                    where a.delete_date is null
                    and a.terms_auth_yn = 'N'
                    and date_format(sysdate(), '%Y%m%d') between a.terms_start_date and a.terms_expire_date
                    /* spt_customer_service_profile 조건 */
                    and b.delete_date is null
                    and a.customer_reg_no = b.customer_reg_no
                    and a.terms_reg_no = b.terms_reg_no
                    /* spt_customer_service_account_profile 조건 */
                    and c.delete_date is null
                    and b.customer_reg_no = c.customer_reg_no
                    and b.service_reg_no = c.service_reg_no
                ) a 
                join spt_customer_account_profile b
                on a.customer_reg_no = b.customer_reg_no
                and a.pubcompany_code_id = b.company_code_id
                and a.customer_realaccount_no = b.customer_realaccount_no
                and b.customer_vtaccount_status = 'G009002'
                and b.customer_vtaccount_expire_date is null
            ) b on a.customer_reg_no = b.customer_reg_no
        ) a
        where a.customer_reg_no = v_customer_reg_no COLLATE utf8_general_ci
        and a.app_key = p_app_key COLLATE utf8_general_ci
        and a.api_id = p_api_id COLLATE utf8_general_ci
        and a.customer_vtaccount_no = p_customer_vtaccount_no COLLATE utf8_general_ci
        ;
        
        # 결과값 확인
        if ifnull(v_chk, 0) <= 0 then
            return 'Authorization failed. 서비스 신청 정보를 확인하시기 바랍니다.';
        end if;
    end if;
    
    # 5. API/APP 계약 정보 확인 -> p_api_id + p_app_key를 이용하여 계약정보 확인 =================================================================
    select count(*) as cnt
    into   v_chk
    from(
        /* 계약정보 */
        select  b.app_id,
                b.app_name,
                b.app_key,
                b.subcompany_code_id,
                a.api_id,
                a.api_name,
                a.pubcompany_code_id
        from (
            /* api 정보 */
            select  a.*
            from(
                select  a.api_id,
                        a.api_name,
                        a.company_code_id as pubcompany_code_id,
                        b.subcompany_code_id,
                        # api의 계약정보가 필요없음(G023006)이면 계약정보 확인 안함.
                        case when a.api_contract_code = 'G023006' then
                            'Y'
                        else
                            /* 계약완료, 재계약, 필요없음 */
                            case when b.service_contract_status in ('G023001', 'G023005', 'G023006') then
                                'Y'
                            else
                                'N'
                            end
                        end as contract_yn,
                        # api의 계약정보가 필요없음(G023006)이면 계약정보 확인 안함. -> 계약일 확인
                        case when a.api_contract_code = 'G023006' then
                            'Y'
                        else
                            /* 계약완료, 재계약, 필요없음 */
                            case when b.service_contract_status in ('G023001', 'G023005', 'G023006') then
                                case when 
                                    date_format(sysdate(), '%Y%m%d') 
                                    between if(b.service_contract_status = 'G023006', date_format(sysdate(), '%Y%m%d'), b.contract_terms_start_date)
                                    and if(b.service_contract_status = 'G023006', date_format(sysdate(), '%Y%m%d'), b.contract_terms_expire_date)
                                then
                                    'Y'
                                else
                                    'N'
                                end
                            else
                                'N'
                            end
                        end as contract_date_yn
                from com_api_info a
                left join com_api_contract_info b
                on a.api_id = b.api_id
                and a.company_code_id = b.pubcompany_code_id
                where a.api_contract_code in ('G023001', 'G023005', 'G023006') /* 계약완료, 재계약, 필요없음 */
                and date_format(sysdate(), '%Y%m%d')
                    between if(a.api_contract_code = 'G023006', date_format(sysdate(), '%Y%m%d'), a.api_terms_start_date)
                    and if(a.api_contract_code = 'G023006', date_format(sysdate(), '%Y%m%d'), a.api_terms_expire_date)
            ) a
            where a.contract_yn = 'Y'
            and a.contract_date_yn = 'Y'
        ) a
        join (
            /* app 정보 */
            select  a.app_id,
                    b.app_name,
                    a.app_key,
                    b.company_code_id as subcompany_code_id,
                    c.api_id
            from com_app_info a
            join com_app_view as b on a.app_id = b.app_id
            join com_app_apilist_view as c on a.app_id = c.app_id
            where a.app_contract_code in ('G023001', 'G023005', 'G023006') /* 계약완료, 재계약, 필요없음 */
            and date_format(sysdate(), '%Y%m%d')
                between if(a.app_contract_code = 'G023006', date_format(sysdate(), '%Y%m%d'), a.app_terms_start_date)
                and if(a.app_contract_code = 'G023006', date_format(sysdate(), '%Y%m%d'), a.app_terms_expire_date)
        ) b
        on a.api_id = b.api_id COLLATE utf8_unicode_ci
        #and a.subcompany_code_id = b.subcompany_code_id COLLATE utf8_unicode_ci        
    ) a
    where a.app_key = p_app_key COLLATE utf8_general_ci
    and a.api_id = p_api_id COLLATE utf8_general_ci
    ;
    
    # 결과값 확인
    if ifnull(v_chk, 0) <= 0 then
        return 'Authorization failed. API/APP 계약 정보를 확인 하시기 바랍니다.';
    end if;
        
    
    return 'success';   
END$$
DELIMITER ;
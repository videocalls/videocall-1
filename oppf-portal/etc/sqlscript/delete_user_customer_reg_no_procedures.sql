DROP PROCEDURE IF EXISTS delete_user_customer_reg_no_procedures;
CREATE PROCEDURE delete_user_customer_reg_no_procedures(IN customerRegno varchar(12))
BEGIN
    /*  
    * 입력 받은 회원 아이디 별로 회원 정보를 삭제한다.
    */

    DECLARE memberCount INT;
    
    select count(*) 
    from oppf.spt_customer_info_profile
    where customer_reg_no = customerRegno COLLATE utf8_unicode_ci 
    into memberCount;
    
    /* 
    현대 개발서버에 코드가 아닌 delete_date 만 update 되어 있는 회원이 존재함 
    데이터 클린징을 위하여 상태값 조건 주석처리
    and customer_reg_status = 'G005004' 
    */
    
    if memberCount >= 1 then
        SET FOREIGN_KEY_CHECKS=0;
        select concat(customerRegno COLLATE utf8_unicode_ci," 회원 데이터 삭제 시작(delete_user_custemr_reg_no_procedures) ") AS 'DEBUG' ;
        /* 회원데이터 삭제 */
                  delete from spt_customer_company_terms_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;
                  delete from spt_customer_company_terms_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;


                  delete from spt_customer_common_terms_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  delete from spt_customer_common_terms_pubcompany_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  delete from spt_customer_common_terms_pubcompany_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;
                            
                  delete from spt_customer_company_terms_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;


                  delete from spt_customer_common_terms_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;


                  /* 일반회원검증 hist */
                  delete from spt_customer_verify_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원검증 */
                  delete from spt_customer_verify_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반 회원 약관동의 hist */
                  delete from spt_customer_terms_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 약관동의 */
                  delete from spt_customer_terms_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 ARS 인증 정보 이력  */
                  delete from spt_customer_service_ars_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 ARS 인증 정보 */
                  delete from spt_customer_service_ars_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 서비스 약관 금투사 프로파일 hist */
                  delete from spt_customer_service_terms_pubcompany_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 서비스 약관 금투사 프로파일 */
                  delete from spt_customer_service_terms_pubcompany_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 서비스 약관 프로파일 hist */
                  delete from spt_customer_service_terms_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 서비스 약관 프로파일 */
                  delete from spt_customer_service_terms_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /*  */
                  delete from spt_customer_service_terms_file_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /*  */
                  delete from spt_customer_service_terms_file_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 서비스 계좌 프로파일 hist */
                  delete from spt_customer_service_account_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 서비스 계좌 프로파일 */
                  delete from spt_customer_service_account_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 서비스 프로파일 hist */
                  delete from spt_customer_service_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 서비스 프로파일 */
                  delete from spt_customer_service_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 OTP 프로파일 hist */
                  delete from spt_customer_otp_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 OTP 프로파일 */
                  delete from spt_customer_otp_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 회원 기업 약관 동의 프로파일 hist */
                  delete from spt_customer_company_terms_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 회원 기업 약관 동의 프로파일 */
                  delete from spt_customer_company_terms_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 가상계좌 프로파일 hist */
                  delete from spt_customer_account_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원 가상계좌 프로파일 */
                  delete from spt_customer_account_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 회원 Provider SCOPE 동의 프로파일 */
                  delete from spt_customer_provider_scope_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 회원 account oauth 토큰 */
                  delete from spt_customer_account_oauth_token_info
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 회원 Provider 약관 동의 프로파일 */
                  delete from spt_customer_provider_terms_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 데이터셋 주식 정보 */
                  delete from dataset_equity_info
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 가상증권사 계좌 프로파일 */
                  delete from dataset_vtcompany_account_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 데이터셋 기타 정보 */
                  delete from dataset_etc_info
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 데이터셋 거래내역 정보 */
                  delete from dataset_transaction_info
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 데이터셋 관심종목 정보 */
                  delete from dataset_interest_info
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 데이터셋 펀드 정 */
                  delete from dataset_fund_info
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 데이터셋 계좌 프로파일 */
                  delete from dataset_account_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원프로파일 hist */
                  delete from spt_customer_info_profile_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원프로파일 hist */
                  delete from spt_mobile_info_hist
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원프로파일 hist */
                  delete from spt_mobile_info
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 탈퇴회원 프로파일 */
                  delete from leave_customer_verify_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 탈퇴회원 프로파일 */
                  delete from leave_customer_info_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;

                  /* 일반회원프로파일 */
                  delete from spt_customer_info_profile
                  where customer_reg_no = customerRegno COLLATE utf8_unicode_ci ;






		  SET FOREIGN_KEY_CHECKS=1;
          
         select concat(customerRegno COLLATE utf8_unicode_ci," 회원 데이터 삭제 종료 (delete_user_custemr_reg_no_procedures) ") AS 'DEBUG' ;
    else
        select concat(customerRegno COLLATE utf8_unicode_ci," 삭제할 사용자가 없습니다.") AS 'DEBUG' ;
    end if;
    
END;
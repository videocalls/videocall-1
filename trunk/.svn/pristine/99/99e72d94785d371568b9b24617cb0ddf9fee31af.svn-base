package kr.co.koscom.oppfm.cmmAcc.dao

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.cmmAcc.model.MypSvcApplVO

@Mapper
public interface SvcApplMapper {
	
	
	/**
	  * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.selectMypSvcApplList
	  * @Method description : [신청서비스] 신청서비스 목록을 조회한다.
	  * @param              : MypSvcApplVO
	  * @return             : MypSvcApplVO
	  * @throws             : Exception
	*/
	@Select("""<script>
		select  a.customer_reg_no               as customerRegNo,
				a.service_reg_no                as serviceRegNo,
				a.app_id                        as appId,
				a.api_id                        as apiId,
				a.customer_realaccount_no       as customerRealaccountNo,
				a.terms_reg_no                  as termsRegNo,
				a.terms_auth_yn                 as termsAuthYn,
				a.terms_start_date              as termsStartDate,
				a.terms_expire_date             as termsExpireDate,
				a.terms_auth_date_yn            as termsAuthDateYn,
				a.app_name                      as appName,
				a.subcompany_code_id            as subcompanyCodeId,
				sub.company_name_kor_alias      as subcompanyName,
				a.api_name                      as apiName,
				a.pubcompany_code_id            as pubcompanyCodeId,
				pub.company_name_kor_alias      as pubcompanyName,
				b.customer_vtaccount_no         as customerVtaccountNo,
				b.customer_vtaccount_alias      as customerVtaccountAlias
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
							a.terms_expire_date,
							a.terms_auth_date_yn
					from(
						select  a.*,
								b.terms_auth_yn,
								date_format(b.terms_start_date, '%Y-%m-%d') as terms_start_date,
								date_format(b.terms_expire_date, '%Y-%m-%d') as terms_expire_date,
								case when
									  /*date_format(sysdate(), '%Y%m%d') between b.terms_start_date and b.terms_expire_date*/
									   date_format(sysdate(), '%Y%m%d') <![CDATA[ >= ]]> date_format(b.terms_expire_date - INTERVAL 1 MONTH, '%Y%m%d') AND
									   date_format(sysdate(), '%Y%m%d') <![CDATA[ <= ]]> date_format(b.terms_expire_date, '%Y%m%d')
									  then 'N'
									  else 'Y'
								end as terms_auth_date_yn
						from(
							select  customer_reg_no,
									service_reg_no,
									app_id,
									terms_reg_no
							from spt_customer_service_profile
							where customer_reg_no = #{customerRegNo}
							and terms_reg_no is not null
							and delete_date is null
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
				/*and c.exposure_yn = 'Y'*/
			) a
			left join com_api_view b on a.api_id = b.api_id
			left join com_api_info c on a.api_id = c.api_id
			where 1=1 /*c.exposure_yn = 'Y'*/
			and c.api_account_yn = 'Y'
		) a
		left join(
			select *
			from spt_customer_account_profile
			where customer_reg_no = #{customerRegNo}
			and customer_vtaccount_status = 'G009002'
		) b
		on a.pubcompany_code_id = b.company_code_id
		and a.customer_realaccount_no = b.customer_realaccount_no
		join com_company_profile as pub on a.pubcompany_code_id = pub.company_code_id and pub.delete_date is null /*and pub.exposure_yn = 'Y'*/
		join com_company_profile as sub on a.subcompany_code_id = sub.company_code_id and sub.delete_date is null /*and sub.exposure_yn = 'Y'*/
		order by
			ifnull(sub.exposure_order, 'Z') asc, sub.company_name_kor_alias asc,
			ifnull(a.subexposure_order, 'Z') asc, a.app_name asc,
			ifnull(pub.exposure_order, 'Z') asc, pub.company_name_kor_alias asc,
			ifnull(a.pubexposure_order, 'Z') asc,  a.api_name asc	</script>""")
	List<MypSvcApplVO> selectMypSvcApplList(MypSvcApplVO mypSvcApplVO)
	
	
	/**
	  * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.selectFintechSvcDiscardList
	  * @Method description : [신청서비스] 폐기 정보제공동의 목록을 조회한다.
	  * @param              : MypSvcApplVO
	  * @return             : MypSvcApplVO
	  * @throws             : Exception
	*/
	@Select("""<script>
		select  a.customer_reg_no               as customerRegNo,
				a.service_reg_no                as serviceRegNo,
				a.app_id                        as appId,
				a.api_id                        as apiId,
				a.customer_realaccount_no       as customerRealaccountNo,
				a.terms_reg_no                  as termsRegNo,
				a.terms_discard_date            as termsDiscardDate,
				a.terms_auth_yn                 as termsAuthYn,
				a.terms_start_date              as termsStartDate,
				a.terms_expire_date             as termsExpireDate,
				a.terms_auth_date_yn            as termsAuthDateYn,
				a.app_name                      as appName,
				a.subcompany_code_id            as subcompanyCodeId,
				sub.company_name_kor_alias      as subcompanyName,
				a.api_name                      as apiName,
				a.pubcompany_code_id            as pubcompanyCodeId,
				pub.company_name_kor_alias      as pubcompanyName,
				b.customer_vtaccount_no         as customerVtaccountNo,
				case when b.customer_vtaccount_status = 'G009003' then
					'폐기된 가상계좌'
				else
					b.customer_vtaccount_alias
				end                             as customerVtaccountAlias
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
							a.terms_discard_date,
							a.terms_auth_yn,
							a.terms_start_date,
							a.terms_expire_date,
							a.terms_auth_date_yn
					from(
						select  a.*,
								b.terms_auth_yn,
								date_format(b.terms_start_date, '%Y-%m-%d') as terms_start_date,
								date_format(b.terms_expire_date, '%Y-%m-%d') as terms_expire_date,
								case when date_format(sysdate(), '%Y%m%d') between b.terms_start_date and b.terms_expire_date then
									'Y'
								else
									'N'
								end as terms_auth_date_yn
						from(
							select  customer_reg_no,
									service_reg_no,
									app_id,
									terms_reg_no,
									date_format(delete_date, '%Y-%m-%d %H:%i') as terms_discard_date
							from spt_customer_service_profile
							where customer_reg_no = #{customerRegNo}
							and terms_reg_no is not null
							and delete_date is not null
						) a
						left join spt_customer_service_terms_profile b
						on a.customer_reg_no = b.customer_reg_no
						and a.terms_reg_no = b.terms_reg_no
						where b.terms_auth_yn = 'N'
					) a, spt_customer_service_account_profile b
					where a.customer_reg_no = b.customer_reg_no
					and a.service_reg_no = b.service_reg_no
					and a.app_id = b.app_id
				) a
				left join com_app_view b on a.app_id = b.app_id
				left join com_app_info c on a.app_id = c.app_id
			) a
			left join com_api_view b on a.api_id = b.api_id
			left join com_api_info c on a.api_id = c.api_id
		) a
		left join(
			select *
			from spt_customer_account_profile
			where customer_reg_no = #{customerRegNo}
		) b
		on a.pubcompany_code_id = b.company_code_id
		and a.customer_realaccount_no = b.customer_realaccount_no
		join com_company_profile as pub on a.pubcompany_code_id = pub.company_code_id and pub.delete_date is null /*and pub.exposure_yn = 'Y'*/
		join com_company_profile as sub on a.subcompany_code_id = sub.company_code_id and sub.delete_date is null /*and sub.exposure_yn = 'Y'*/
		order by
			a.terms_discard_date desc,
			ifnull(sub.exposure_order, 'Z') asc, sub.company_name_kor_alias asc,
			ifnull(a.subexposure_order, 'Z') asc, a.service_reg_no desc, a.app_name asc,
			ifnull(pub.exposure_order, 'Z') asc, pub.company_name_kor_alias asc,
			ifnull(a.pubexposure_order, 'Z') asc,  a.api_name asc

	</script>""")
	List<MypSvcApplVO> selectFintechSvcDiscardList(MypSvcApplVO mypSvcApplVO)
	
	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.cancelMypSvcAppl
	 * @Method description : [신청서비스] 서비스 해지
	 * @param              : MypSvcApplVO
	 * @return             : int
	 * @throws             : Exception
	*/
	@Update("""<script>
		update spt_customer_service_profile set
			delete_date = sysdate(),
			update_date = sysdate(),
			update_id = #{createId}
		where customer_reg_no = #{customerRegNo}
		and service_reg_no = #{serviceRegNo}
	</script>""")
	int cancelMypSvcAppl(MypSvcApplVO mypSvcApplVO)
	
	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.insertMypSvcApplHist
	 * @Method description : [신청서비스] spt_customer_service_profile_hist hist 저장
	 * @param              : MypSvcApplVO
	 * @return             : int
	 * @throws             : Exception
	 */
	@Insert("""<script>
		insert into spt_customer_service_profile_hist(
			customer_reg_no,
			service_reg_no,
			service_seq,
			app_id,
			terms_reg_no,
			delete_date,
			create_date,
			create_id
		)
		select  customer_reg_no,
				service_reg_no,
				ifnull((
					select ifnull(max(cast(service_seq as SIGNED)), 0) + 1
					from spt_customer_service_profile_hist
					where customer_reg_no = a.customer_reg_no
					and service_reg_no = a.service_reg_no
				), 1),
				app_id,
				terms_reg_no,
				delete_date,
				update_date,
				update_id
		from spt_customer_service_profile a
		where customer_reg_no = #{customerRegNo}
		and service_reg_no = #{serviceRegNo}
	</script>""")
	int insertMypSvcApplHist(MypSvcApplVO mypSvcApplVO)
	
	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.selectCancelMypSvcApplTermsList
	 * @Method description : [신청서비스]서비스 해지 시 폐기될 동의서 목록 조회
	 * @param              : MypSvcApplVO
	 * @return             : MypSvcApplVO
	 * @throws             : Exception
	*/
	
	@Select("""<script>
		select  a.customer_reg_no   as customerRegNo,
				a.service_reg_no    as serviceRegNo,
				a.terms_reg_no      as termsRegNo
		from(
			select  a.customer_reg_no,
					a.service_reg_no,
					a.terms_reg_no,
					(
						select count(*)
						from spt_customer_service_profile
						where customer_reg_no = a.customer_reg_no
						and terms_reg_no = a.terms_reg_no
						and delete_date is null
					) as terms_cnt
			from spt_customer_service_profile a
			where a.customer_reg_no = #{customerRegNo}
			and a.service_reg_no = #{serviceRegNo}
			and a.terms_reg_no is not null
		) a
		where terms_cnt = 1
	</script>""")
	List<MypSvcApplVO> selectCancelMypSvcApplTermsList(MypSvcApplVO mypSvcApplVO)
	
	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.updateCancelMypSvcAppl
	 * @Method description : [신청서비스] 동의서 폐기 시 해당 서비스 해지
	 * @param              : MypSvcApplVO
	 * @return             : int
	 * @throws             : Exception
	*/
	@Update("""<script>
		update spt_customer_service_profile set
			delete_date = sysdate(),
			update_date = sysdate(),
			update_id = #{createId}
		where customer_reg_no = #{customerRegNo}
		and terms_reg_no = #{termsRegNo}
	</script>""")
	int updateCancelMypSvcAppl(MypSvcApplVO mypSvcApplVO)
	
	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.updateCancelMypSvcApplHist
	 * @Method description : [신청서비스] 동의서 폐기 시 해당 서비스 해지 hist 저장
	 * @param              : MypSvcApplVO
	 * @return             : int
	 * @throws             : Exception
	 */
	@Insert("""<script>
		insert into spt_customer_service_profile_hist(
			customer_reg_no,
			service_reg_no,
			service_seq,
			app_id,
			terms_reg_no,
			delete_date,
			create_date,
			create_id
		)
		select  customer_reg_no,
				service_reg_no,
				ifnull((
					select ifnull(max(cast(service_seq as SIGNED)), 0) + 1
					from spt_customer_service_profile_hist
					where customer_reg_no = a.customer_reg_no
					and service_reg_no = a.service_reg_no
				), 1),
				app_id,
				terms_reg_no,
				delete_date,
				update_date,
				update_id
		from spt_customer_service_profile a
		where customer_reg_no = #{customerRegNo}
		and terms_reg_no = #{termsRegNo}
	</script>""")
	int updateCancelMypSvcApplHist(MypSvcApplVO mypSvcApplVO)
	
	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.cancelMypSvcApplTerms
	 * @Method description : [신청서비스] 동의서 폐기
	 * @param              : MypSvcApplVO
	 * @return             : int
	 * @throws             : Exception
	*/
	@Update("""<script>
		update spt_customer_service_terms_profile set
			delete_date = sysdate(),
			update_date = sysdate(),
			update_id = #{createId}
		where customer_reg_no = #{customerRegNo}
		and terms_reg_no = #{termsRegNo}
	</script>""")
	int cancelMypSvcApplTerms(MypSvcApplVO mypSvcApplVO)
	
	
	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.insertMypSvcApplTermsHist
	 * @Method description : [신청서비스] spt_customer_service_terms_profile hist 저장
	 * @param              : MypSvcApplVO
	 * @return             : int
	 * @throws             : Exception
	 */
	@Insert("""<script>
		insert into spt_customer_service_terms_profile_hist(
			customer_reg_no,
			terms_reg_no,
			terms_seq,
			subcompany_code_id,
			subcompany_name,
			customer_name,
			customer_email,
			customer_birth_day,
			terms_policy,
			terms_chg_date,
			terms_start_date,
			terms_expire_date,
			terms_auth_yn,
			terms_file_reg_no,
			terms_file_status,
			customer_sign_dn,   /* 약관 동의 공인인증서 */
			customer_sign_data, /* 약관 동의 전자서명키 */
			customer_tsa_data,  /* 약관 동의 시점확인키 */
			delete_date,
			create_date,
			create_id
		)
		select  customer_reg_no,
				terms_reg_no,
				ifnull((
					select ifnull(max(cast(terms_seq as SIGNED)), 0) + 1
					from spt_customer_service_terms_profile_hist
					where customer_reg_no = a.customer_reg_no
					and terms_reg_no = a.terms_reg_no
				), 1),
				subcompany_code_id,
				subcompany_name,
				customer_name,
				customer_email,
				customer_birth_day,
				terms_policy,
				terms_chg_date,
				terms_start_date,
				terms_expire_date,
				terms_auth_yn,
				terms_file_reg_no,
				terms_file_status,
				customer_sign_dn,   /* 약관 동의 공인인증서 */
				customer_sign_data, /* 약관 동의 전자서명키 */
				customer_tsa_data,  /* 약관 동의 시점확인키 */
				delete_date,
				update_date,
				update_id
		from spt_customer_service_terms_profile a
		where customer_reg_no = #{customerRegNo}
		and terms_reg_no = #{termsRegNo}
	</script>""")
	int insertMypSvcApplTermsHist(MypSvcApplVO mypSvcApplVO)
	
	
	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.selectCustomerAccountProcList
	 * @Method description : [가상계좌 삭제 공통]삭제한 가상계좌로 신청한 서비스 목록을 가져온다.
	 * @param              : MypSvcApplVO
	 * @return             : MypSvcApplVO
	 * @throws             : Exception
	*/
	@Select("""<script>
		select  a.*
		from(
			select  a.service_reg_no    as serviceRegNo,
					a.terms_reg_no      as termsRegNo,
					(
						select count(*)
						from spt_customer_service_profile
						where delete_date is null
						and customer_reg_no = a.customer_reg_no
						and terms_reg_no = a.terms_reg_no
					)                   as termsCnt,
					a.account_cnt       as accountCnt
			from(
				select  a.*,
						(
							select terms_reg_no
							from spt_customer_service_profile
							where delete_date is null
							and customer_reg_no = a.customer_reg_no
							and service_reg_no = a.service_reg_no
						) as terms_reg_no,
						(
							select count(*)
							from spt_customer_service_account_profile
							where delete_date is null
							and customer_reg_no = a.customer_reg_no
							and service_reg_no = a.service_reg_no
						)                   as account_cnt
				from(
					select  a.customer_reg_no,
							a.service_reg_no
					from spt_customer_service_profile a
					join (
						select *
						from spt_customer_service_account_profile
						where (customer_reg_no, service_reg_no) in (
							/* 삭제할 계좌 정보가 있는 서비스를 찾는다 */
							select  a.customer_reg_no,
									a.service_reg_no
							from spt_customer_service_account_profile a
							join com_api_info b on a.api_id = b.api_id
							where a.delete_date is null
							and a.customer_reg_no = #{customerRegNo}
							and a.customer_realaccount_no = enc_char_ins(
								#{customerRealaccountNo},
								10,
								'ARIA',
								'spt_customer_service_account_profile',
								'customer_realaccount_no',
								user(),
								connection_id()
							)
							and b.company_code_id = #{companyCodeId}
						)
						and delete_date is null
					) b
					on a.customer_reg_no = b.customer_reg_no
					and a.service_reg_no = b.service_reg_no
					where a.customer_reg_no = #{customerRegNo}
					and a.delete_date is null
					group by a.customer_reg_no, a.service_reg_no
				) a
			) a
		) a
		order by a.accountCnt asc, a.termsCnt asc

	</script>""")
	List<MypSvcApplVO> selectCustomerAccountProcList(MypSvcApplVO mypSvcApplVO)
	
	
	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.selectCustomerAccountDeleteList
	 * @Method description : [가상계좌 삭제 공통]삭제할 서비스 가상계좌 목록을 조회한다.
	 * @param              : MypSvcApplVO
	 * @return             : MypSvcApplVO
	 * @throws             : Exception
	*/
	@Select("""<script>
		select  a.customer_reg_no   as customerRegNo,
				a.service_reg_no    as serviceRegNo,
				a.account_reg_no    as accountRegNo
		from spt_customer_service_account_profile a
		join com_api_info b on a.api_id = b.api_id
		where a.delete_date is null
		and a.customer_reg_no = #{customerRegNo}
		and a.customer_realaccount_no = enc_char_ins(
			#{customerRealaccountNo},
			10,
			'ARIA',
			'spt_customer_service_account_profile',
			'customer_realaccount_no',
			user(),
			connection_id()
		)
		and b.company_code_id = #{companyCodeId}
		group by a.customer_reg_no, a.service_reg_no, a.account_reg_no

	</script>""")
	List<MypSvcApplVO> selectCustomerAccountDeleteList(MypSvcApplVO mypSvcApplVO)
	
	
	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.deleteCustomerAccount
	 * @Method description : [가상계좌 삭제 공통] 서비스 가상계좌를 삭제한다.
	 * @param              : SptCustomerServiceAccountProfileVO
	 * @return             : int
	 * @throws             : Exception
	*/
	@Update("""<script>
		update spt_customer_service_account_profile set
			delete_date = sysdate(),
			update_date = sysdate(),
			update_id = #{createId}
		where customer_reg_no = #{customerRegNo}
		and service_reg_no = #{serviceRegNo}
		and account_reg_no = #{accountRegNo}
	</script>""")
	int deleteCustomerAccount(MypSvcApplVO mypSvcApplVO)

	/**
	 * @Method Name        : spt.myp.svcAppl.MypSvcApplDAO.insertCustomerAccountHist
	 * @Method description : [가상계좌 삭제 공통] 서비스 가상계좌 삭제 hist를 insert 한다.
	 * @param              : SptCustomerServiceAccountProfileVO
	 * @return             : int
	 * @throws             : Exception
	 */
	@Insert("""<script>
		insert into spt_customer_service_account_profile_hist(
			customer_reg_no,
			service_reg_no,
			account_reg_no,
			account_seq,
			app_id,
			api_id,
			customer_realaccount_no,
			delete_date,
			create_date,
			create_id
		)
		select  customer_reg_no,
				service_reg_no,
				account_reg_no,
				ifnull((
					select ifnull(max(cast(account_seq as SIGNED)), 0) + 1
					from spt_customer_service_account_profile_hist
					where customer_reg_no = a.customer_reg_no
					and service_reg_no = a.service_reg_no
					and account_reg_no = a.account_reg_no
				), 1),
				app_id,
				api_id,
				customer_realaccount_no,
				delete_date,
				update_date,
				update_id
		from spt_customer_service_account_profile a
		where customer_reg_no = #{customerRegNo}
		and service_reg_no = #{serviceRegNo}
		and account_reg_no = #{accountRegNo}
	</script>""")
	int insertCustomerAccountHist(MypSvcApplVO mypSvcApplVO)
	
	
}

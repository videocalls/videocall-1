package kr.co.koscom.oppfm.push.dao

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.SelectKey
import org.apache.ibatis.annotations.Update

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.push.model.PushMessageReq
import kr.co.koscom.oppfm.push.model.PushMessageRes

/**
 * PushMessageMapper
 * <p>
 * Created by Yoojin Han on 2017-05-19.
 */

@Mapper
public interface PushMessageMapper {

    /**
     * 푸시 메시지 조회
     * selectPushMessage 
     * @return 
     */
    @Select("""<script>
    SELECT
            push_message_reg_no         as pushMessageRegNo        /* push 메세지 등록번호 */
            ,push_message_title          as pushMessageTitle        /* push 메세지 제목 */
            ,(
             	select code_name_eng
            	 from   com_system_code
            	 where  concat(system_grp_code, system_code)=push_message_type
            )								as pushMessageType         /* push 메세지 구분 */
            ,contents                    as pushContents           /* 메세지 내용 */
            ,contents_url                as pushContentsUrl
    FROM push_message_info      /* push 메시지 정보 */
    WHERE 1=1
        AND push_message_reg_no = #{pushMessageRegNo}

    </script>""")
    PushMessageRes selectPushMessage(PushMessageReq pushMessageReq);
	
	/**
	 * 보내야될 푸시 메시지 정보 조회
	 * selectSendPushMessage
	 * @return
	 */
	@Select("""<script>
	SELECT
				push_message_reg_no				AS pushMessageRegNo,
				push_message_title					AS pushMessageTitle,
				device_type							AS deviceType,
				send_date								AS sendDate
	FROM	push_message_info
	WHERE	(date_format(sysdate(), 'Y%m%d%H%i') >= date_format(send_date, 'Y%m%d%H%i'))
	AND   	send_yn='N'
	</script>""")
	List<PushMessageRes> selectSendPushMessage();
	
	/**
	 * 푸시메시지를 받을 사용자의 정보(device_type, device_unique_id)
	 * selectSendPushMessage
	 * @return
	 */
	@Select("""<script>
    SELECT
                a.customer_reg_no           AS customerRegNo,
                a.device_type               AS deviceType,
                a.device_unique_id          AS deviceUniqueId
    FROM    spt_mobile_info  a
           ,spt_customer_info_profile aa
           
    WHERE   device_type IS NOT NULL
    AND     aa.customer_mobile_push_yn  = 'Y'
    AND     a.customer_reg_no           = aa.customer_reg_no
	</script>""")
	List<PushMessageRes> selectMemberPush();
	
	@Insert("""<script>
	INSERT INTO push_message_reciver_info
						(
	   						push_message_reg_no
	  						,push_message_reciver_no
	  						,customer_reg_no
	  						,send_yn
	  						,receive_yn
	  						,create_date
						) VALUES
									(
	  								 #{pushMessageRegNo}
	 								 ,(select ifnull(max(a.push_message_reciver_no),0)+1 from push_message_reciver_info a)
	  								,#{customerRegNo}
	 								,#{sendYn} 
	  								,'N'
	  								,sysdate() 
									)	
	</script>""")
	int insertPushMessageReciverInfo(PushMessageReq);
	
	
	@Update("""<script>
		UPDATE 	push_message_info
			SET
						send_yn = #{sendYn}
						,update_date = sysdate()
						,update_id = 'onestar'
		WHERE 		push_message_reg_no = #{pushMessageRegNo}
	</script>""")
	int updatePushMessageInfo(PushMessageReq);
	
	
}

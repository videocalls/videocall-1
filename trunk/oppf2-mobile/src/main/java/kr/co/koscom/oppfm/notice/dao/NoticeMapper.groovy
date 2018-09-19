package kr.co.koscom.oppfm.notice.dao

import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.notice.model.NoticeFileRes
import kr.co.koscom.oppfm.notice.model.NoticeReq
import kr.co.koscom.oppfm.notice.model.NoticeRes

/**
 * NoticeMapper
 * <p>
 * Created by Yoojin Han on 2017-05-10.
 */


@Mapper
public interface NoticeMapper {

	/**
	 * com_notice_info     총 갯수 
	 * @param              NoticeReq noticeReq
	 * @return             int
	 */
	@Select("""<script>
		SELECT
            count(a.notice_seq)                                as totalCount
        FROM com_notice_info a                                 /* 공지사항 a */
		WHERE 1=1
		AND a.delete_date is null
		AND a.use_yn = 'Y'
		AND ifnull(date_format(now(), '%Y%m%d'), '') between a.notice_start_date and a.notice_expire_date

        <if test='noticePopYn != null and noticePopYn != ""'>
            AND a.notice_pop_yn = #{noticePopYn}
        </if>
        <if test='searchKeyword != null  and searchKeyword != ""'>
            <if test='searchType=="G040002"'>
                and a.notice_title like concat('%',#{searchKeyword},'%')
            </if>
            <if test='searchType=="G040003"'>
                and a.notice_content like concat('%',#{searchKeyword},'%')
            </if>
            <if test='searchType=="G040001"'>
                AND (
                a.notice_title like concat('%',#{searchKeyword},'%') or
                a.notice_content like concat('%',#{searchKeyword},'%')
                )
            </if>
        </if>

        AND a.notice_kind in ('G003001', 'G003002')
        AND ifnull(a.display_server_type,'') &lt;&gt; 'G045001'
    </script>""")
	int selectNotiListTotalCount(NoticeReq noticeReq)


	/**
	 * com_notice_info     리스트 조회
	 * @param              NoticeReq noticeReq
	 * @return             List<NoticeRes>
	 */
	@Select("""<script>
		SELECT
            a.notice_seq                                       as noticeSeq,            
            a.notice_fix_yn                                    as noticeFixYn,           
            a.notice_kind                                      as noticeKind,           
            a.notice_title                                     as noticeTitle,     
            a.notice_content                                   as noticeContent,      
            a.file_id                                          as fileId,                
            ifnull(date_format(a.create_date,'%Y-%m-%d'),'')   as createDate,
            ifnull(date_format(a.update_date,'%Y-%m-%d'),'')   as updateDate,
            a.create_id                                        as createId, 
            a.update_id                                        as updateId, 
            ifnull(date_format(delete_date,'%Y-%m-%d'),'')     as deleteDate,
            ifnull(a.read_count,0)                             as readCount,             
            case when a.delete_date is not null then 'y' else 'n' end as useYn     

		FROM com_notice_info a
		WHERE 1=1
		AND a.delete_date is null
		AND a.use_yn = 'Y'
		AND ifnull(date_format(now(), '%Y%m%d'), '') between a.notice_start_date and a.notice_expire_date

        <if test='noticeFixYn != null and noticeFixYn != ""'>
            AND a.notice_fix_yn = #{noticeFixYn}
        </if>
        <if test='noticePopYn != null and noticePopYn != ""'>
            AND a.notice_pop_yn = #{noticePopYn}
        </if>

		<if test='searchKeyword != null  and searchKeyword != ""'>
			<if test='searchType=="G040002"'>
                and a.notice_title like concat('%',#{searchKeyword},'%')
            </if>
            <if test='searchType=="G040003"'>
                and a.notice_content like concat('%',#{searchKeyword},'%')
            </if>
            <if test='searchType=="G040001"'>
				AND (
				a.notice_title like concat('%',#{searchKeyword},'%') or
				a.notice_content like concat('%',#{searchKeyword},'%')
				)
			</if>
		</if>
        AND ifnull(a.display_server_type,'') &lt;&gt; 'G045001'

		ORDER BY a.notice_kind asc,  a.update_date desc
        <if test="pageInfo != null">
            LIMIT #{pageInfo.startRowNum},#{pageInfo.endRowNum}
        </if>

	</script>""")
	List<NoticeRes> selectNotiList(NoticeReq noticeReq)

	/**
	 * com_notice_info     공지사항 상세 조회
	 * @param              NoticeReq noticeReq
	 * @return             NoticeRes 
	 */
	@Select("""<script>
        SELECT
            a.notice_seq                                       as noticeSeq,               /* 공지사항.순번 */
            a.notice_kind                                      as noticeKind,              /* 공지사항.노출 */
            a.notice_title                                     as noticeTitle,             /* 공지사항.제목 */
            a.notice_content                                   as noticeContent,           /* 공지사항.내용 */
            ifnull(a.read_count,0)                             as readCount,               /* 공지사항.조회수 */
            a.file_id                                          as fileId,                  /* 공지사항.파일ID */
            ifnull(date_format(a.create_date,'%Y-%m-%d'),'')   as createDate,              /* 공지사항.생성일 */
            ifnull(date_format(a.update_date,'%Y-%m-%d'),'')   as updateDate,              /* 공지사항.수정일 */
            a.create_id                                        as createId,                /* 공지사항.생성자ID */
            a.update_id                                        as updateId,                /* 공지사항.수정자ID */
            ifnull(date_format(delete_date,'%Y-%m-%d'),'')     as deleteDate,              /* 공지사항.삭제일 */
            case when a.delete_date is not null then 'y' else 'n' end as useYn             /* 공지사항.삭제여부 */

        FROM com_notice_info a                          /* 공지사항 a */
        WHERE 1=1
        AND a.notice_seq = #{noticeSeq}
	</script>""")
	NoticeRes selectNotiDetail(NoticeReq noticeReq);

	/**
	 * com_notice_info     조회수 업데이트
	 * @param              NoticeReq noticeReq
	 * @return             int
	 */
	@Update("""<script>
        UPDATE
            com_notice_info 
        SET
            read_count = ifnull(read_count,0)+1

        WHERE 1=1
        AND notice_seq = #{noticeSeq}
	</script>""")
	int updateNotiReadCount(NoticeReq noticeReq);
	

	/**
	 * com_file_data   첨부파일 목록 조회
	 * @param          String fileId
	 * @return         List<NoticeFileRes>
	 */
	@Select("""<script>
        SELECT  
            file_id                     as fileId,
            file_seq                    as fileSeq,
            file_name                   as fileName,
            file_extention              as fileExtention,
            round(file_size / 1000)     as fileSize

        FROM        com_file_data
        WHERE       file_id = #{fileId}
        ORDER BY    file_seq asc   
	</script>""")
	 List<NoticeFileRes> selectNotiDetailFileList(String fileId)
     
    
    /**
     * com_notice_info  고정 공지사항 목록 조회  
     * @param           NoticeReq noticeReq
     * @return          List<NoticeRes>
     */
    @Select("""<script>
        SELECT            
            a.notice_seq                                       as noticeSeq,            
            a.notice_fix_yn                                    as noticeFixYn,           
            a.notice_kind                                      as noticeKind,           
            a.notice_title                                     as noticeTitle,     
            a.notice_content                                   as noticeContent,      
            a.file_id                                          as fileId,                
            ifnull(date_format(a.create_date,'%Y-%m-%d'),'')   as createDate,
            ifnull(date_format(a.update_date,'%Y-%m-%d'),'')   as updateDate,
            a.create_id                                        as createId, 
            a.update_id                                        as updateId, 
            ifnull(date_format(delete_date,'%Y-%m-%d'),'')     as deleteDate,
            ifnull(a.read_count,0)                             as readCount,             
            case when a.delete_date is not null then 'y' else 'n' end as useYn   

        FROM com_notice_info a   
        WHERE 1=1
        AND a.delete_date is null
        AND a.use_yn = 'Y'
        AND ifnull(date_format(now(), '%Y%m%d'), '') between a.notice_start_date and a.notice_expire_date
        AND a.notice_fix_yn = 'Y'
        AND ifnull(a.display_server_type,'') &lt;&gt; 'G045001'
    
        ORDER BY a.create_date desc
    </script>""")
    List<NoticeRes> selectNotiListFix(NoticeReq noticeReq)
    
    

}

package kr.co.koscom.oppfm.faq.dao

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import org.apache.ibatis.annotations.Select

import kr.co.koscom.oppfm.faq.model.FaqReq
import kr.co.koscom.oppfm.faq.model.FaqRes

/**
 * FaqMapper
 * <p>
 * Created by hanbyul.lee on 2017-04-28.
 */

@Mapper
public interface FaqMapper {

    /**
     * com_faq_info 총 갯수
     * @param faq
     * @return
     */
    @Select("""<script>
        select count(a.faq_seq) as totalCount
        from com_faq_info a /* faq a */
        where 1=1
        and a.delete_date is null
        and ifnull(date_format(now(), '%Y%m%d'), '') between a.faq_start_date and a.faq_expire_date
        and a.use_yn ='Y'
        <if test="searchKeyword!=null and searchKeyword!=''">
            <if test='searchType=="G040002"'>
                and a.faq_title like concat('%',#{searchKeyword},'%')
            </if>
            <if test='searchType=="G040003"'>
                and a.faq_content like concat('%',#{searchKeyword},'%')
            </if>
            <if test='searchType=="G040001"'>
                and (
                a.faq_title like concat('%',#{searchKeyword},'%') or
                a.faq_content like concat('%',#{searchKeyword},'%')
                )
            </if>
        </if>
        and a.faq_kind in ('G003001','G003002')
        AND ifnull(a.display_server_type,'') &lt;&gt; 'G045001'
    </script>""")
    int selectFaqListTotalCount(FaqReq faqReq);

    
    @Select("""<script>
        select
             a.faq_seq              as faqSeq                                               /* 순번         */  
            ,a.faq_title            as faqTitle                                             /* 제목         */
            ,a.faq_kind             as faqKind                                              /* FAQ 노출     */
            ,a.faq_content          as faqContent                                           /* 내용         */     
            ,a.file_id              as fileId                                               /* 첨부파일 ID  */  
            ,ifnull(date_format(a.delete_date, '%Y-%m-%d'), '') as deleteDate         /* 삭제일시     */  
            ,ifnull(date_format(a.create_date, '%Y-%m-%d'), '') as createDate         /* 생성 일시    */   
            ,ifnull(a.create_id,'') as createId                                             /* 생성 관리자  */  
            ,ifnull(date_format(a.update_date, '%Y-%m-%d'), '') as updateDate         /* 변경 일시    */   
            ,ifnull(a.update_id,'') as updateId

        from com_faq_info a /* faq a */
        where 1=1
        and a.delete_date is null
        and ifnull(date_format(now(), '%Y%m%d'), '') between a.faq_start_date and a.faq_expire_date
        and a.use_yn ='Y'
        
        <if test="searchKeyword !=null and searchKeyword !=''">
            <if test='searchType=="G040002"'>
                and a.faq_title like concat('%',#{searchKeyword},'%')
            </if>
            <if test='searchType=="G040003"'>
                and a.faq_content like concat('%',#{searchKeyword},'%')
            </if>
            <if test='searchType=="G040001"'>
                and (
                a.faq_title like concat('%',#{searchKeyword},'%') or
                a.faq_content like concat('%',#{searchKeyword},'%')
                )
            </if>
        </if>
        <if test="faqSeq !=null">
            and a.faq_seq = #{faqSeq}
        </if>
        and a.faq_kind in ('G003001','G003002')
        AND ifnull(a.display_server_type,'') &lt;&gt; 'G045001'
        order by a.faq_order, a.create_date desc, a.faq_title
        <if test="pageInfo!=null">
            limit #{pageInfo.startRowNum},#{pageInfo.endRowNum}
        </if>
        
    </script>""")
    public List<FaqRes> selectFaqList(FaqReq faqReq);

}

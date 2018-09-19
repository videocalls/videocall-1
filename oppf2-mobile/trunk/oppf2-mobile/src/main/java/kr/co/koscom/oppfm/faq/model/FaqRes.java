package kr.co.koscom.oppfm.faq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * FaqRes
 * <p>
 * Created by hanbyul.lee on 2017-04-24.
 */
@Data
public class FaqRes extends CommonVO{

	private static final long serialVersionUID = -3154520733013740993L;

	private String faqSeq;      //순번
	
    private String faqTitle;    //제목
   
    private String faqContent;  //내용
   
    private String createDate;  //생성 일시
    
    @JsonIgnore
    private String faqKind;     //FAQ 노출
    @JsonIgnore
    private String fileId;      //첨부파일 ID
    @JsonIgnore
    private String deleteDate;  //삭제일시
    @JsonIgnore
    private String createId;    //생성 관리자 ID
    @JsonIgnore
    private String updateDate;  //변경 일시
    @JsonIgnore    
    private String updateId;    //변경 관리자 ID

}

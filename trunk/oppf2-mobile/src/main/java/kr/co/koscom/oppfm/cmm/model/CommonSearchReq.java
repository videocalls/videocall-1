package kr.co.koscom.oppfm.cmm.model;

import lombok.Data;

/**
 * CommonSearchReq
 * <p>
 * Created by chungyeol.kim on 2017-05-12.
 */
@Data
public class CommonSearchReq extends CommonVO {
    private static final long serialVersionUID = -1490239118823017679L;

    // 현재 페이지
    private Integer page;
    // 한 페이지의 출력 건 수
    private Integer size;
    // 현재 페이지 건 수
    private Integer resultCount;
    // 전체 건 수
    private Integer totalCount;

    private PageInfo pageInfo;

    public PageInfo getPageInfo() {
    	if(page != null && size != null && pageInfo == null) {
    		pageInfo = new PageInfo(page, size);
    	}
		return pageInfo;
    }
}

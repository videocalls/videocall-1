package kr.co.koscom.oppfm.cmm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * PageInfo
 * <p>
 * Created by chungyeol.kim on 2017-04-21.
 */
@Data
public class PageInfo extends CommonVO {

    private static final long serialVersionUID = 4492782947093022653L;

    // 현재 페이지
    private Integer page;
    // 한 페이지의 출력 건 수
    private Integer size;
    // 현재 페이지 건 수
    private Integer resultCount;
    // 전체 건 수
    private Integer totalCount;

    @JsonIgnore
    private Integer startRowNum;
    @JsonIgnore
    private Integer endRowNum;

    public PageInfo()
    {
        super();
    }

    public PageInfo(int page, int size, int resultCount, int totalCount) {
        this.page = page;
        this.size = size;
        this.resultCount = resultCount;
        this.totalCount = totalCount;
    }

    public PageInfo(int page, int size) {
        this.page = page;
        this.size = size;
    }

    /*for paging*/
    public Integer getStartRowNum()
    {
        return ((page-1)*size);
    }

    /*for paging*/
    public Integer getEndRowNum()
    {
        return (page)*size;
    }
}

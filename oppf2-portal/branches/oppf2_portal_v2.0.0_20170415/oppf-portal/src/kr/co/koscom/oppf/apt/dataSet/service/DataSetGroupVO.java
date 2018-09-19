package kr.co.koscom.oppf.apt.dataSet.service;

import lombok.Data;

/**
 * Created by LSH on 2017. 2. 23..
 */
@Data
@SuppressWarnings("serial")
public class DataSetGroupVO extends DataSetCommonVO {

    /**
     * Group -> Interest
     * */
    private String datasetInterestNumber;//데이터셋 관심종목(group) 등록 번호
    private String isinCode;//종목코드
    private String groupName;//그룹이름
    private String modifyDate;//최종 수정일
    
}

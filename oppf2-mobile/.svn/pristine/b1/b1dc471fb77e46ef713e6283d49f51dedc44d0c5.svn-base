package kr.co.koscom.oppfm.cmm.dao

import kr.co.koscom.oppfm.cmm.annotation.Mapper
import kr.co.koscom.oppfm.cmm.model.StockItemVO
import org.apache.ibatis.annotations.Select

/**
 * 공통으로 필요한 mapper 관련
 * @author lee
 * @date 2017-05-24
 */
@Mapper
public interface CommonMapper {

    @Select("""<script>
	 select isu_cd as isuCd,
            isu_srt_cd as isuSrtCd,
            market_cd as marketCd,
            isu_kor_nm as isuKorNm,
            isu_kor_abbrv as isuKorAbbrv,
            map_to as  map_to
       from com_stocks_info
	</script>""")
    List<StockItemVO> selectStockItemList()
}
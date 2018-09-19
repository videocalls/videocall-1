package kr.co.koscom.oppf.cmm.service.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import kr.co.koscom.oppf.apt.dataSet.service.DataSetManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ComAbstractDAO.java 클래스
 * 
 * @author 이환덕
 * @since 2016. 04. 20.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일                      수정자        수정내용
 *  -------------  ------  ----------------------
 *  2016. 04. 20.  이환덕        최초 생성
 * </pre>
 */
public abstract class ComSecondAbstractDAO extends SqlMapClientDaoSupport{
	
    @Resource(name = "fixSqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

    public Object insert(String queryId) {
        return getSqlMapClientTemplate().insert(queryId);
    }

    public Object insert(String queryId, Object parameterObject) {
        return getSqlMapClientTemplate().insert(queryId, parameterObject);
    }

    public int update(String queryId) {
        return getSqlMapClientTemplate().update(queryId);
    }

    public int update(String queryId, Object parameterObject) {
        return getSqlMapClientTemplate().update(queryId, parameterObject);
    }

    public void update(String queryId, Object parameterObject,
            int requiredRowsAffected) {
        getSqlMapClientTemplate().update(queryId, parameterObject,
                requiredRowsAffected);
    }

    public int delete(String queryId) {
        return getSqlMapClientTemplate().delete(queryId);
    }

    public int delete(String queryId, Object parameterObject) {
        return getSqlMapClientTemplate().delete(queryId, parameterObject);
    }

    public void delete(String queryId, Object parameterObject,
            int requiredRowsAffected) {
        getSqlMapClientTemplate().delete(queryId, parameterObject,
                requiredRowsAffected);
    }

    @Deprecated
    public Object selectByPk(String queryId, Object parameterObject) {
        return getSqlMapClientTemplate().queryForObject(queryId,
                parameterObject);
    }

    public Object select(String queryId) {
        return getSqlMapClientTemplate().queryForObject(queryId);
    }

    public Object select(String queryId, Object parameterObject) {
        return getSqlMapClientTemplate().queryForObject(queryId,
                parameterObject);
    }

    public Object select(String queryId, Object parameterObject,
            Object resultObject) {
        return getSqlMapClientTemplate().queryForObject(queryId,
                parameterObject, resultObject);
    }

    public List<?> list(String queryId) {
        return getSqlMapClientTemplate().queryForList(queryId);
    }

    public List<?> list(String queryId, Object parameterObject) {
        return getSqlMapClientTemplate().queryForList(queryId, parameterObject);
    }

    public List<?> list(String queryId, int skipResults, int maxResults) {
        return getSqlMapClientTemplate().queryForList(queryId, skipResults,
                maxResults);
    }

    public List<?> list(String queryId, Object parameterObject,
            int skipResults, int maxResults) {
        return getSqlMapClientTemplate().queryForList(queryId, parameterObject,
                skipResults, maxResults);
    }

    public List<?> listWithPaging(String queryId, Object parameterObject,
            int pageIndex, int pageSize) {
        int skipResults = pageIndex * pageSize;

        int maxResults = pageSize;

        return getSqlMapClientTemplate().queryForList(queryId, parameterObject,
                skipResults, maxResults);
    }

    public Map<?, ?> map(String queryId, Object parameterObject,
            String keyProperty) {
        return getSqlMapClientTemplate().queryForMap(queryId, parameterObject,
                keyProperty);
    }

    public Map<?, ?> map(String queryId, Object parameterObject,
            String keyProperty, String valueProperty) {
        return getSqlMapClientTemplate().queryForMap(queryId, parameterObject,
                keyProperty, valueProperty);
    }
	

}

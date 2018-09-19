package kr.co.koscom.oppf.spt.direct.internal.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.direct.internal.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberDAO extends ComAbstractDAO {

    public MemberDTO selectMember(String userCi) throws SQLException {
        return (MemberDTO) select("direct.MemberDAO.selectMemberByCi", userCi);
    }

    public void insertMember(MemberDTO param) throws SQLException {
        insert("direct.MemberDAO.insertMember", param);
    }

    public int selectFindUserId(String customerId) throws SQLException {
        return (int) select("direct.MemberDAO.selectFindUserId", customerId);
    }

    public void updateMember(MemberDTO param) throws SQLException {
        update("direct.MemberDAO.updateMember", param);
    }
    
    public void insertMemberVerify(MemberDTO param) throws SQLException {
        insert("direct.MemberDAO.insertMemberVerify", param);
    }
    
    public void updateMemberVerify(MemberDTO param) throws SQLException {
        update("direct.MemberDAO.updateMemberVerify", param);
    }

}

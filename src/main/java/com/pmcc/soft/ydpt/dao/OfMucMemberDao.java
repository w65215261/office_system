package com.pmcc.soft.ydpt.dao;

import com.pmcc.soft.ydpt.domain.OfMucMember;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunyongxing
 * on 2015/6/8 0008 9:09
 */
@Repository
public class OfMucMemberDao extends SqlSessionDaoSupport{

    public static final String NAME_SPACE = "com/pmcc/soft/ydpt/ofMucMemberMapper";

    /**
     * 查询所有
     * @param omm
     * @return
     */
    public List<OfMucMember> query(OfMucMember omm) {
        return getSqlSession().selectList(NAME_SPACE + ".query",omm);
    }

    /**
     * 新增
     * @param omm
     */
    public int insert(OfMucMember omm) {
        return getSqlSession().insert(NAME_SPACE + ".insert", omm);
    }

    /**
     * 删除
     * @param omm
     * @return
     */
    public int delete(OfMucMember omm) {
        //int id = omm.getRoomID();
        return getSqlSession().delete(NAME_SPACE + ".delete", omm);
    }

    /**
     * 修改
     * @param omm
     */
    public int update(OfMucMember omm) {
        return getSqlSession().update(NAME_SPACE + ".update", omm);
    }

    /**
     * 通过id查找
     * @param roomID
     * @return
     */
    public List<OfMucMember> findByRoomId(int roomID){
        return getSqlSession().selectList(NAME_SPACE + ".findByRoomId", roomID);
    }
}

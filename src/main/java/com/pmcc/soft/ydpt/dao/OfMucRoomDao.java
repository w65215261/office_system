package com.pmcc.soft.ydpt.dao;

import com.pmcc.soft.ydpt.domain.OfMucRoom;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Name;
import java.util.List;

/**
 * Created by sunyongxing
 * on 2015/6/4 0004 12:09
 */
@Repository
public class OfMucRoomDao extends SqlSessionDaoSupport{

    public static final String NAME_SPACE = "com/pmcc/soft/ydpt/ofMucRoomMapper";

    /**
     * 查询所有
     * @param omr
     * @return
     */
    public List<OfMucRoom> query(OfMucRoom omr) {
        return getSqlSession().selectList(NAME_SPACE + ".query",omr);
    }

    /**
     * 新增
     * @param omr
     */
    public int insert(OfMucRoom omr) {
        return getSqlSession().insert(NAME_SPACE + ".insert", omr);
    }

    /**
     * 删除
     * @param omr
     * @return
     */
    public int delete(OfMucRoom omr) {
        int  roomID = omr.getRoomID();
       return getSqlSession().delete(NAME_SPACE + ".delete", roomID);
    }

    /**
     * 删除
     * @param omr
     */
    public int update(OfMucRoom omr) {
        return getSqlSession().update(NAME_SPACE + ".update", omr);
    }

    /**
     * 通过id查找
     * @param id
     * @return
     */
    public OfMucRoom findById(String id){
        return getSqlSession().selectOne(NAME_SPACE + "findById",id);
    }

    /**
     * 查询roomId
     * @param ofMucRoom
     * @return
     */
    public List<OfMucRoom> queryRoomId(OfMucRoom ofMucRoom){
        return getSqlSession().selectList(NAME_SPACE + ".queryRoomId",ofMucRoom);
    }

    /**
     * 根据roomID查询
     * @param roomID
     * @return
     */
    public OfMucRoom findByRoomID(int roomID){
        return getSqlSession().selectOne(NAME_SPACE + ".findByRoomID",roomID);
    }
}

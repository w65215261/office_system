package com.hndl.mobileplatform.dao;

import com.hndl.mobileplatform.model.Dlroompostmessage;
import com.hndl.mobileplatform.model.DlroompostmessageExample;
import com.hndl.mobileplatform.model.DlroompostmessageWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlroompostmessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int countByExample(DlroompostmessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int deleteByExample(DlroompostmessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int insert(DlroompostmessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int insertSelective(DlroompostmessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    List<DlroompostmessageWithBLOBs> selectByExampleWithBLOBs(DlroompostmessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    List<Dlroompostmessage> selectByExample(DlroompostmessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    DlroompostmessageWithBLOBs selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int updateByExampleSelective(@Param("record") DlroompostmessageWithBLOBs record, @Param("example") DlroompostmessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int updateByExampleWithBLOBs(@Param("record") DlroompostmessageWithBLOBs record, @Param("example") DlroompostmessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int updateByExample(@Param("record") Dlroompostmessage record, @Param("example") DlroompostmessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int updateByPrimaryKeySelective(DlroompostmessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(DlroompostmessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomPostMessage
     *
     * @mbggenerated Tue Jul 21 16:44:12 CST 2015
     */
    int updateByPrimaryKey(Dlroompostmessage record);
}
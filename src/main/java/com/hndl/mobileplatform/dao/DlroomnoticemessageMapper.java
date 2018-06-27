package com.hndl.mobileplatform.dao;

import com.hndl.mobileplatform.model.Dlroomnoticemessage;
import com.hndl.mobileplatform.model.DlroomnoticemessageExample;
import com.hndl.mobileplatform.model.DlroomnoticemessageWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlroomnoticemessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int countByExample(DlroomnoticemessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int deleteByExample(DlroomnoticemessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int insert(DlroomnoticemessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int insertSelective(DlroomnoticemessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    List<DlroomnoticemessageWithBLOBs> selectByExampleWithBLOBs(DlroomnoticemessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    List<Dlroomnoticemessage> selectByExample(DlroomnoticemessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    DlroomnoticemessageWithBLOBs selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int updateByExampleSelective(@Param("record") DlroomnoticemessageWithBLOBs record, @Param("example") DlroomnoticemessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int updateByExampleWithBLOBs(@Param("record") DlroomnoticemessageWithBLOBs record, @Param("example") DlroomnoticemessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int updateByExample(@Param("record") Dlroomnoticemessage record, @Param("example") DlroomnoticemessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int updateByPrimaryKeySelective(DlroomnoticemessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(DlroomnoticemessageWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlRoomNoticeMessage
     *
     * @mbggenerated Thu Jul 09 18:03:16 CST 2015
     */
    int updateByPrimaryKey(Dlroomnoticemessage record);
}
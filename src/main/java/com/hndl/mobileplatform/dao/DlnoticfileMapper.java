package com.hndl.mobileplatform.dao;

import com.hndl.mobileplatform.model.Dlnoticfile;
import com.hndl.mobileplatform.model.DlnoticfileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlnoticfileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    int countByExample(DlnoticfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    int deleteByExample(DlnoticfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    int insert(Dlnoticfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    int insertSelective(Dlnoticfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    List<Dlnoticfile> selectByExample(DlnoticfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    Dlnoticfile selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    int updateByExampleSelective(@Param("record") Dlnoticfile record, @Param("example") DlnoticfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    int updateByExample(@Param("record") Dlnoticfile record, @Param("example") DlnoticfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    int updateByPrimaryKeySelective(Dlnoticfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlNoticFile
     *
     * @mbggenerated Thu Jul 16 17:56:52 CST 2015
     */
    int updateByPrimaryKey(Dlnoticfile record);
}
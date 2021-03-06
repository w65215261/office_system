package com.hndl.mobileplatform.dao;

import com.hndl.mobileplatform.model.Dlfilesmanager;
import com.hndl.mobileplatform.model.DlfilesmanagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlfilesmanagerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    int countByExample(DlfilesmanagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    int deleteByExample(DlfilesmanagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    int deleteByPrimaryKey(String fileid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    int insert(Dlfilesmanager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    int insertSelective(Dlfilesmanager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    List<Dlfilesmanager> selectByExample(DlfilesmanagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    Dlfilesmanager selectByPrimaryKey(String fileid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    int updateByExampleSelective(@Param("record") Dlfilesmanager record, @Param("example") DlfilesmanagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    int updateByExample(@Param("record") Dlfilesmanager record, @Param("example") DlfilesmanagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    int updateByPrimaryKeySelective(Dlfilesmanager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dlFilesManager
     *
     * @mbggenerated Fri Jul 03 18:29:32 CST 2015
     */
    int updateByPrimaryKey(Dlfilesmanager record);
}
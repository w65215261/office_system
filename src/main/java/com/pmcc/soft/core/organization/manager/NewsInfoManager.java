package com.pmcc.soft.core.organization.manager;


import com.pmcc.soft.core.organization.dao.NewsInfoDao;
import com.pmcc.soft.core.organization.domain.NewsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sunyake on 15/3/19.
 */
@Service
@Transactional
public class NewsInfoManager {




    @Autowired
    private NewsInfoDao newsInfoDao;


    //查询
    public List<NewsInfo> query(NewsInfo ni) {
        List<NewsInfo> list = newsInfoDao.query(ni);

        return list;
    }

    //更新
    public void update(NewsInfo ni){
        newsInfoDao.update(ni);
    }

    /**
     * 删除
     * @param
     */
    public int delete(NewsInfo ni){
        int res= newsInfoDao.delete(ni);
        return res;
    }

    /**
     * 通过id查找
     * @param id
     * @return
     */
    public NewsInfo findById(String id) {

        return newsInfoDao.findById(id);
    }

    /**
     * 保存数据，
     */
    public void insert(NewsInfo ni){
        newsInfoDao.insert(ni);
    }

    public List<NewsInfo> queryNewsInfo(NewsInfo newsInfo){
        return newsInfoDao.queryNewsInfo(newsInfo);
    }



}

package com.pmcc.soft.core.organization.manager;

import java.util.*;

import com.pmcc.soft.core.organization.dao.AuthorityMenuInfoDao;
import com.pmcc.soft.core.organization.dao.AuthorityUserInfoDao;
import com.pmcc.soft.core.organization.domain.AuthorityMenuInfo;
import com.pmcc.soft.core.organization.domain.AuthorityUserInfo;
import com.pmcc.soft.core.organization.domain.MenuNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.MenuItemInfoDao;
import com.pmcc.soft.core.organization.domain.MenuItemInfo;
import com.pmcc.soft.core.utils.UUIDGenerator;


@Transactional
@Service
public class MenuItemInfoManager {

    @Autowired
    MenuItemInfoDao menuItemInfoDao;

    @Autowired
    private AuthorityUserInfoDao authorityUserInfoDao;

    @Autowired
    private AuthorityMenuInfoDao authorityMenuInfoDao;

    public List<MenuItemInfo> query(String parendId) {
        return menuItemInfoDao.query(parendId);
    }

    public List<MenuItemInfo> queryList(MenuItemInfo menuItemInfo) {
        //查询出所有的二级菜单
        List<MenuItemInfo> list = menuItemInfoDao.query(menuItemInfo.getId());
        //判断二级菜单是否为空
        if (list != null && list.size() > 0) {
            //循环遍历二级菜单，查看是否有三级菜单
            for (int i = 0; i < list.size(); i++) {
                List<MenuItemInfo> li = menuItemInfoDao.query(list.get(i).getId());
                //判断如果li为空则无下级菜单，如果不为空，则有下级菜单
                if (li != null && li.size() > 0) {
                    list.get(i).setState("closed");
                } else {
                    list.get(i).setState("open");
                }
                if (list.get(i).getIsShow() == 0) {
                    list.get(i).setIsShowMsg("是");
                } else {
                    list.get(i).setIsShowMsg("否");
                }
            }
        } else {
            list = new ArrayList<MenuItemInfo>();
        }
        return list;
    }

    public void save(MenuItemInfo menuItemInfo) {
        String id = menuItemInfo.getId();
        if (id != null && !id.toString().equals("")) {
            menuItemInfoDao.update(menuItemInfo);
        } else {
            menuItemInfo.setId(UUIDGenerator.getUUID());
            menuItemInfo.setDelFlag(0);
            menuItemInfo.setCreateTime(new Date());
            menuItemInfoDao.save(menuItemInfo);
        }
    }

    public MenuItemInfo find(MenuItemInfo menuItemInfo) {
        // TODO Auto-generated method stub
        return menuItemInfoDao.queryList(menuItemInfo).get(0);
    }

    public void delete(MenuItemInfo menuItemInfo) {
        // TODO Auto-generated method stub
        menuItemInfoDao.delete(menuItemInfo);
    }
    /**此处有魔法，慎入.新的检查权限的方法写在AuthorityMenuInfoManager当中
     *
     */

    public boolean hasAuthority(String url,String uuid) {
        boolean o = false;
        List<String> roles = new ArrayList<String>();
        if (uuid != null) {
            // 过滤角色权限
            List<AuthorityUserInfo> aUsers = authorityUserInfoDao.query(uuid,null);
            for (int i = 0; i < aUsers.size(); i++) {
                roles.add(aUsers.get(i).getRole().getId());
            }
        }
        List<MenuItemInfo> aList = new ArrayList<MenuItemInfo>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentMenu", "-1");
        param.put("roles", roles);
        List<AuthorityMenuInfo> list = authorityMenuInfoDao.queryByParentMenuMap(param);
        Map<String, AuthorityMenuInfo> res = new LinkedHashMap<String, AuthorityMenuInfo>();
        for (AuthorityMenuInfo authorityMenuInfo : list) {
            res.put(authorityMenuInfo.getMenu(), authorityMenuInfo);
        }
        Iterator<AuthorityMenuInfo> iit = res.values().iterator();
        AuthorityMenuInfo aMenu = null;
        MenuItemInfo menuItem =null;
        while (iit.hasNext()) {
            menuItem = new MenuItemInfo();
            aMenu = iit.next();
            menuItem.setMenuUrl(aMenu.getMenuItems().getMenuUrl());
            menuItem.setId(aMenu.getMenuItems().getId());
            if (hasChild(menuItem.getId())) {
                convertMenu(aList,menuItem.getId());
            } else {
                aList.add(menuItem);
            }
        }
        Map<String,String> nodeList=new HashMap<String, String>();
        for (MenuItemInfo node : aList) {

            nodeList.put(node.getMenuUrl(),node.getMenuUrl());
        }
        if(nodeList.containsValue(url)){
            o=true;
        }
        return o;
    }

    private void convertMenu(List<MenuItemInfo> menuItems,String parentId) {
        List<MenuItemInfo> aList = query(parentId);
        Iterator<MenuItemInfo> iit = aList.iterator();
        MenuItemInfo menuItem = null;
        while (iit.hasNext()) {
            menuItem = iit.next();
            menuItems.add(menuItem);
            if (hasChild(menuItem.getId())) {
                convertMenu(menuItems, menuItem.getId());
            }
        }
    }


    public boolean hasChild(String parentId) {
        boolean res = false;
        List<MenuItemInfo> list = query(parentId);
        if (list != null && list.size() > 0) {
            res = true;
        }
        return res;
    }

}

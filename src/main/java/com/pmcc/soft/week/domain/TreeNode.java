package com.pmcc.soft.week.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunyake on 15/7/15.
 */
public class TreeNode {
    public String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String href;
    public String text;
    public String icon;
    public String code;

    public List<TreeNode> nodes;

    public TreeNode(){
        this.href="";
        this.text="";
        this.code="";
        this.icon="glyphicon glyphicon-tower";
        this.nodes=new ArrayList<TreeNode>();
    }

    public TreeNode(String href,String text){
        this.href=href;
        this.text=text;
        this.code="";
        this.icon="glyphicon glyphicon-tower";
        this.nodes=new ArrayList<TreeNode>();
    }
    public TreeNode(String href,String text,String code,String icon){
        this.href=href;
        this.text=text;
        this.code=code;
        this.icon=icon;
        this.nodes=new ArrayList<TreeNode>();
    }

    public TreeNode(String href,String text,String icon){
        this.href=href;
        this.text=text;
        this.code="";
        this.icon=icon;
        this.nodes=new ArrayList<TreeNode>();
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void addNode(TreeNode node){
                this.nodes.add(node);
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes)
    {
        this.nodes = nodes;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

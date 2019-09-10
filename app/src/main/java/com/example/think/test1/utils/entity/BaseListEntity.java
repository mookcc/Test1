package com.example.think.test1.utils.entity;

import java.util.List;

/**
 * @author 406
 * @version 1.0.0
 * @ClassName BaseListEntity
 * @Description 列表数据bean  配合BaseEntity泛型使用
 * @E-mail
 * @time 2018/06/04
 */
public class BaseListEntity<T> {

    private List<T> list;
    private int totalpage;
    private int ps;
    private int pno;

    private String last_view;// "92"//最近浏览到第几话,未浏览该字段为空字符串


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getLast_view() {
        return last_view;
    }

    public void setLast_view(String last_view) {
        this.last_view = last_view;
    }

    @Override
    public String toString() {
        return "BaseListEntityOfMall{" +
                "list=" + list +
                ", totalpage='" + totalpage + '\'' +
                ", ps='" + ps + '\'' +
                ", pno='" + pno + '\'' +
                '}';
    }


}

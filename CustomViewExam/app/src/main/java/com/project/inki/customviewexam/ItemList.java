package com.project.inki.customviewexam;

public class ItemList {
    String name;
    String number;
    String listnumber;
    int res;

    public ItemList(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public ItemList(String name, String number, String listnumber, int res) {
        this.name = name;
        this.number = number;
        this.listnumber = listnumber;
        this.res = res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getListnumber() {
        return listnumber;
    }

    public void setListnumber(String listnumber) {
        this.listnumber = listnumber;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }


}

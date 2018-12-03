package cn.yinxm.lib.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinxm on 2016/8/18.
 */

public class CationList {
    private long contactID;
    private String name;
    private List<String> list=new ArrayList<>();

    public CationList(long contactID, String name, List<String> list) {
        this.contactID = contactID;
        this.name = name;
        this.list = list;
    }

    public CationList() {
        super();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CationList{");
        sb.append("contactID=").append(contactID);
        sb.append(", name='").append(name).append('\'');
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }

    public long getContactID() {
        return contactID;
    }

    public void setContactID(long contactID) {
        this.contactID = contactID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

}

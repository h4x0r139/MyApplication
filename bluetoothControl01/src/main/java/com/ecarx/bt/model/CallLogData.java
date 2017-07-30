package com.ecarx.bt.model;

import java.io.Serializable;

/**
 * Created by kongnan on 16/12/14.
 * 通话记录
 */
public class CallLogData implements Serializable {

    public String name = "";
    public String phone = "";
    public int type = 1;
    public long date = 0;
    public String time = "";
    public int duration = 0;
    public int count = 1;
    public String id;
}

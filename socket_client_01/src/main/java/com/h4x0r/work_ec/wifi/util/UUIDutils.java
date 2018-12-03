package com.h4x0r.work_ec.wifi.util;

import java.util.UUID;

/**
 * Created by yinxm on 2016/8/2.
 */

public class UUIDutils {

    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        return uniqueId;
    }
}

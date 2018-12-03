package cn.yinxm.lib.api;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static cn.yinxm.lib.api.LocationType.AMAP;
import static cn.yinxm.lib.api.LocationType.BAIDU;
import static cn.yinxm.lib.api.LocationType.OS_NATIVE;

/**
 * <p>
 *
 * @author yinxuming
 * @date 2018/9/11
 */
@IntDef({OS_NATIVE, BAIDU, AMAP})
@Retention(RetentionPolicy.SOURCE)
public @interface LocationType {
    /**
     * 系统自带
     */
    int OS_NATIVE = 0;
    /**
     * 百度
     */
    int BAIDU = 1;
    /**
     * 高德
     */
    int AMAP = 2;
}

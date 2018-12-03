package cn.yinxm.lib.api.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import cn.yinxm.lib.api.ILocationApi;
import cn.yinxm.lib.api.LocationType;
import cn.yinxm.lib.api.bean.LocationInfo;
import cn.yinxm.lib.api.iml.OsNativeLocationApi;


/**
 * app自定义定位管理器
 * 使用：
 * 默认30分钟定位一次
 * 1、app初始化
 * AppLocationManger.getInstance().init(
 * getApplicationContext(),
 * true,
 * LocationType.OS_NATIVE,
 * new ILocationApi.OnLocationUpdate())
 * <p>
 * 2、获取经纬度
 * AppLocationManger.getInstance().getLongitude();
 * <p>
 *
 * @author yinxuming
 * @date 2018/9/10
 */
public class AppLocationManger {
    private static final String TAG = "AppLocationManger";

    private static final String FILE_NAME = "location_config";
    private static final String KEY_LON = "lon";
    private static final String KEY_LAT = "lat";
    private static final String KEY_UPDATE_TIME = "time";

    /**
     * 缓存默认有效期,30分钟
     */
    private static final int CACHE_VALID_PERIOD = 30 * 60 * 1000;

    private Context mContext;
    private boolean isUseCache = true;
    private ILocationApi mLocationApi;
    private ILocationApi.OnLocationUpdate mlocationUpdateForManager;
    private ILocationApi.OnLocationUpdate mLocationUpdate;
    boolean isInit = false;

    private int cacheValidPeriod = CACHE_VALID_PERIOD;

    private AppLocationManger() {
        mlocationUpdateForManager = new ILocationApi.OnLocationUpdate() {
            @Override
            public void onLocationUpdte(boolean isSucess, LocationInfo lastLocationInfo) {
                Log.d(TAG, "onLocationUpdte isSucess=" + isSucess + ", lastLocationInfo=" + lastLocationInfo);
                if (isUseCache) {
                    cacheLocation(lastLocationInfo);
                }
                if (mLocationUpdate != null) {
                    mLocationUpdate.onLocationUpdte(isSucess, lastLocationInfo);
                }
            }
        };
    }

    public static AppLocationManger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static AppLocationManger INSTANCE = new AppLocationManger();
    }


    private ILocationApi getLocationApi() {
        if (mLocationApi == null) {
            if (mContext == null) {
                throw new IllegalArgumentException("Context can not be null , call init first");
            }
            mLocationApi = new OsNativeLocationApi(mContext);
        }
        return mLocationApi;
    }

    public void init(Context context, boolean isUseCache, @LocationType int locationType, ILocationApi.OnLocationUpdate
            locationUpdate) {
        this.isUseCache = isUseCache;
        this.mContext = context.getApplicationContext();

        if (LocationType.OS_NATIVE == locationType) {
            mLocationApi = new OsNativeLocationApi(mContext);
        } else {
            throw new IllegalArgumentException("locationType is not known");
        }
        mLocationApi.setMinLocationTime(CACHE_VALID_PERIOD);
        startUpdateLocation(locationUpdate);
        isInit = true;
    }


    public void startUpdateLocation(ILocationApi.OnLocationUpdate locationUpdate) {
        this.mLocationUpdate = locationUpdate;
        getLocationApi().startUpdateLocation(mlocationUpdateForManager);
    }

    /**
     * 获取精度x
     *
     * @return
     */
    public double getLongitude() {
        return getLocationKey(KEY_LON);
    }


    /**
     * 获取纬度
     *
     * @return
     */
    public double getLatitude() {
        return getLocationKey(KEY_LAT);
    }

    public void setCacheValidPeriod(int cacheValidPeriod) {
        this.cacheValidPeriod = cacheValidPeriod;
        getLocationApi().setMinLocationTime(cacheValidPeriod);
    }

    private double getLocationKey(String key) {
        double rtn = 0.0;
        if (!isInit) {
            Log.e(TAG, "location is not init");
            return rtn;
        }
        // 缓存失效
        boolean cacheInvalid = false;

        if (isUseCache) {
            // 1、内存
            LocationInfo locationInfo = getLocationApi().getLastLocationInfo();
            if (locationInfo != null) {
                if (KEY_LON == key) {
                    rtn = locationInfo.getLongitude();
                } else if (KEY_LAT == key) {
                    rtn = locationInfo.getLatitude();
                }
                if (System.currentTimeMillis() - locationInfo.getUpdateTime() > cacheValidPeriod
                        && key == KEY_LON) {
                    // 缓存失效，获取经度时更新缓存
                    cacheInvalid = true;
                    Log.d(TAG, "mem cache invalid ");
                }
            }

            // 2、磁盘
            if (rtn == 0.0) {
                rtn = getSpLocation(key);
                // 缓存是否失效
                SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
                long upTime = sharedPreferences.getLong(KEY_UPDATE_TIME, 0L);
                if (System.currentTimeMillis() - upTime > cacheValidPeriod
                        && key == KEY_LON) {
                    // 缓存失效，获取经度时更新缓存
                    cacheInvalid = true;
                    Log.d(TAG, "disk cache invalid ");
                }
            }

            // 3、更新定位，只有请求经度时才更新
            if (rtn == 0.0 && key == KEY_LON || cacheInvalid) {
                Log.d(TAG, "relocation rtn=" + rtn + ", cacheInvalid=" + cacheInvalid);
                getLocationApi().startUpdateLocation(mlocationUpdateForManager);
            }
        } else {
            LocationInfo locationInfo = getLocationApi().getLastLocationInfo();
            if (locationInfo != null) {
                if (KEY_LON == key) {
                    rtn = locationInfo.getLongitude();
                } else if (KEY_LAT == key) {
                    rtn = locationInfo.getLatitude();
                }
            } else if (key == KEY_LON) {
                getLocationApi().startUpdateLocation(mlocationUpdateForManager);
            }
        }
        return rtn;
    }

    private double getSpLocation(String key) {
        double rtn = 0.0;
        try {
            SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            String str = sharedPreferences.getString(key, "0.0");
            rtn = Double.parseDouble(str);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return rtn;
    }

    private void cacheLocation(LocationInfo lastLocationInfo) {
        if (lastLocationInfo != null
                && lastLocationInfo.getLongitude() != 0.0
                && lastLocationInfo.getLatitude() != 0.0) {
            SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_LON, "" + lastLocationInfo.getLongitude());
            editor.putString(KEY_LAT, "" + lastLocationInfo.getLatitude());
            editor.putLong(KEY_UPDATE_TIME, lastLocationInfo.getUpdateTime());
            editor.commit();
        }
    }
}

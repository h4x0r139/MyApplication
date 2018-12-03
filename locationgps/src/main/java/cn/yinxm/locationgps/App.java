package cn.yinxm.locationgps;

        import android.app.Application;
        import android.util.Log;

        import cn.yinxm.lib.api.ILocationApi;
        import cn.yinxm.lib.api.LocationType;
        import cn.yinxm.lib.api.bean.LocationInfo;
        import cn.yinxm.lib.api.manager.AppLocationManger;

/**
 * <p>
 *
 * @author yinxuming
 * @date 2018/9/10
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppLocationManger.getInstance().init(
                getApplicationContext(),
                true, LocationType.OS_NATIVE,
                new ILocationApi.OnLocationUpdate() {
                    @Override
                    public void onLocationUpdte(boolean isSucess, LocationInfo lastLocationInfo) {
                        Log.d("yinxm", "onLocationUpdte " + isSucess + ", lastLocationInfo" + lastLocationInfo);
                    }
                });
    }
}

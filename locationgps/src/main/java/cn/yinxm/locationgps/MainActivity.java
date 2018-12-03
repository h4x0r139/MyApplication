package cn.yinxm.locationgps;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.yinxm.lib.api.ILocationApi;
import cn.yinxm.lib.api.bean.LocationInfo;
import cn.yinxm.lib.api.manager.AppLocationManger;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);


//        initLocation();
    }

    private void initLocation() {
        LocationApi locationApi = new LocationApi(getApplicationContext(), new LocationApi.OnLocationChangeListener() {
            @Override
            public void onLocationChanged(ICustomLocation location) {
                Log.d("yinxm", "onLocationChanged="+location);
                tv.setText(location.getAddress());
            }
        });
        locationApi.startRequestLocationUpdates();
        Location location = locationApi.getCurrentLocation();
        Log.d("yinxm", "location="+location);

    }

    public void updateLocation(View view) {
        Log.d("yinxm", "updateLocation ");
        AppLocationManger.getInstance().startUpdateLocation(new ILocationApi.OnLocationUpdate() {
            @Override
            public void onLocationUpdte(boolean isSucess, LocationInfo lastLocationInfo) {
                Log.d("yinxm", "onLocationUpdte " + isSucess + ", lastLocationInfo" + lastLocationInfo);
                tv.setText(lastLocationInfo.toString());
            }
        });
    }

    public void getLonLat(View view) {
       double lon = AppLocationManger.getInstance().getLongitude();
       double lat = AppLocationManger.getInstance().getLatitude();
       tv.setText("lon="+lon+", lat="+lat);
    }
}

package cn.yinxm.locationgps;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        initLocation();
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
}

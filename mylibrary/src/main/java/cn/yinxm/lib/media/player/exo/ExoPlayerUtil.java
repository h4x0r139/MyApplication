package cn.yinxm.lib.media.player.exo;

import android.content.Context;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by yinxm on 2017/1/12.
 * 功能:
 */

public class ExoPlayerUtil {
    private ExoPlayerUtil(){}
    private static ExoPlayerUtil instance = new ExoPlayerUtil();


    protected String userAgent;
    private Context context;

    public static ExoPlayerUtil getInstance() {
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        userAgent = Util.getUserAgent(context, "ExoPlayerDemo");
    }

    public DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(context, bandwidthMeter,
                buildHttpDataSourceFactory(bandwidthMeter));
    }

    public HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultHttpDataSourceFactory(userAgent, bandwidthMeter);
    }

    /**
     * Returns a new DataSource factory.
     *
     * @param useBandwidthMeter Whether to set BANDWIDTH_METER as a listener to the new
     *     DataSource factory.
     * @return A new DataSource factory.
     */
    public DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter, DefaultBandwidthMeter bandwidthMeter) {
        return buildDataSourceFactory(useBandwidthMeter ? bandwidthMeter : null);
    }

    /**
     * Returns a new HttpDataSource factory.
     *
     * @param useBandwidthMeter Whether to set BANDWIDTH_METER as a listener to the new
     *     DataSource factory.
     * @return A new HttpDataSource factory.
     */
    public HttpDataSource.Factory buildHttpDataSourceFactory(boolean useBandwidthMeter, DefaultBandwidthMeter bandwidthMeter) {
        return buildHttpDataSourceFactory(useBandwidthMeter ? bandwidthMeter : null);
    }

}

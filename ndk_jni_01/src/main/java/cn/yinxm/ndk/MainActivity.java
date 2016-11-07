package cn.yinxm.ndk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static android.R.attr.data;
import static android.R.attr.vendor;

public class MainActivity extends AppCompatActivity {

    static{
        System.loadLibrary("ndk_jni_01");
//        java.lang.UnsatisfiedLinkError: dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/cn.yinxm.ndk-1/base.apk"],
//        nativeLibraryDirectories=[/data/app/cn.yinxm.ndk-1/lib/arm, /data/app/cn.yinxm.ndk-1/base.apk!/lib/armeabi-v7a, /vendor/lib, /system/lib]]] couldn't find "libndk_jni_01c.so"

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv=(TextView)findViewById(R.id.tv);
        tv.setText(getStrFromJni());
    }

    public native String getStrFromJni();
}

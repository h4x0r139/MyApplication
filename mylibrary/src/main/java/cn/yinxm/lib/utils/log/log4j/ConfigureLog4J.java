package cn.yinxm.lib.utils.log.log4j;

import android.content.Context;
import android.os.Environment;

import org.apache.log4j.Level;

import java.io.File;
import java.util.Date;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * 功能：
 * Created by yinxm on 2017/7/11.
 */

public class ConfigureLog4J {
//日志级别优先度从高到低:OFF(关闭),FATAL(致命),ERROR(错误),WARN(警告),INFO(信息),DEBUG(调试),ALL(打开所有的日志，我的理解与DEBUG级别好像没有什么区别得)
//Log4j建议只使用FATAL ,ERROR ,WARN ,INFO ,DEBUG这五个级别。
    // "yyyy-MM-dd");// 日志的输出格式

    private Context context;
    private String filePath = "";
    private String fileName = "xiaoka_log.txt";
    private long maxFileSize = 1024 * 1024 * 10;//默认10M

    public ConfigureLog4J(Context context) {
        this.context = context;
    }

    public LogConfigurator getDefaultLogConfig() {
        initData();
        LogConfigurator logConfigurator = new LogConfigurator();
        Date nowtime = new Date();
        // String needWriteMessage = myLogSdf.format(nowtime);
        //日志文件路径地址:SD卡下myc文件夹log文件夹的test文件
//        String fileName = Environment.getExternalStorageDirectory()
//                + File.separator + "myc" + File.separator + "log"
//                + File.separator + "test.log";

        String fileName = filePath;
        //设置文件名
        logConfigurator.setFileName(fileName);
        //设置root日志输出级别 默认为DEBUG
        logConfigurator.setRootLevel(Level.DEBUG);
        // 设置日志输出级别
        logConfigurator.setLevel("org.apache", Level.DEBUG);
        //设置 输出到日志文件的文字格式 默认 %d %-5p [%c{2}]-[%L] %m%n
        logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
        //设置输出到控制台的文字格式 默认%m%n
        logConfigurator.setLogCatPattern("%m%n");
        //设置总文件大小
        logConfigurator.setMaxFileSize(maxFileSize);
        //设置最大产生的文件个数
        logConfigurator.setMaxBackupSize(1);
        //设置所有消息是否被立刻输出 默认为true,false 不输出
        logConfigurator.setImmediateFlush(true);
        //是否本地控制台打印输出 默认为true ，false不输出
        logConfigurator.setUseLogCatAppender(true);
        //设置是否启用文件附加,默认为true。false为覆盖文件
        logConfigurator.setUseFileAppender(true);
        //设置是否重置配置文件，默认为true
        logConfigurator.setResetConfiguration(true);
        //是否显示内部初始化日志,默认为false
        logConfigurator.setInternalDebugging(false);

        return logConfigurator;
    }

    public void initDefaultConfig() {
        LogConfigurator  logConfigurator = getDefaultLogConfig();
        if (logConfigurator != null) {
            logConfigurator.configure();
        }
    }

    public void init(LogConfigurator logConfigurator) {
        if (logConfigurator != null) {
            logConfigurator.configure();
        }else {
            initDefaultConfig();
        }
    }

    private void initData() {
        boolean state = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());

        try {
            if (state && Environment.getExternalStorageDirectory() != null) {
                filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            } else if (context != null && context.getExternalCacheDir() != null) {
                filePath = context.getExternalCacheDir().getAbsolutePath();
            } else if(context != null) {
                filePath = context.getCacheDir().getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath == null) {
            filePath = "";
        }
        filePath = filePath+File.separator+"xiaoka_log";

        if (fileName == null || "".equals(fileName)) {
            fileName = "xiaoka_log.txt";
        }
        filePath = filePath + File.separator+fileName;

        if (maxFileSize <=0 ) {
            maxFileSize = 1024 * 1024 * 10;
        }
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
}

package cn.yinxm.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.yinxm.lib.utils.log.LogUtil;

/**
 * Created by Administrator on 2016/4/11.
 */
public class SpUtil {

    /**
     * 写入sp配置文件，
     *
     * @param context
     * @param configName 文件名
     * @param key        键
     * @param value      值
     */
    public static void spWriteStr(Context context, String configName, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 读取sp
     *
     * @param context
     * @param configName 文件名
     * @param key        读取键
     * @return
     */
    public static String spReadStr(Context context, String configName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    /**
     * 写入布尔型数据
     *
     * @param context    上下文
     * @param configName 文件名
     * @param key        键
     * @param value      值
     */
    public static void writeBoolean(Context context, String configName, String key, Boolean value) {
        SharedPreferences preference = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 读取布尔型数据
     *
     * @param context    上下文
     * @param configName 文件名
     * @param key        键
     * @param defValue   默认的值，如果不存在则以此值返回
     * @return boolean 布尔数据
     */
    public static boolean readBoolean(Context context, String configName, String key, boolean defValue) {
        SharedPreferences preference = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
        return preference.getBoolean(key, defValue);
    }

    /**
     * 写入整型数据
     *
     * @param context    上下文
     * @param configName 文件名
     * @param key        键
     * @param value      值
     */
    public static void wirteInt(Context context, String configName, String key, int value) {
        SharedPreferences preference = context.getSharedPreferences(
                configName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 读取整型数据
     *
     * @param context    上下文
     * @param configName 文件名
     * @param key        键
     * @param defValue   默认值
     * @return int 整型数据
     */
    public static int readInt(Context context, String configName, String key, int defValue) {
        SharedPreferences preference = context.getSharedPreferences(
                configName, Context.MODE_PRIVATE);
        return preference.getInt(key, defValue);
    }

    /**
     * 写入搜索历史
     *
     * @param context
     * @param content 为当前搜索的东西(当前搜索东西的名字)
     * @param fieName sp命名空间
     */
    public static void writeHistory(Context context, String fieName, String content) {
        SharedPreferences sp = context.getSharedPreferences(fieName, Context.MODE_PRIVATE);
        ArrayList<String> list = readHistory(context, fieName);
        clearHistory(context, fieName);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equals(content)) {
                it.remove();
            }
        }
        list.add(0, content);
        int size = list.size() >= 5 ? 5 : list.size();
        SharedPreferences.Editor editor = sp.edit();
        for (int i = size - 1; i >= 0; i--) {
            editor.putString("content" + i, "" + list.get(i));
            editor.commit();
        }
        LogUtil.d("####写入历史###");
    }

    /**
     * 读取搜索历史记录
     *
     * @param context
     * @param fileName
     * @return
     */
    public static ArrayList<String> readHistory(Context context, String fileName) {
        ArrayList<String> list = new ArrayList<>();
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        String content;

        for (int i = 0; i < 5; i++) {
            content = sp.getString("content" + i, null);
            if (content == null) {
                break;
            }
            list.add(content);
        }
        return list;
    }

    /**
     * 清除历史记录
     *
     * @param context
     * @param fileName
     */
    public static void clearHistory(Context context, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 存储集合
     *
     * @param context
     * @param filename
     * @param key
     * @param list
     */
    public static void putList(Context context, String filename, String key, List list) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(filename,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = mySharedPreferences.edit();
        try {
            String listStr = ListToString(list);
            edit.putString(key, listStr);
            edit.commit();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 获取集合
     *
     * @param context
     * @param filename
     * @param key
     * @return
     */
    public static List getList(Context context, String filename, String key) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        String listStr = sharedPreferences.getString(key, "");
        try {
            List list = StringToList(listStr);
            return list;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * List转字符串
     *
     * @param list
     * @return
     * @throws IOException
     */
    public static String ListToString(List list)
            throws IOException {
        //创建ByteArrayOutputStream对象，用来存放字节文件。
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //得到的字符放到到ObjectOutputStream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
        objectOutputStream.writeObject(list);
        //Base64.encode将字节文件转换成Base64编码存在String中
        String string = new String(Base64.encode(
                byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        // 关闭Stream
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return string;

    }

    /**
     * 字符串转list
     *
     * @param string
     * @return
     * @throws StreamCorruptedException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static List StringToList(String string)
            throws StreamCorruptedException, IOException,
            ClassNotFoundException {
        byte[] b = Base64.decode(string.getBytes(),
                Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                b);
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        List list = (List) objectInputStream
                .readObject();
        // 关闭Stream
        objectInputStream.close();
        byteArrayInputStream.close();
        return list;
    }

    /**
     * 保存对象
     *
     * @param context
     * @param object
     * @param key
     * @return
     */
    public static boolean setObjectToSp(Context context, String key, Object object) {
        SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(context);
        if (object == null) {
            SharedPreferences.Editor editor = share.edit().remove(key);
            return editor.commit();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        String objectStr = new String(Base64.encode(baos.toByteArray(),
                Base64.DEFAULT));
        try {
            baos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = share.edit();
        editor.putString(key, objectStr);
        return editor.commit();
    }

    /**
     * 读取对象
     *
     * @param context
     * @param key
     * @return
     */
    public static Object readObjectFromSp(Context context, String key) {
        SharedPreferences sharePre = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            String wordBase64 = sharePre.getString(key, "");
            if (wordBase64 == null || wordBase64.equals("")) {
                return null;
            }
            byte[] objBytes = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();
            bais.close();
            ois.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

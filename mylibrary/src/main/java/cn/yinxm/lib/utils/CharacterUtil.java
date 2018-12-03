package cn.yinxm.lib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yinxm on 2017/4/12.
 * 功能:
 */

public class CharacterUtil {
	
/*	public static void main(String[] args) {
        //中文乱码测试
      System.out.println(isMessyCode("Ã©Å¸Â©Ã©Â¡ÂºÃ¥Â¹Â³"));
      System.out.println(isMessyCode("你好"));
      System.out.println("1="+isMessyCode("��"));
      System.out.println("2="+isMessyCode("+8617097212908@work_ec.m800-api.com_###_+8617097212908@work_ec.m800-api.com.out.1491993133000.YTw"));
      System.out.println("3="+isMessyCode("坚挺爱十分aweohhasd阿斯顿发hi啊!！@#%……&*（）——+？“。"));
      System.out.println("4="+isMessyCode("坚挺爱十分aweohhasd阿斯顿发hi啊"));
      
      System.out.println("10"+CharacterUtil.filterMessyCode("Ã©Å¸Â©Ã©Â¡ÂºÃ¥Â¹Â³"));
      System.out.println("11"+CharacterUtil.filterMessyCode("Ã©Å¸Â©Ã©Â你好¡ÂºÃ¥哈哈Â¹Â³"));
      System.out.println("1="+CharacterUtil.filterMessyCode("��你，阿斯顿发had"));//判断错误
      System.out.println("2="+CharacterUtil.filterMessyCode("+8617097212908@work_ec.m800-api.com_###_+8617097212908@work_ec.m800-api.com.out.1491993133000.YTw"));
      System.out.println("3="+CharacterUtil.filterMessyCode("坚挺爱十分aweohhasd阿斯顿发hi啊!！@#%……&*（）——+？“。"));
      System.out.println("4="+CharacterUtil.filterMessyCode("坚挺爱十分aweohhasd阿斯顿发hi啊"));
	}*/

    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 过滤乱码
     * 还没测试
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    @Deprecated
    public static String filterMessyCode(String strName) {
    	if (strName == null) {
    		return strName;
    	}
    	StringBuilder stringBuilder = new StringBuilder();
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        System.out.println("temp1="+after);
        String temp = after.replaceAll("\\p{P}", "");
        System.out.println("temp2="+strName+"\ntemp3="+temp);
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                    stringBuilder.append(c);
                }
            }
        }
//        float result = count / chLength;
//        if (result > 0.4) {
//            return true;
//        } else {
//            return false;
//        }
        return stringBuilder.toString();

    }



    /** 判断字符是否是中文
     * @param c 字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


}

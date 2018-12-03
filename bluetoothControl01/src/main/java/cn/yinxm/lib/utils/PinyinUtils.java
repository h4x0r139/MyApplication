package cn.yinxm.lib.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Locale;

import utils.StringUtil;

/**
 * 拼音帮助类
 *
 * @author kongnan
 */
public class PinyinUtils {

    private static final String CHINESE_REGEX = "[\u4e00-\u9fa5]";
    private static final String EMPTY_STRING = "";
    private static final String SPACE_STRING = " ";

    /**
     * 私有构造方法
     */
    private PinyinUtils() {
        super();
    }

    /**
     * 生成中文拼音首字母
     *
     * @param chinese 中文字符串
     * @return 中文拼音首字母
     */
    public static String cn2FirstSpell(String chinese) {
        return cn2FirstSpell(chinese, true);
    }

    /**
     * 生成中文拼音首字母
     *
     * @param chinese              中文字符串
     * @param ignoreNonChineseChar 是否忽略非中文字符
     * @return 中文拼音首字母
     */
    public static String cn2FirstSpell(String chinese, boolean ignoreNonChineseChar) {
        return cn2FirstSpell(chinese, ignoreNonChineseChar, true);
    }

    /**
     * 生成中文拼音首字母
     *
     * @param chinese              中文字符串
     * @param ignoreNonChineseChar 是否忽略非中文字符
     * @param lowerCase            生成结果是否为小写字母
     * @return 中文拼音首字母
     */
    public static String cn2FirstSpell(String chinese, boolean ignoreNonChineseChar, boolean lowerCase) {
        return cn2FirstSpell(chinese, ignoreNonChineseChar, lowerCase, EMPTY_STRING);
    }

    /**
     * 生成中文拼音首字母
     *
     * @param chinese              中文字符串
     * @param ignoreNonChineseChar 是否忽略非中文字符
     * @param lowerCase            生成结果是否为小写字母
     * @param separator            分隔符
     * @return 中文拼音首字母
     */
    public static String cn2FirstSpell(String chinese, boolean ignoreNonChineseChar, boolean lowerCase,
                                       String separator) {

        if (chinese == null || StringUtil.isBlank(chinese)) {
            return EMPTY_STRING;
        }

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

        if (lowerCase) {
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        } else {
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        }

        StringBuilder result = new StringBuilder();

        char[] arr = chinese.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (!new String(new char[]{c}).matches(CHINESE_REGEX)) {
                if (!ignoreNonChineseChar) {
                    result.append(c);
                    if (i != arr.length - 1) {
                        result.append(separator);
                    }
                }
                continue;
            }
            try {
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (pinyinArray != null && pinyinArray.length != 0) {
                    result.append(pinyinArray[0].charAt(0));
                    if (i != arr.length - 1) {
                        result.append(separator);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        }
        return result.toString().trim();
    }

    /**
     * 中文串换为拼音
     *
     * @param chinese 中文字符串
     * @return 拼音
     */
    public static String cn2Spell(String chinese) {
        return cn2Spell(chinese, true);
    }

    /**
     * 中文串换为拼音
     *
     * @param chinese              中文字符串
     * @param ignoreNonChineseChar 是否忽略非中文字符
     * @return 拼音
     */
    public static String cn2Spell(String chinese, boolean ignoreNonChineseChar) {
        return cn2Spell(chinese, ignoreNonChineseChar, true);
    }

    /**
     * 中文串换为拼音
     *
     * @param chinese              中文字符串
     * @param ignoreNonChineseChar 是否忽略非中文字符
     * @param lowerCase            生成结果是否为小写字母
     * @return 拼音
     */
    public static String cn2Spell(String chinese, boolean ignoreNonChineseChar, boolean lowerCase) {
        return cn2Spell(chinese, ignoreNonChineseChar, false, lowerCase, SPACE_STRING);
    }

    /**
     * 中文串换为拼音
     *
     * @param chinese              中文字符串
     * @param ignoreNonChineseChar 是否忽略非中文字符
     * @param firstLetter          是否首字母大写
     * @param lowerCase            生成结果是否为小写字母
     * @param separator            分隔符
     * @return 拼音
     */
    public static String cn2Spell(String chinese, boolean ignoreNonChineseChar, boolean firstLetter,
                                  boolean lowerCase, String separator) {
        if (chinese == null || StringUtil.isBlank(chinese)) {
            return EMPTY_STRING;
        }

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

        if (lowerCase) {
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        } else {
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        }

        StringBuilder result = new StringBuilder();

        char[] arr = chinese.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (!new String(new char[]{c}).matches(CHINESE_REGEX)) {// 非汉字
                if (!ignoreNonChineseChar) {
                    result.append(c);
                    if (i != arr.length - 1) {
                        result.append(separator);
                    }
                }
                continue;
            }
            try {
                // 汉字
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (pinyinArray != null && pinyinArray.length != 0) {
                    String py = pinyinArray[0].substring(0, pinyinArray[0].length() - 1);
                    if (firstLetter) {
                        // 大写第一个字母
                        char f = py.charAt(0);
                        String pinyin = String.valueOf(f).toUpperCase(Locale.getDefault())
                                .concat(py.substring(1));
                        result.append(pinyin);
                    } else {
                        result.append(py);
                    }
                    if (i != arr.length - 1) {
                        result.append(separator);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        }

        return result.toString().trim();
    }

    /**
     * 将拼音转换为 T9数字
     *
     * @param pinyin
     * @return
     */
    public static String pinyinConvertToNumber(String pinyin) {
        StringBuilder numberString = new StringBuilder();
        char[] self = pinyin.toCharArray();
        for (char c : self) {
            if (c == 'a' || c == 'A' || c == 'b' || c == 'B' || c == 'c' || c == 'C') {
                numberString.append(2);
            } else if (c == 'd' || c == 'D' || c == 'e' || c == 'E' || c == 'f' || c == 'F') {
                numberString.append(3);
            } else if (c == 'g' || c == 'G' || c == 'h' || c == 'H' || c == 'i' || c == 'I') {
                numberString.append(4);
            } else if (c == 'j' || c == 'J' || c == 'k' || c == 'K' || c == 'l' || c == 'L') {
                numberString.append(5);
            } else if (c == 'm' || c == 'M' || c == 'n' || c == 'N' || c == 'o' || c == 'O') {
                numberString.append(6);
            } else if (c == 'p' || c == 'P' || c == 'q' || c == 'Q' || c == 'r' || c == 'R' || c == 's'
                    || c == 'S') {
                numberString.append(7);
            } else if (c == 't' || c == 'T' || c == 'u' || c == 'U' || c == 'v' || c == 'V') {
                numberString.append(8);
            } else if (c == 'w' || c == 'W' || c == 'x' || c == 'X' || c == 'y' || c == 'Y' || c == 'z'
                    || c == 'Z') {
                numberString.append(9);
            } else {
                numberString.append(c);
            }
        }

        return numberString.toString();
    }
}

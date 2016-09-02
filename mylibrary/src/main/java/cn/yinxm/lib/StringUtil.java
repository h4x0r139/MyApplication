package cn.yinxm.lib;


/**
 * 字符串工具类
 * 
 */
public final class StringUtil {
    private StringUtil() {
    }

    public static String ResetText(String str){
        if(str.contains("&nbsp;")){
            String replace = str.replace("&nbsp;", " ");
            return replace;
        }
        return str;
    }
    public static final boolean isBlank(final String str) {
        return str == null || str.trim().equals("") || str.equals("Null") || str.equals("null");
    }

    public static final boolean isNotBlank(final String str) {
       return (!isBlank(str));
    }

    public static final int string2int(final String str, final int def) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return def;
        }
    }

    public static final byte string2byte(final String str, final byte def) {
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException nfe) {
            return def;
        }
    }

    public static final short string2short(final String str, final short def) {
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }
    
    public static final float string2float(final String str, final float def) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }
    
    public static final long string2Long(final String str, final long def) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }
    
    public static final long object2Long(final Object obj, final long def) {
        if(obj == null){
            return def;
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static final double object2Double(final Object obj, final double def) {
        if(obj == null){
            return def;
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (NumberFormatException e) {
            return def;
        }
    }
    
    public static final float object2float(final Object obj, final float def) {
        if(obj == null){
            return def;
        }
        try {
            return Float.parseFloat(obj.toString());
        } catch (NumberFormatException e) {
            return def;
        }
    }
    
    public static final boolean object2boolean(final Object obj, final boolean def) {
        if(obj == null){
            return def;
        }
        try {
            return Boolean.parseBoolean(obj.toString());
        } catch (NumberFormatException e) {
            return def;
        }
    }
    
    
    public static final int object2int(final Object obj, final int def) {
        if(obj == null){
            return def;
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException nfe) {
            return def;
        }
    }
    
    

    public static final boolean contains(final String src, final String regex) {
        if (isBlank(src) || isBlank(regex))
            return false;
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        return contains(src, pattern);
    }

    private static final boolean contains(final String src, final java.util.regex.Pattern regex) {
        if (isBlank(src) || regex == null)
            return false;

        java.util.regex.Matcher m = regex.matcher(src);

        return m.find();
    }

    /* ------------------------------------------------------------ */
    /**
     * replace substrings within string.
     */
    public static final String replace(final String s, final String sub, final String with) {
        int c = 0;
        int i = s.indexOf(sub, c);
        if (i == -1)
            return s;

        StringBuffer buf = new StringBuffer(s.length() + with.length());

        synchronized (buf) {
            do {
                buf.append(s.substring(c, i));
                buf.append(with);
                c = i + sub.length();
            } while ((i = s.indexOf(sub, c)) != -1);

            if (c < s.length())
                buf.append(s.substring(c, s.length()));

            return buf.toString();
        }
    }
}

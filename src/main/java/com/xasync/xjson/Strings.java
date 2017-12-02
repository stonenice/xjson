package com.xasync.iplain;

/**
 * [iplain] Strings
 * <p>
 * Author: stonenice@outlook.com
 * Date: 2016-12-29
 */
public class Strings {
    public static final String EMPTY="";
    public static final String SPACE=" ";

    public static boolean startsWith(String source,String sub){
        return startsWith(source,sub,false);
    }

    public static boolean startsWith(String source,String sub,boolean ignoreCase){
        if(source==null||sub==null) return false;
        return source.regionMatches(ignoreCase,0,sub,0,sub.length());
    }

    public static boolean endsWith(String source,String sub){
        return endsWith(source,sub,false);
    }

    public static boolean endsWith(String source,String sub,boolean ignoreCase){
        if(source==null||sub==null) return false;
        int alen,blen;
        if((alen=source.length())<(blen=sub.length())) return false;
        return source.regionMatches(ignoreCase,alen-blen,sub,0,blen);
    }

    public static String trim(String str){
        return trim(str,null);
    }

    public static String trim(String str,String strip){
        str=ltrim(str,strip);
        return rtrim(str,strip);
    }

    public static String ltrim(String str){
        return ltrim(str,null);
    }

    public static String ltrim(String str,String strip){
        int slen;
        if (str == null || (slen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        strip=strip!=null?strip:EMPTY;
        if (strip.trim().length()<=0) {
            while (start != slen && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else {
            while (start != slen && strip.indexOf(str.charAt(start)) >=0) {
                start++;
            }
        }
        return str.substring(start);
    }

    public static String rtrim(String str){
        return rtrim(str,null);
    }

    public static String rtrim(String str,String strip){
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        strip=strip!=null?strip:EMPTY;

        if (strip.trim().length()<=0) {
            while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        }else {
            while (end != 0 && strip.indexOf(str.charAt(end - 1)) >=0 ) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    public static boolean isEmpty(String str){
        return str!=null?str.length()<=0:true;
    }

    public static boolean realEmpty(String str){
        return str!=null?str.trim().length()<=0:true;
    }
}

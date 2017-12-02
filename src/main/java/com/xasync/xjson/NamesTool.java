package com.xasync.iplain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xasync.iplain.NamesType.*;


/**
 * [iplain] NamesTool
 *
 * Author: stonenice@outlook.com
 * Date: 2016-12-28
 */
public class NamesTool {
    private final static Pattern UnderlineUpperPattern=Pattern.compile("^[A-Z]{1}[A-Z0-9]*(_[A-Z0-9]+)+");
    private final static Pattern UnderlineLowerPattern=Pattern.compile("^[a-z]{1}[a-z0-9]*(_[a-z0-9]+)+");
    private final static Pattern AllUpperPattern=Pattern.compile("^[A-Z]{1}[A-Z0-9]*[A-Z0-9]{1}$");
    private final static Pattern AllLowerPattern=Pattern.compile("^[a-z]+([a-z]{1}[a-z0-9]+)+[a-z0-9]{1}$");
    private final static Pattern CamelLowerPattern=Pattern.compile("^[a-z]{1}[a-z0-9]*(([A-Z]{1}[a-z0-9]+)|([A-Z]$))+");
    private final static Pattern CamelUpperPattern=Pattern.compile("^[A-Z]{1}[a-z0-9]+(([A-Z]?[a-z0-9]+)|([A-Z]$))+");

    public static char NAMES_SEPARATOR = '_';

    public static boolean like(String astr, String bstr){
        astr=astr!=null?astr.trim().toLowerCase():"";
        bstr=bstr!=null?bstr.trim().toLowerCase():"";

        if(astr.length()>0&&bstr.length()>0){
            if(astr.equals(bstr)) return true;
            String s=String.valueOf(NAMES_SEPARATOR);
            astr=astr.replaceAll(s,"");
            bstr=bstr.replaceAll(s,"");
            return astr.equals(bstr);
        }else {
            return false;
        }
    }

    public static NamesType type(String name){
        name=name!=null?name.trim():"";
        if(name.length()<=0) return UNKNOWN;
        char firstChar=name.charAt(0);

        if(firstChar>='0'&&firstChar<='9') return UNKNOWN;

        if(name.indexOf(NAMES_SEPARATOR)>=0){
            Matcher uum = UnderlineUpperPattern.matcher(name);
            Matcher ulm = UnderlineLowerPattern.matcher(name);

            return (uum.matches() ? UNDERLINE_UPPER_CASE
                    :(ulm.matches() ? UNDERLINE_LOWER_CASE
                    : UNKNOWN));
        }else{
            Matcher clm=CamelLowerPattern.matcher(name);
            if(clm.matches()) return CAMEL_LOWER_CASE;

            Matcher cum=CamelUpperPattern.matcher(name);
            if(cum.matches()) return CAMEL_UPPER_CASE;

            Matcher aum=AllUpperPattern.matcher(name);
            if(aum.matches()) return ALL_UPPER_CASE;

            Matcher alm=AllLowerPattern.matcher(name);
            if(alm.matches()) return ALL_LOWER_CASE;

            return UNKNOWN;
        }
    }

    public static String convert(String name, NamesType to){
        NamesType from=type(name);
        if(from== UNKNOWN||to== UNKNOWN||from==to) return name;
        return convert(name,from,to);
    }

    public static String convert(String name, NamesType from, NamesType to){
        name=name!=null?name.trim():"";
        int len=name.length();
        if(len<=0||to== UNKNOWN||from==to) return name;

        //NamesType.CAMEL_LOWER_CASE
        if(from== CAMEL_LOWER_CASE){
            String result;
            switch (to){
                case UNDERLINE_LOWER_CASE:
                    StringBuilder ulc=new StringBuilder();
                    ulc.append(name.charAt(0));
                    if(len>1){
                        for (int i=1;i<len;++i){
                            char c=name.charAt(i);
                            if(c>='A'&&c<='Z'){
                                ulc.append(NAMES_SEPARATOR);
                                ulc.append(c);
                            }else{
                                ulc.append(c);
                            }
                        }
                    }
                    result=ulc.toString().toLowerCase();
                    break;
                case UNDERLINE_UPPER_CASE:
                    StringBuilder uuc=new StringBuilder();
                    uuc.append(name.charAt(0));
                    if(len>1){
                        for (int i=1;i<len;++i){
                            char c=name.charAt(i);
                            if(c>='A'&&c<='Z'){
                                uuc.append(NAMES_SEPARATOR);
                                uuc.append(c);
                            }else{
                                uuc.append(c);
                            }
                        }
                    }
                    result=uuc.toString().toUpperCase();
                    break;
                case CAMEL_UPPER_CASE:
                    char firstChar=name.charAt(0);
                    result=Character.toUpperCase(firstChar)+name.substring(1,len-1);
                    break;
                case ALL_LOWER_CASE:
                    result=name.toUpperCase();
                    break;
                case ALL_UPPER_CASE:
                    result=name.toLowerCase();
                    break;
                default:
                    result=name;
                    break;
            }
            return result;

         //NamesType.UNDERLINE_LOWER_CASE
        } else if(from==UNDERLINE_LOWER_CASE){
            String result;
            switch (to){
                case CAMEL_LOWER_CASE:
                    StringBuilder clc=new StringBuilder();
                    clc.append(Character.toLowerCase(name.charAt(0)));
                    if(len>1){
                        for (int i=1;i<len;++i){
                            char c=name.charAt(i);
                            if(c==NAMES_SEPARATOR&&(i+1)<len){
                                clc.append(Character.toUpperCase(name.charAt(++i)));
                            }else{
                                clc.append(c);
                            }
                        }
                    }
                    result=clc.toString();
                    break;
                case CAMEL_UPPER_CASE:
                    StringBuilder cuc=new StringBuilder();
                    cuc.append(Character.toUpperCase(name.charAt(0)));
                    if(len>1){
                        for (int i=1;i<len;++i){
                            char c=name.charAt(i);
                            if(c==NAMES_SEPARATOR&&(i+1)<len){
                                cuc.append(Character.toUpperCase(name.charAt(++i)));
                            }else{
                                cuc.append(c);
                            }
                        }
                    }
                    result=cuc.toString();
                    break;
                case UNDERLINE_UPPER_CASE:
                    result=name.toUpperCase();
                    break;
                case ALL_LOWER_CASE:
                    result=name.replaceAll(String.valueOf(NAMES_SEPARATOR),"");
                    result=result.toLowerCase();
                    break;
                case ALL_UPPER_CASE:
                    result=name.replaceAll(String.valueOf(NAMES_SEPARATOR),"");
                    result=result.toUpperCase();
                    break;
                default:
                    result=name;
                    break;
            }
            return result;

         //NamesType.UNDERLINE_UPPER_CASE
        } else if(from==UNDERLINE_UPPER_CASE){
            String result;
            switch (to){
                case CAMEL_LOWER_CASE:
                    StringBuilder clc=new StringBuilder();
                    clc.append(Character.toLowerCase(name.charAt(0)));
                    if(len>1){
                        for (int i=1;i<len;++i){
                            char c=name.charAt(i);
                            if(c>='A'&&c<='Z'){
                                clc.append(Character.toLowerCase(c));
                            }else if(c==NAMES_SEPARATOR&&(i+1)<len){
                                clc.append(Character.toUpperCase(name.charAt(++i)));
                            }else{
                                clc.append(c);
                            }
                        }
                    }
                    result=clc.toString();
                    break;
                case CAMEL_UPPER_CASE:
                    StringBuilder cuc=new StringBuilder();
                    cuc.append(Character.toUpperCase(name.charAt(0)));
                    if(len>1){
                        for (int i=1;i<len;++i){
                            char c=name.charAt(i);
                            if(c>='A'&&c<='Z'){
                                cuc.append(Character.toLowerCase(c));
                            }else if(c==NAMES_SEPARATOR&&(i+1)<len){
                                cuc.append(Character.toUpperCase(name.charAt(++i)));
                            }else{
                                cuc.append(c);
                            }
                        }
                    }
                    result=cuc.toString();
                    break;
                case UNDERLINE_LOWER_CASE:
                    result=name.toLowerCase();
                    break;
                case ALL_LOWER_CASE:
                    result=name.replaceAll(String.valueOf(NAMES_SEPARATOR),"");
                    result=result.toLowerCase();
                    break;
                case ALL_UPPER_CASE:
                    result=name.replaceAll(String.valueOf(NAMES_SEPARATOR),"");
                    result=result.toUpperCase();
                    break;
                default:
                    result=name;
                    break;
            }
            return result;

        //NamesType.CAMEL_UPPER_CASE
        }else if(from==CAMEL_UPPER_CASE){
            String result;
            switch (to){
                case UNDERLINE_LOWER_CASE:
                    StringBuilder ulc=new StringBuilder();
                    ulc.append(name.charAt(0));
                    if(len>1){
                        for (int i=1;i<len;++i){
                            char c=name.charAt(i);
                            if(c>='A'&&c<='Z'){
                                ulc.append(NAMES_SEPARATOR);
                                ulc.append(c);
                            }else{
                                ulc.append(c);
                            }
                        }
                    }
                    result=ulc.toString().toLowerCase();
                    break;
                case UNDERLINE_UPPER_CASE:
                    StringBuilder uuc=new StringBuilder();
                    uuc.append(name.charAt(0));
                    if(len>1){
                        for (int i=1;i<len;++i){
                            char c=name.charAt(i);
                            if(c>='A'&&c<='Z'){
                                uuc.append(NAMES_SEPARATOR);
                                uuc.append(c);
                            }else{
                                uuc.append(c);
                            }
                        }
                    }
                    result=uuc.toString().toUpperCase();
                    break;
                case CAMEL_LOWER_CASE:
                    char firstChar=name.charAt(0);
                    result=Character.toLowerCase(firstChar)+name.substring(1,len-1);
                    break;
                case ALL_LOWER_CASE:
                    result=name.toUpperCase();
                    break;
                case ALL_UPPER_CASE:
                    result=name.toLowerCase();
                    break;
                default:
                    result=name;
                    break;
            }
            return result;

        //NamesType.ALL_LOWER_CASE
        } else if(from==ALL_LOWER_CASE){
            return (to==ALL_UPPER_CASE?name.toUpperCase():name);

        //NamesType.ALL_UPPER_CASE
        } else if(from==ALL_UPPER_CASE){
            return (to==ALL_LOWER_CASE?name.toLowerCase():name);

        //NamesType.UNKNOWN
        }else{
            return name;
        }
    }
}

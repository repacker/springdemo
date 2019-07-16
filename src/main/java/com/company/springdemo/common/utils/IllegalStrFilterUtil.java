package com.company.springdemo.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuys
 * @Description: 非法字符校验工具类
 * @date 2018/7/4
 */
public class IllegalStrFilterUtil {

    //特殊字符
    private static final String REGX = "!|！|@|◎|#|＃|(\\$)|￥|%|％|(\\^)|……|(\\&)|※|(\\*)|×|(\\()|（|(\\))|）|_|——|(\\+)|＋|(\\|)|§ ";

    // 非法xss 字符
    private static String[] SAFE_LESS = {"set-cookie", "<", "%3c", "%3e", ">", "\\"};

    private static final String IS_SQL_INJECTION = "存在SQL注入风险";

    private static final String UNVALIDATED_INPUT = "含有非法字符";

    private static final String XSS_INPUT = "含有非法XSS字符";

    /**
     * 对常见的sql注入攻击进行拦截
     *
     * @param sInput
     * @return true 表示参数不存在SQL注入风险
     * false 表示参数存在SQL注入风险
     */
    public static Boolean isSqlSafe(String sInput) {
        if (sInput == null || sInput.trim().length() == 0) {
            return false;
        }
        sInput = sInput.toUpperCase();

        if (sInput.indexOf("DELETE") >= 0 || sInput.indexOf("ASCII") >= 0 || sInput.indexOf("UPDATE") >= 0 || sInput.indexOf("SELECT") >= 0
                || sInput.indexOf("'") >= 0 || sInput.indexOf("SUBSTR(") >= 0 || sInput.indexOf("COUNT(") >= 0 || sInput.indexOf(" OR ") >= 0
                || sInput.indexOf(" AND ") >= 0 || sInput.indexOf("DROP") >= 0 || sInput.indexOf("EXECUTE") >= 0 || sInput.indexOf("EXEC") >= 0
                || sInput.indexOf("TRUNCATE") >= 0 || sInput.indexOf("INTO") >= 0 || sInput.indexOf("DECLARE") >= 0 || sInput.indexOf("MASTER") >= 0) {
            return false;
        }
        return true;
    }

    /**
     * 对非法字符进行检测
     *
     * @param sInput
     * @return true 表示参数包含非法字符
     * false 表示参数不包含非法字符
     */
    public static Boolean isIllegalStr(String sInput) {

        if (sInput == null || sInput.trim().length() == 0) {
            return false;
        }
        sInput = sInput.trim();
        Pattern compile = Pattern.compile(REGX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compile.matcher(sInput);
        return matcher.find();
    }

    /**
     * Xss校验
     *
     * @param sInput
     * @return true 表示参数不包含非法字符
     * false 表示参数包含非法字符
     */
    public static boolean isXssStr(String sInput) {
        if (StringUtils.isNotBlank(sInput)) {
            for (String s : SAFE_LESS) {
                if (sInput.toLowerCase().contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 参数是否安全
     *
     * @param str
     * @return
     */
    public static String isSafeParam(String str) {
        String prefix = "输入参数包含";
        if (!isSqlSafe(str)) {
            return prefix + IS_SQL_INJECTION;
        }
        if (!isXssStr(str)) {
            return prefix + XSS_INPUT;
        }
        return "";
    }

    /**
     * url是否安全
     *
     * @param str
     * @return
     */
    public static String isSafeUrl(String url) {
        String prefix = "url包含";

        if (!isSqlSafe(url)) {
            return prefix + IS_SQL_INJECTION;
        }

        if (!isXssStr(url)) {
            return prefix + XSS_INPUT;
        }
        return "";
    }

}
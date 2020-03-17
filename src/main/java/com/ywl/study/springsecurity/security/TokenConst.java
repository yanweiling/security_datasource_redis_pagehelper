package com.ywl.study.springsecurity.security;

public class TokenConst {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_MARK = "user:";
    public static final String TOKEN_GNMK = "gnmk:";
    public static final String TOKEN_USERNAME = "TK_USER_NAME";

    // 过期时间是3600秒，既是1个小时 (一天)
    public static final long EXPIRATION = 86400L;//3600L;

    // 选择了记住我之后的过期时间为7天
    public static final long EXPIRATION_REMEMBER = 604800L;
}

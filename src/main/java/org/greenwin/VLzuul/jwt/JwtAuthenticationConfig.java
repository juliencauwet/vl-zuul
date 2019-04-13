package org.greenwin.VLzuul.jwt;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * Config JWT.
 * Only one property 'jwt.security.jwt.secret' is mandatory.
 *
 * @author jwt 2017/10/18
 */
@Getter
@ToString
public class JwtAuthenticationConfig {

    @Value("${jwt.security.jwt.url:/login}")
    private String url;

    @Value("${jwt.security.jwt.header:Authorization}")
    private String header;

    @Value("${jwt.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${shuaicj.security.jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${jwt.security.jwt.secret}")
    private String secret;
}

package com.teamtek.shiro.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
/**
 * jwt 工具类
 * @author Administrator
 *
 */
public class JWTUtil {
	/**
	 * 验证结果  此处只列举 出 token过期异常（fail_expired）
	 * 其他验证失败 为（fail_other）
	 * 常出现的是  fail_expired  pass两种结果  
	 * fail_other由其他异常造成，比如编码错误、加密算法错误等 ，只要程序写法无误，这种异常 基本不会出现
	 * @author syj
	 *@createTime：2018年2月1日 上午8:37:20
	 */
	public enum JwtVerifyResult{
		pass("验证通过"),
		fail_expired("验证失败，token过期"),
		fail_other("验证失败,参数有误或编码错误");
		
		private final String title;
		JwtVerifyResult(String title) {
			this.title = title;
		}
		public String title() {
			return title;
		}

	}
	
	private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);
	
	public static final String ACCESS_TOKEN_CODE = "access_token";
	public static final String PASSWORD_CODE = "password";
    // 过期时间60分钟
	public static final long EXPIRE_TIME = 60*60*1000;

	//签名密钥
    private static final String key = "I'm a dog , what are you ?";
   /**
    * 校验token是否正确
    * @param token
    * @param username
    * @return
    */
    public static JwtVerifyResult verify(String token, String username) {
    	 try {
			Algorithm algorithm = Algorithm.HMAC256(key);
			 JWTVerifier verifier = JWT.require(algorithm)
					 .withIssuer(username)
			         .build();
			 verifier.verify(token);
			 logger.info("*****jwt token验证通过************************");
			 return JwtVerifyResult.pass;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			 logger.warn("*****jwt token验证失败***IllegalArgumentException********{}",e.getMessage());
			return JwtVerifyResult.fail_other;
		} catch (UnsupportedEncodingException e) {
			 logger.warn("*****jwt token验证失败**UnsupportedEncodingException**********{}",e.getMessage());
			 return JwtVerifyResult.fail_other;
		} catch (JWTVerificationException e) {
			 logger.warn("*****jwt token验证失败***JWTVerificationException******{}",e.getMessage());
			 if(e instanceof TokenExpiredException){
				 return JwtVerifyResult.fail_expired;
			 }
			 return JwtVerifyResult.fail_other;
		}
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
    	 DecodedJWT jwt = JWT.decode(token);
         return jwt.getIssuer();
    }
    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名  密码
     */
    public static String getPwd(String token) {
    	  DecodedJWT jwt = JWT.decode(token);
          return jwt.getClaim(PASSWORD_CODE).asString();
	}
    /**
     * 获得token中的信息 SessionCode
     * @param token
     * @return
     */
    public static String getSessionCode(String token) {
    	 DecodedJWT jwt = JWT.decode(token);
         return jwt.getId();
    }

    /**
     * sessionCode加入 jwt 发送到client 
     * 生成签名,60min后过期
     * @param username 用户名
     * @param secret 用户的密码
     * @return 加密的token
     * @throws Exception 
     */
    public static String sign(String username,String sessionCode){
    	return sign(username, sessionCode,null);
    }
    
    /**
     * 用于realm 登陆验证
     * @throws Exception 
     */
    public static String sign(String username,String sessionCode,String password) {
    	
    	try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(key);
            logger.info("*****生成签名的jwt token***********************");
            Builder builder = JWT.create()
            		.withIssuer(username)
            		.withJWTId(sessionCode)
            		.withExpiresAt(date);
            if(StringUtils.isNotBlank(password)){
            	builder.withClaim(PASSWORD_CODE, password);
            }
            return builder.sign(algorithm);
        } catch (Exception e) {
        	return null;
        }
        
    }

	
}

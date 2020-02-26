//package com.spring.cloud.example.apigateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import com.spring.cloud.example.apigateway.constant.Constants;
//import com.spring.cloud.example.apigateway.util.CookieUtil;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
//
//@Component
//public class AuthFilter extends ZuulFilter {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Override
//    public String filterType() {
//        return PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        //  越小优先级越高
//        return PRE_DECORATION_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest httpServletRequest = requestContext.getRequest();
//
//        /**
//         *  /order/create   只能买家访问（cookie里有openid）
//         *  /order/finish   只能卖家访问（cookie里有token，并且对应redis里有值）
//         *  /product/list   都可以访问
//         */
//        if ("/order/order/create".equals(httpServletRequest.getRequestURI())) {
//            Cookie cookie = CookieUtil.get(httpServletRequest, "openid");
//            if (null == cookie || StringUtils.isBlank(cookie.getValue())) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            }
//        }
//        if ("/order/order/finish".equals(httpServletRequest.getRequestURI())) {
//            Cookie cookie = CookieUtil.get(httpServletRequest, "token");
//            if (null == cookie
//                    || StringUtils.isBlank(cookie.getValue())
//                    || StringUtils.isBlank(stringRedisTemplate.opsForValue().get(String.format(Constants.RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            }
//        }
//        return null;
//    }
//}

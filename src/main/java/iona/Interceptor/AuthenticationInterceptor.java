package iona.Interceptor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import iona.cache.EhcacheManager;
import iona.cache.IonaCache;
import iona.modelView.BaseModelView;
import iona.util.MyHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 *  SpringMvc拦截器，
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private IonaCache ionaCache;

    // 拦截controller方法 preHandle会被SpringMvc执行两次，我们需要判断handler类型，防止其执行两次
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HttpRequestHandler){
            return true;
        }
        return authentication(request,response);
    }

    //执行Controller方法之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    //SpringMvc处理完视图渲染，返回请求之前
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean authentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = URLDecoder.decode(request.getHeader("Authorization"),"UTF-8");
        String[] arr = token.split("_");
        String cacheToken = "";
        if(arr.length > 1){
            cacheToken = ionaCache.getCacheValue(URLDecoder.decode(arr[1],"UTF-8") );
        }

        // 手写Response，告诉前端没有登录凭证或者登录凭证不存在或者登录凭证不匹配
        if(StringUtils.isEmpty(token) ||
                StringUtils.isEmpty(cacheToken) ||
                !cacheToken.equals(token)){
            BaseModelView baseModelView = new BaseModelView(MyHttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Content-Type","application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(baseModelView));
            return false;
        }

        return true;
    }
}
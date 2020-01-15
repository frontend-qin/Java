package com.admin.interceptor;

import com.admin.entity.Token;

import com.admin.utils.JwtToken;
import com.admin.utils.Result;
import com.admin.utils.StatusCode;
import com.alibaba.fastjson.JSON;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


public class InterceptorConfig  implements HandlerInterceptor{

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    private JwtToken jwtToken = new JwtToken();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("--------开始进入请求地址拦截------------");
        // 获取请求头里的token
        String token = httpServletRequest.getHeader("token");
        // 如果 请求头里的 token 为空
        // 接着判断token 跟登录时返回的token 是否一样, 判断是否过期
        if(StringUtils.isEmpty(token)){
            // 返回给前端提示语
            PrintWriter printWriter = httpServletResponse.getWriter();
            Result<String> result = new Result<String>(StatusCode.ERROR,"can not find token info from header","");
            // JSON.toJSONString 将对象转化为json
            printWriter.write(JSON.toJSONString(result));
            return false;
        }
        return token.equals(Token.saveToken)? true : false;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("------处理请求完成后--");
    }


}

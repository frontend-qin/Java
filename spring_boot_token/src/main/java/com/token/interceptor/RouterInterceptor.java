package com.token.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.token.utils.ResultUtil;
import com.token.utils.TokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 配置全局自定义的路由请求拦截器: 请求前验证， 请求中...， 请求后
 * 每个请求都会走这里
 */
public class RouterInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置返回值字符编码
        response.setCharacterEncoding("utf-8");
        // 从请求头获取token信息()
        // AccessToken 是前端请求头自定义的字段， 一般后台定义好以后告诉前端
        String token = request.getHeader("AccessToken");
        // 判断
        if(token != null){
            boolean finalToken = TokenUtil.virfityToken(token);
            return finalToken ? true : this.isCanUserToken(response);
        }
        return this.noHeaderToken(response);
//        if(token != null){
//            // 验证token 正确与否 以及是否过期
//            boolean finalToken = TokenUtil.virfityToken(token);
//            if(finalToken){
//                return true;
//            }else{
//                ResultUtil resultUtil = new ResultUtil("token令牌失效");
//                responseMessage(response, response.getWriter(),resultUtil);
//                return false;
//            }
//        }

    }
    /**
     * 返回信息给客户端
     *
     * @param response
     * @param out
     * @param Result
     */
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResultUtil Result) {
        response.setContentType("application/json; charset=utf-8");
        out.print(JSONObject.toJSONString(Result));
        out.flush();
        out.close();
    }

    /**
     * 未在请求头里获取到token
     * @return  ResultUtil 对象
     */
    private boolean noHeaderToken(HttpServletResponse response) throws IOException {
        ResultUtil<String> resultUtil = new ResultUtil<String>("请求头未携带token验证","");
        responseMessage(response, response.getWriter(),resultUtil);
        return false;
    }

    /**
     * 验证token失效
     * @return false
     */
    private boolean isCanUserToken(HttpServletResponse response) throws IOException {
        ResultUtil<String> resultUtil = new ResultUtil<String>("token令牌失效","");
        responseMessage(response, response.getWriter(),resultUtil);
        return false;
    }
}

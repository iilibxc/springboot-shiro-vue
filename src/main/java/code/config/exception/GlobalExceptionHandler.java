package code.config.exception;

import code.common.ResponseCode;
import code.common.ServerResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 统一异常拦截
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @ExceptionHandler(value = Exception.class)
    public ServerResponse defaultErrorHandler(HttpServletRequest req, Exception e) {
        String errorPosition = "";
        //如果错误堆栈信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        JSONObject errorObject = new JSONObject();
        errorObject.put("errorLocation", e.toString() + "    错误位置:" + errorPosition);
        logger.error("异常", e);
        return ServerResponse.error(ResponseCode.HANDLING_EXCEPTION, errorObject);
    }

    /**
     * GET/POST请求方法错误的拦截器
     * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
     * 所以定义了这个拦截器
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ServerResponse httpRequestMethodHandler() {
        return ServerResponse.error(ResponseCode.METHOD_NOT_SUPPORTED);
    }

    /**
     * 本系统自定义错误的拦截器
     * 拦截到此错误之后,就返回这个类里面的json给前端
     * 常见使用场景是参数校验失败,抛出此错,返回错误信息给前端
     */
    @ExceptionHandler(CommonException.class)
    public ServerResponse commonExceptionHandler(CommonException commonException) {
        return commonException.getServerResponse();
    }

    /**
     * 权限不足报错拦截
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ServerResponse unauthorizedExceptionHandler() {
        return ServerResponse.error(ResponseCode.INSUFFICIENT_AUTHORITY);
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public ServerResponse unauthenticatedException() {
        return ServerResponse.error(ResponseCode.LOGIN_EXPIRED);
    }
}

package code.config.exception;

import code.common.ResponseCode;
import code.common.ServerResponse;

/**
 * @description: 本系统使用的自定义错误类
 * 比如在校验参数时,如果不符合要求,可以抛出此错误类
 * 拦截器可以统一拦截此错误,将其中json返回给前端
 */
public class CommonException extends RuntimeException {
    private ServerResponse serverResponse;

    /**
     * 调用时可以在任何代码处直接throws这个Exception,
     * 都会统一被拦截,并封装好json返回给前台
     *
     * @param responseCode 以错误的ErrorEnum做参数
     */
    public CommonException(ResponseCode responseCode) {
        this.serverResponse = ServerResponse.error(responseCode);
    }

    public CommonException(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }
}

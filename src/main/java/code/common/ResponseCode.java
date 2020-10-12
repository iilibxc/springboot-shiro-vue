package code.common;


/**
 * 响应状态码
 */
public enum ResponseCode {

    // 系统模块
    SUCCESS("100", "操作成功"),
    ERROR("1", "操作失败"),
    PATH_NOT_EXIST("404", "请求路径不存在"),
    HANDLING_EXCEPTION("400", "请求处理异常，请稍后再试"),
    SERVER_ERROR("500", "服务器异常"),
    AUTHENTICATION_EXCEPTION("5000","身份验证异常"),
    LOGIN_EXPIRED("5001", "登陆已过期,请重新登陆"),
    MISSING_REQUIRED_PARAMETERS("5002", "缺少必填参数"),
    ACCOUNT_ALREADY_EXISTS("5003", "账户已存在"),
    ROLE_STILL_IN_USER("5004", "角色删除失败,尚有用户属于此角色"),
    INSUFFICIENT_AUTHORITY("5005", "用户无权限"),
    USER_NOT_EXISTS("50006", "用户不存在"),
    METHOD_NOT_SUPPORTED("5007", "请求方式有误,请检查 GET/POST"),
    // 通用模块 1xxxx
    ILLEGAL_ARGUMENT("10000", "参数不合法"),
    REPETITIVE_OPERATION("10001", "请勿重复操作"),
    ACCESS_LIMIT("10002", "请求太频繁, 请稍后再试"),
    MAIL_SEND_SUCCESS("10003", "邮件发送成功"),

    // 用户模块 2xxxx
    NEED_LOGIN("20001", "登录失效"),
    USERNAME_OR_PASSWORD_EMPTY("20002", "用户名或密码不能为空"),
    USERNAME_OR_PASSWORD_WRONG("20003", "用户名或密码错误"),

    WRONG_PASSWORD("20005", "密码错误"),

    // 订单模块 4xxxx

    ;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

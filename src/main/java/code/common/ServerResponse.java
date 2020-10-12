package code.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.PackagePrivate;

import java.io.Serializable;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class ServerResponse implements Serializable {

    private static final long serialVersionUID = 7498483649536881777L;
    // 用于属性上、set/get方法上，该属性序列化后可重命名。
    @JsonProperty(value = "code")
    private String code;

    private String msg;

    private Object data;

    private ServerResponse() {

    }

    private ServerResponse(String code, String msg, Object data) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    @JsonIgnore
    //在json序列化时将pojo中的一些属性忽略掉，标记在属性或者方法上，返回的json数据即不包含该属性。
    public boolean isSuccess() {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public static ServerResponse success() {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), null);
    }

    public static ServerResponse success(String msg, Object data) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static ServerResponse success(ResponseCode responseCode) {
        return new ServerResponse(responseCode.getCode(), responseCode.getMsg(), null);
    }

    public static ServerResponse success(Object data) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), data);
    }

    public static ServerResponse success(ResponseCode responseCode, Object data) {
        return new ServerResponse(responseCode.getCode(), responseCode.getMsg(), data);
    }

    public static ServerResponse error() {
        return new ServerResponse(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);
    }

    public static ServerResponse error(String msg, Object data) {
        return new ServerResponse(ResponseCode.ERROR.getCode(), msg, data);
    }

    public static ServerResponse error(ResponseCode responseCode) {
        return new ServerResponse(responseCode.getCode(), responseCode.getMsg(), null);
    }

    public static ServerResponse error(Object data) {
        return new ServerResponse(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), data);
    }

    public static ServerResponse error(ResponseCode responseCode, Object data) {
        return new ServerResponse(responseCode.getCode(), responseCode.getMsg(), data);
    }

}

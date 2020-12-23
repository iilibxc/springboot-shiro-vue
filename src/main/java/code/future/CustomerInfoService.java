package code.future;
public class CustomerInfoService implements RemoteLoader {
    public String load() {
        this.delay();
        return "基本信息";
    }
}

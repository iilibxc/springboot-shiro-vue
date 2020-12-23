package code.future;

public class LearnRecordService implements RemoteLoader {
    public String load() {
        this.delay();
        return "学习信息";
    }
}
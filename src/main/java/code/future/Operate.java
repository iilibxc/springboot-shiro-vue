package code.future;

public class Operate {
    public static class WatchRecordService implements RemoteLoader {
        @Override
        public String load() {
            this.delay();
            return "观看记录";
        }
    }

    public static class OrderService implements RemoteLoader {
        @Override
        public String load() {
            this.delay();
            return "订单信息";
        }
    }

    public static class LabelService implements RemoteLoader {
        @Override
        public String load() {
            this.delay();
            return "标签信息";
        }
    }

}

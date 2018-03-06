package app.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by mjn_ziyu on 2017/8/24.
 */

public class Machine {

    private List<DeviceListBean> device_list;
    /**
     * hasnext : 0
     * address : {"chat_url":"101.132.132.109:8282","control_url":"101.132.98.228:8282"}
     */

    private int hasnext;
    private int free_num;

    public List<DeviceListBean> getDevice_list() {
        return device_list;
    }
    public void setDevice_list(List<DeviceListBean> device_list) {
        this.device_list = device_list;
    }

    public int getHasnext() {
        return hasnext;
    }

    public void setHasnext(int hasnext) {
        this.hasnext = hasnext;
    }

    public int getFree_num() {
        return free_num;
    }

    public void setFree_num(int free_num) {
        this.free_num = free_num;
    }

    public static class DeviceListBean implements Parcelable {
        /**
         * device_id : 10002
         * toy_name : 长颈鹿布朗熊
         * front_cover : csbz1503733090t5132631.png
         * brilliant : 10
         * state : 3
         */

        private String device_id;
        private String toy_name;
        private String front_cover;
        private String brilliant;
        private int state;
        private int infinite;
        private String l_brilliant;
        private String characteristic;
        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getToy_name() {
            return toy_name;
        }

        public void setToy_name(String toy_name) {
            this.toy_name = toy_name;
        }

        public String getFront_cover() {
            return front_cover;
        }

        public void setFront_cover(String front_cover) {
            this.front_cover = front_cover;
        }

        public String getBrilliant() {
            return brilliant;
        }

        public void setBrilliant(String brilliant) {
            this.brilliant = brilliant;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getInfinite() {
            return infinite;
        }

        public void setInfinite(int infinite) {
            this.infinite = infinite;
        }

        public String getL_brilliant() {
            return l_brilliant;
        }

        public void setL_brilliant(String l_brilliant) {
            this.l_brilliant = l_brilliant;
        }

        public String getCharacteristic() {
            return characteristic;
        }

        public void setCharacteristic(String characteristic) {
            this.characteristic = characteristic;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.device_id);
            dest.writeString(this.toy_name);
            dest.writeString(this.front_cover);
            dest.writeString(this.brilliant);
            dest.writeInt(this.state);
            dest.writeInt(this.infinite);
            dest.writeString(this.l_brilliant);
            dest.writeString(this.characteristic);
        }

        public DeviceListBean() {
        }

        protected DeviceListBean(Parcel in) {
            this.device_id = in.readString();
            this.toy_name = in.readString();
            this.front_cover = in.readString();
            this.brilliant = in.readString();
            this.state = in.readInt();
            this.infinite = in.readInt();
            this.l_brilliant = in.readString();
            this.characteristic = in.readString();
        }

        public static final Creator<DeviceListBean> CREATOR = new Creator<DeviceListBean>() {
            @Override
            public DeviceListBean createFromParcel(Parcel source) {
                return new DeviceListBean(source);
            }

            @Override
            public DeviceListBean[] newArray(int size) {
                return new DeviceListBean[size];
            }
        };
    }
}

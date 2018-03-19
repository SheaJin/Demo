package app.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjn_ziyu on 2017/8/21.
 */

public class PlayHistory implements Parcelable {

    /**
     * hasnext : 1
     * log_list : [{"c_day":"0310","create_time":"1520665386","array_child":[{"log_id":"5718","grab_id":"665386C4C7WE","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/fe4312027447398154968437875/f0.mp4","create_time":"1520665386","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5717","grab_id":"664404934H6J","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/f4fc98147447398154968040295/f0.mp4","create_time":"1520664404","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5716","grab_id":"6643543NRTT4","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/f9f219387447398154968285612/f0.mp4","create_time":"1520664354","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5715","grab_id":"664140Z4U0TC","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/f9f1917a7447398154968284741/f0.mp4","create_time":"1520664140","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5714","grab_id":"664120MF6CE6","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/fbda0db07447398154968324151/f0.mp4","create_time":"1520664120","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5713","grab_id":"663538ZBDVR2","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/bb0a92877447398154967898744/f0.mp4","create_time":"1520663538","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5712","grab_id":"663309RV0FPV","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/bd04a7ff7447398154967947480/f0.mp4","create_time":"1520663309","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5711","grab_id":"662972U3URD7","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"","create_time":"1520662972","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5710","grab_id":"662907G1ZCW2","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"","create_time":"1520662907","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5709","grab_id":"662888FUP6VF","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"","create_time":"1520662888","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5708","grab_id":"6624957H1QH8","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"","create_time":"1520662495","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5707","grab_id":"662183BHKBJE","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/bb0751457447398154967892982/f0.mp4","create_time":"1520662183","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5706","grab_id":"662157070MW9","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"","create_time":"1520662157","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"}]}]
     */

    private int hasnext;
    private List<LogListBean> log_list;

    public int getHasnext() {
        return hasnext;
    }

    public void setHasnext(int hasnext) {
        this.hasnext = hasnext;
    }

    public List<LogListBean> getLog_list() {
        return log_list;
    }

    public void setLog_list(List<LogListBean> log_list) {
        this.log_list = log_list;
    }

    public static class LogListBean {
        /**
         * c_day : 0310
         * create_time : 1520665386
         * array_child : [{"log_id":"5718","grab_id":"665386C4C7WE","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/fe4312027447398154968437875/f0.mp4","create_time":"1520665386","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5717","grab_id":"664404934H6J","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/f4fc98147447398154968040295/f0.mp4","create_time":"1520664404","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5716","grab_id":"6643543NRTT4","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/f9f219387447398154968285612/f0.mp4","create_time":"1520664354","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5715","grab_id":"664140Z4U0TC","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/f9f1917a7447398154968284741/f0.mp4","create_time":"1520664140","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5714","grab_id":"664120MF6CE6","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/fbda0db07447398154968324151/f0.mp4","create_time":"1520664120","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5713","grab_id":"663538ZBDVR2","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/bb0a92877447398154967898744/f0.mp4","create_time":"1520663538","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5712","grab_id":"663309RV0FPV","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/bd04a7ff7447398154967947480/f0.mp4","create_time":"1520663309","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5711","grab_id":"662972U3URD7","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"","create_time":"1520662972","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5710","grab_id":"662907G1ZCW2","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"","create_time":"1520662907","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5709","grab_id":"662888FUP6VF","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"","create_time":"1520662888","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5708","grab_id":"6624957H1QH8","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"","create_time":"1520662495","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5707","grab_id":"662183BHKBJE","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/bb0751457447398154967892982/f0.mp4","create_time":"1520662183","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"},{"log_id":"5706","grab_id":"662157070MW9","toy_id":"523","outcome":"2","at_last":"2","video":"","video_two":"","create_time":"1520662157","v_switch":"1","toy_name":"helloKitty吊坠","front_cover":"csbz1516674738bft9.jpeg"}]
         */

        private String c_day;
        private String create_time;
        private List<ArrayChildBean> array_child;

        public String getC_day() {
            return c_day;
        }

        public void setC_day(String c_day) {
            this.c_day = c_day;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public List<ArrayChildBean> getArray_child() {
            return array_child;
        }

        public void setArray_child(List<ArrayChildBean> array_child) {
            this.array_child = array_child;
        }

        public static class ArrayChildBean implements Parcelable {
            /**
             * log_id : 5718
             * grab_id : 665386C4C7WE
             * toy_id : 523
             * outcome : 2
             * at_last : 2
             * video :
             * video_two : http://1254350062.vod2.myqcloud.com/78bb417bvodgzp1254350062/fe4312027447398154968437875/f0.mp4
             * create_time : 1520665386
             * v_switch : 1
             * toy_name : helloKitty吊坠
             * front_cover : csbz1516674738bft9.jpeg
             */

            private String log_id;
            private String grab_id;
            private String toy_id;
            private int outcome;
            private int at_last;
            private String video;
            private String video_two;
            private long create_time;
            private long surplus;
            private String v_switch;
            private String toy_name;
            private String front_cover;

            public String getLog_id() {
                return log_id;
            }

            public void setLog_id(String log_id) {
                this.log_id = log_id;
            }

            public String getGrab_id() {
                return grab_id;
            }

            public void setGrab_id(String grab_id) {
                this.grab_id = grab_id;
            }

            public String getToy_id() {
                return toy_id;
            }

            public void setToy_id(String toy_id) {
                this.toy_id = toy_id;
            }

            public int getOutcome() {
                return outcome;
            }

            public void setOutcome(int outcome) {
                this.outcome = outcome;
            }

            public int getAt_last() {
                return at_last;
            }

            public void setAt_last(int at_last) {
                this.at_last = at_last;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getVideo_two() {
                return video_two;
            }

            public void setVideo_two(String video_two) {
                this.video_two = video_two;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public String getV_switch() {
                return v_switch;
            }

            public void setV_switch(String v_switch) {
                this.v_switch = v_switch;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.log_id);
                dest.writeString(this.grab_id);
                dest.writeString(this.toy_id);
                dest.writeInt(this.outcome);
                dest.writeInt(this.at_last);
                dest.writeString(this.video);
                dest.writeString(this.video_two);
                dest.writeLong(this.create_time);
                dest.writeLong(this.surplus);
                dest.writeString(this.v_switch);
                dest.writeString(this.toy_name);
                dest.writeString(this.front_cover);
            }

            public ArrayChildBean() {
            }

            protected ArrayChildBean(Parcel in) {
                this.log_id = in.readString();
                this.grab_id = in.readString();
                this.toy_id = in.readString();
                this.outcome = in.readInt();
                this.at_last = in.readInt();
                this.video = in.readString();
                this.video_two = in.readString();
                this.create_time = in.readLong();
                this.surplus = in.readLong();
                this.v_switch = in.readString();
                this.toy_name = in.readString();
                this.front_cover = in.readString();
            }

            public static final Creator<ArrayChildBean> CREATOR = new Creator<ArrayChildBean>() {
                @Override
                public ArrayChildBean createFromParcel(Parcel source) {
                    return new ArrayChildBean(source);
                }

                @Override
                public ArrayChildBean[] newArray(int size) {
                    return new ArrayChildBean[size];
                }
            };

            public long getSurplus() {
                return surplus;
            }

            public void setSurplus(long surplus) {
                this.surplus = surplus;
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.hasnext);
        dest.writeList(this.log_list);
    }

    public PlayHistory() {
    }

    protected PlayHistory(Parcel in) {
        this.hasnext = in.readInt();
        this.log_list = new ArrayList<LogListBean>();
        in.readList(this.log_list, LogListBean.class.getClassLoader());
    }

    public static final Creator<PlayHistory> CREATOR = new Creator<PlayHistory>() {
        @Override
        public PlayHistory createFromParcel(Parcel source) {
            return new PlayHistory(source);
        }

        @Override
        public PlayHistory[] newArray(int size) {
            return new PlayHistory[size];
        }
    };
}

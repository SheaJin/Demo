package app.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjn_ziyu on 2017/12/8.
 */

public class FastEntrance implements Parcelable {

    private List<FastTrackBean> fast_track;
    private List<BannerBean> banner;

    public List<FastTrackBean> getFast_track() {
        return fast_track;
    }

    public void setFast_track(List<FastTrackBean> fast_track) {
        this.fast_track = fast_track;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class FastTrackBean implements Parcelable {
        /**
         * type : 1
         * title : 签到
         * operate_id : 0
         * url :
         * img : csbz148887124420170306154905.png
         */

        private int type;
        private String title;
        private String operate_id;
        private String url;
        private String img;
        private int share;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOperate_id() {
            return operate_id;
        }

        public void setOperate_id(String operate_id) {
            this.operate_id = operate_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int isShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public FastTrackBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.type);
            dest.writeString(this.title);
            dest.writeString(this.operate_id);
            dest.writeString(this.url);
            dest.writeString(this.img);
            dest.writeInt(this.share);
        }

        protected FastTrackBean(Parcel in) {
            this.type = in.readInt();
            this.title = in.readString();
            this.operate_id = in.readString();
            this.url = in.readString();
            this.img = in.readString();
            this.share = in.readInt();
        }

        public static final Creator<FastTrackBean> CREATOR = new Creator<FastTrackBean>() {
            @Override
            public FastTrackBean createFromParcel(Parcel source) {
                return new FastTrackBean(source);
            }

            @Override
            public FastTrackBean[] newArray(int size) {
                return new FastTrackBean[size];
            }
        };
    }

    public static class BannerBean {
        /**
         * number : 40
         * url :
         * picture : csbz1512560287csbz1508483800diamond_icon_@3x.png
         * classification : app
         */

        private String number;
        private String url;
        private String picture;
        private String classification;
        private int share;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }

        public int isShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }
    }

    public FastEntrance() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.fast_track);
        dest.writeList(this.banner);
    }

    protected FastEntrance(Parcel in) {
        this.fast_track = in.createTypedArrayList(FastTrackBean.CREATOR);
        this.banner = new ArrayList<BannerBean>();
        in.readList(this.banner, BannerBean.class.getClassLoader());
    }

    public static final Creator<FastEntrance> CREATOR = new Creator<FastEntrance>() {
        @Override
        public FastEntrance createFromParcel(Parcel source) {
            return new FastEntrance(source);
        }

        @Override
        public FastEntrance[] newArray(int size) {
            return new FastEntrance[size];
        }
    };
}

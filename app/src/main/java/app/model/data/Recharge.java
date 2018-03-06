package app.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjn_ziyu on 2017/8/26.
 */

public class Recharge implements Parcelable {

    private List<GoodsListBean> goods_list;
    private List<BannerBean> banner;

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class GoodsListBean implements Parcelable {
        /**
         * type : 1
         * item : [{"g_id":"71","goods_detailed":"月卡每天领取200钻","name":"超值月卡","apiece":"30","info":"我是月卡规","picture":"csbz1512556180csbz1508483800diamond_icon_@3x.png","give":"200","give_info":"购买后立即获得200钻","goods_type":"30","brilliant":"300","cash":"0.01","activity_end":"1544092180","a_info":"","a_type":"0","a_quantity":"0","draw":0},{"g_id":"73","goods_detailed":"超值周卡2.0","name":"超值周卡","apiece":"2","info":"购买月卡后账户直接增加100钻，从购买当日起可领取10钻，持续时间为7天","picture":"csbz1512632650appicon.png","give":"2","give_info":"超值周卡2.0","goods_type":"20","brilliant":"3","cash":"10.00","activity_end":"1516261444","a_info":"","a_type":"0","a_quantity":"0","draw":0}]
         */

        private int type;
        private List<ItemBean> item;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean implements Parcelable {
            /**
             * g_id : 71
             * goods_detailed : 月卡每天领取200钻
             * name : 超值月卡
             * apiece : 30
             * info : 我是月卡规
             * picture : csbz1512556180csbz1508483800diamond_icon_@3x.png
             * give : 200
             * give_info : 购买后立即获得200钻
             * goods_type : 30
             * brilliant : 300
             * cash : 0.01
             * activity_end : 1544092180
             * a_info :
             * a_type : 0
             * a_quantity : 0
             * draw : 0
             *  * end_time : 30
             * time_info : 剩余时间 30 天
             *
             */

            private String g_id;
            private String goods_detailed;
            private String name;
            private int apiece;
            private String info;
            private String picture;
            private int give;
            private String give_info;
            private int goods_type;
            private double brilliant;
            private String cash;
            private String activity_end;
            private String a_info;
            private int a_type;
            private String a_quantity;
            private int draw;
            private int end_time;
            private int color;
            private int type;
            private int visible;
            private int surplus;
            private String time_info;
            private String img;
            private String title;
            private boolean isLast;
            public String getG_id() {
                return g_id;
            }

            public void setG_id(String g_id) {
                this.g_id = g_id;
            }

            public String getGoods_detailed() {
                return goods_detailed;
            }

            public void setGoods_detailed(String goods_detailed) {
                this.goods_detailed = goods_detailed;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getApiece() {
                return apiece;
            }

            public void setApiece(int apiece) {
                this.apiece = apiece;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getGive() {
                return give;
            }

            public void setGive(int give) {
                this.give = give;
            }

            public String getGive_info() {
                return give_info;
            }

            public void setGive_info(String give_info) {
                this.give_info = give_info;
            }

            public int getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(int goods_type) {
                this.goods_type = goods_type;
            }

            public double getBrilliant() {
                return brilliant;
            }

            public void setBrilliant(double brilliant) {
                this.brilliant = brilliant;
            }

            public String getCash() {
                return cash;
            }

            public void setCash(String cash) {
                this.cash = cash;
            }

            public String getActivity_end() {
                return activity_end;
            }

            public void setActivity_end(String activity_end) {
                this.activity_end = activity_end;
            }

            public String getA_info() {
                return a_info;
            }

            public void setA_info(String a_info) {
                this.a_info = a_info;
            }

            public int getA_type() {
                return a_type;
            }

            public void setA_type(int a_type) {
                this.a_type = a_type;
            }

            public String getA_quantity() {
                return a_quantity;
            }

            public void setA_quantity(String a_quantity) {
                this.a_quantity = a_quantity;
            }

            public int getDraw() {
                return draw;
            }

            public void setDraw(int draw) {
                this.draw = draw;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public String getTime_info() {
                return time_info;
            }

            public void setTime_info(String time_info) {
                this.time_info = time_info;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getColor() {
                return color;
            }

            public void setColor(int color) {
                this.color = color;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public boolean isLast() {
                return isLast;
            }

            public void setLast(boolean last) {
                isLast = last;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.g_id);
                dest.writeString(this.goods_detailed);
                dest.writeString(this.name);
                dest.writeInt(this.apiece);
                dest.writeString(this.info);
                dest.writeString(this.picture);
                dest.writeInt(this.give);
                dest.writeString(this.give_info);
                dest.writeInt(this.goods_type);
                dest.writeDouble(this.brilliant);
                dest.writeString(this.cash);
                dest.writeString(this.activity_end);
                dest.writeString(this.a_info);
                dest.writeInt(this.a_type);
                dest.writeString(this.a_quantity);
                dest.writeInt(this.draw);
                dest.writeInt(this.end_time);
                dest.writeInt(this.color);
                dest.writeInt(this.type);
                dest.writeInt(this.visible);
                dest.writeInt(this.surplus);
                dest.writeString(this.time_info);
                dest.writeString(this.img);
                dest.writeString(this.title);
                dest.writeByte(this.isLast ? (byte) 1 : (byte) 0);
            }

            public ItemBean() {
            }

            protected ItemBean(Parcel in) {
                this.g_id = in.readString();
                this.goods_detailed = in.readString();
                this.name = in.readString();
                this.apiece = in.readInt();
                this.info = in.readString();
                this.picture = in.readString();
                this.give = in.readInt();
                this.give_info = in.readString();
                this.goods_type = in.readInt();
                this.brilliant = in.readDouble();
                this.cash = in.readString();
                this.activity_end = in.readString();
                this.a_info = in.readString();
                this.a_type = in.readInt();
                this.a_quantity = in.readString();
                this.draw = in.readInt();
                this.end_time = in.readInt();
                this.color = in.readInt();
                this.type = in.readInt();
                this.visible = in.readInt();
                this.surplus = in.readInt();
                this.time_info = in.readString();
                this.img = in.readString();
                this.title = in.readString();
                this.isLast = in.readByte() != 0;
            }

            public static final Creator<ItemBean> CREATOR = new Creator<ItemBean>() {
                @Override
                public ItemBean createFromParcel(Parcel source) {
                    return new ItemBean(source);
                }

                @Override
                public ItemBean[] newArray(int size) {
                    return new ItemBean[size];
                }
            };

            public int getSurplus() {
                return surplus;
            }

            public void setSurplus(int surplus) {
                this.surplus = surplus;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.type);
            dest.writeList(this.item);
        }

        public GoodsListBean() {
        }

        protected GoodsListBean(Parcel in) {
            this.type = in.readInt();
            this.item = new ArrayList<ItemBean>();
            in.readList(this.item, ItemBean.class.getClassLoader());
        }

        public static final Creator<GoodsListBean> CREATOR = new Creator<GoodsListBean>() {
            @Override
            public GoodsListBean createFromParcel(Parcel source) {
                return new GoodsListBean(source);
            }

            @Override
            public GoodsListBean[] newArray(int size) {
                return new GoodsListBean[size];
            }
        };
    }

    public static class BannerBean {
        /**
         * number : 42
         * url :
         * picture : csbz1512639167cinque-terre-340348_1280.jpg
         * classification : app
         */

        private String number;
        private String url;
        private String picture;
        private String classification;

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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.goods_list);
        dest.writeList(this.banner);
    }

    public Recharge() {
    }

    protected Recharge(Parcel in) {
        this.goods_list = new ArrayList<GoodsListBean>();
        in.readList(this.goods_list, GoodsListBean.class.getClassLoader());
        this.banner = new ArrayList<BannerBean>();
        in.readList(this.banner, BannerBean.class.getClassLoader());
    }

    public static final Creator<Recharge> CREATOR = new Creator<Recharge>() {
        @Override
        public Recharge createFromParcel(Parcel source) {
            return new Recharge(source);
        }

        @Override
        public Recharge[] newArray(int size) {
            return new Recharge[size];
        }
    };
}

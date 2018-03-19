package app.model.data;

import java.util.List;

/**
 * Created by tianzhen on 2017/10/24.
 */

public class IPaddress {

    /**
     * control_url : ws://139.196.84.33:8282
     * advertisement : [{"number":"58","url":"http://cs.piggi1.cn/","share":"1","picture":"csbz1516780162Chrysanthemum.jpg","classification":"app"}]
     * is_sign :1
     */

    private String control_url;
    private List<AdvertisementBean> advertisement;
    private int is_sign;

    public String getControl_url() {
        return control_url;
    }

    public void setControl_url(String control_url) {
        this.control_url = control_url;
    }

    public List<AdvertisementBean> getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(List<AdvertisementBean> advertisement) {
        this.advertisement = advertisement;
    }

    public int getIs_sign() {
        return is_sign;
    }

    public void setIs_sign(int is_sign) {
        this.is_sign = is_sign;
    }

    public static class AdvertisementBean {
        /**
         * number : 58
         * url : http://cs.piggi1.cn/
         * share : 1
         * picture : csbz1516780162Chrysanthemum.jpg
         * classification : app
         */

        private String number;
        private String url;
        private int share;
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

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
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
}

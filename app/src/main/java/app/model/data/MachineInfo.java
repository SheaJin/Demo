package app.model.data;

import java.util.List;

/**
 * Created by mjn_ziyu on 2017/8/30.
 */

public class MachineInfo {

    /**
     * device_id : 323230004
     * toy_id : 1
     * machine_type : 1
     * front_live : http://20515.liveplay.myqcloud.com/live/20515_f181231_LT004.flv
     * side_live : http://20515.liveplay.myqcloud.com/live/20515_r181231_LT004.flv
     * front_live_two : rtmp://20515.liveplay.myqcloud.com/live/20515_f181231_LT004
     * side_live_two : rtmp://20515.liveplay.myqcloud.com/live/20515_r181231_LT004
     * front_live_three : x
     * side_live_three : x
     * pattern : 0
     * front_num : 1
     * side_num : 3
     * chatroom : 39749102403585
     * toy_name : 测试商品
     * suggest : 测试
     * caption : 测试
     * detail_img : ["cs15170333421Jwl.jpeg"]
     * brilliant : 10
     * employ_info : {"user_id":100,"img":"hread.png"}
     * people_number : 50
     * characteristic : 0
     * l_brilliant : 30
     * infinite : 0
     * u_brilliant : 100
     * u_gold : 30
     * state : 1
     * hx_room : [{"room_id":"39749102403585"}]
     */

    private String device_id;
    private String toy_id;
    private String machine_type;
    private String front_live;
    private String side_live;
    private String front_live_two;
    private String side_live_two;
    private String front_live_three;
    private String side_live_three;
    private String pattern;
    private String front_num;
    private String side_num;
    private String chatroom;
    private String toy_name;
    private String suggest;
    private String caption;
    private String brilliant;
    private EmployInfoBean employ_info;
    private int people_number;
    private String characteristic;
    private String l_brilliant;
    private int infinite;
    private String u_brilliant;
    private String u_gold;
    private int state;
    private List<String> detail_img;
    private List<HxRoomBean> hx_room;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getToy_id() {
        return toy_id;
    }

    public void setToy_id(String toy_id) {
        this.toy_id = toy_id;
    }

    public String getMachine_type() {
        return machine_type;
    }

    public void setMachine_type(String machine_type) {
        this.machine_type = machine_type;
    }

    public String getFront_live() {
        return front_live;
    }

    public void setFront_live(String front_live) {
        this.front_live = front_live;
    }

    public String getSide_live() {
        return side_live;
    }

    public void setSide_live(String side_live) {
        this.side_live = side_live;
    }

    public String getFront_live_two() {
        return front_live_two;
    }

    public void setFront_live_two(String front_live_two) {
        this.front_live_two = front_live_two;
    }

    public String getSide_live_two() {
        return side_live_two;
    }

    public void setSide_live_two(String side_live_two) {
        this.side_live_two = side_live_two;
    }

    public String getFront_live_three() {
        return front_live_three;
    }

    public void setFront_live_three(String front_live_three) {
        this.front_live_three = front_live_three;
    }

    public String getSide_live_three() {
        return side_live_three;
    }

    public void setSide_live_three(String side_live_three) {
        this.side_live_three = side_live_three;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getFront_num() {
        return front_num;
    }

    public void setFront_num(String front_num) {
        this.front_num = front_num;
    }

    public String getSide_num() {
        return side_num;
    }

    public void setSide_num(String side_num) {
        this.side_num = side_num;
    }

    public String getChatroom() {
        return chatroom;
    }

    public void setChatroom(String chatroom) {
        this.chatroom = chatroom;
    }

    public String getToy_name() {
        return toy_name;
    }

    public void setToy_name(String toy_name) {
        this.toy_name = toy_name;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getBrilliant() {
        return brilliant;
    }

    public void setBrilliant(String brilliant) {
        this.brilliant = brilliant;
    }

    public EmployInfoBean getEmploy_info() {
        return employ_info;
    }

    public void setEmploy_info(EmployInfoBean employ_info) {
        this.employ_info = employ_info;
    }

    public int getPeople_number() {
        return people_number;
    }

    public void setPeople_number(int people_number) {
        this.people_number = people_number;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getL_brilliant() {
        return l_brilliant;
    }

    public void setL_brilliant(String l_brilliant) {
        this.l_brilliant = l_brilliant;
    }

    public int getInfinite() {
        return infinite;
    }

    public void setInfinite(int infinite) {
        this.infinite = infinite;
    }

    public String getU_brilliant() {
        return u_brilliant;
    }

    public void setU_brilliant(String u_brilliant) {
        this.u_brilliant = u_brilliant;
    }

    public String getU_gold() {
        return u_gold;
    }

    public void setU_gold(String u_gold) {
        this.u_gold = u_gold;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<String> getDetail_img() {
        return detail_img;
    }

    public void setDetail_img(List<String> detail_img) {
        this.detail_img = detail_img;
    }

    public List<HxRoomBean> getHx_room() {
        return hx_room;
    }

    public void setHx_room(List<HxRoomBean> hx_room) {
        this.hx_room = hx_room;
    }

    public static class EmployInfoBean {
        /**
         * user_id : 100
         * img : hread.png
         */

        private String user_id;
        private String img;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class HxRoomBean {
        /**
         * room_id : 39749102403585
         */

        private String room_id;

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }
    }
}

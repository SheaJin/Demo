package app.model.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mjn_ziyu on 2017/8/25.
 */

public class DollMachineSocket implements Serializable {

    private int data_type;
    private String machine;
    private String client_id;
    private String user_token;
    private int room_type;
    private IntoRoomBean into_room;
    private CatchStatus catch_status;
    private DollMachineStatus doll_machine_status;
    private int machine_status;
    private int game_status;
    private String user_id;
    private String img;
    private String log_id;
    private int login_info;
    private MessageInfoBean message_info;
    private int infinite;
    private String brilliant;

    private String l_brilliant;
    private String characteristic;
    private int shopping_num;


    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public int getRoom_type() {
        return room_type;
    }

    public void setRoom_type(int room_type) {
        this.room_type = room_type;
    }

    public int getData_type() {
        return data_type;
    }

    public void setData_type(int data_type) {
        this.data_type = data_type;
    }

    public int getMachine_status() {
        return machine_status;
    }

    public void setMachine_status(int machine_status) {
        this.machine_status = machine_status;
    }

    public int getGame_status() {
        return game_status;
    }

    public void setGame_status(int game_status) {
        this.game_status = game_status;
    }

    public IntoRoomBean getInto_room() {
        return into_room;
    }

    public void setInto_room(IntoRoomBean into_room) {
        this.into_room = into_room;
    }

    public DollMachineStatus getDoll_machine_status() {
        return doll_machine_status;
    }

    public void setDoll_machine_status(DollMachineStatus doll_machine_status) {
        this.doll_machine_status = doll_machine_status;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public CatchStatus getCatch_status() {
        return catch_status;
    }

    public void setCatch_status(CatchStatus catch_status) {
        this.catch_status = catch_status;
    }

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

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public int getLogin_info() {
        return login_info;
    }

    public void setLogin_info(int login_info) {
        this.login_info = login_info;
    }

    public MessageInfoBean getMessage_info() {
        return message_info;
    }

    public void setMessage_info(MessageInfoBean message_info) {
        this.message_info = message_info;
    }

    public int getInfinite() {
        return infinite;
    }

    public void setInfinite(int infinite) {
        this.infinite = infinite;
    }

    public String getBrilliant() {
        return brilliant;
    }

    public void setBrilliant(String brilliant) {
        this.brilliant = brilliant;
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

    public int getShopping_num() {
        return shopping_num;
    }

    public void setShopping_num(int shopping_num) {
        this.shopping_num = shopping_num;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public static class IntoRoomBean implements Serializable {
        private String username;
        private String photo;
        private int status;
        private String user_id;
        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

    public static class DollMachineStatus implements Serializable {
        private String machine;
        private int status;
        private int infinite;
        private String brilliant;
        private String l_brilliant;
        private String characteristic;
        private int free;
        private int use;
        private List<SeriesListBean> series_list;

        public String getMachine() {
            return machine;
        }

        public void setMachine(String machine) {
            this.machine = machine;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getInfinite() {
            return infinite;
        }

        public void setInfinite(int infinite) {
            this.infinite = infinite;
        }

        public String getBrilliant() {
            return brilliant;
        }

        public void setBrilliant(String brilliant) {
            this.brilliant = brilliant;
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

        public int getFree() {
            return free;
        }

        public void setFree(int free) {
            this.free = free;
        }

        public int getUse() {
            return use;
        }

        public void setUse(int use) {
            this.use = use;
        }

        public List<SeriesListBean> getSeries_list() {
            return series_list;
        }

        public void setSeries_list(List<SeriesListBean> series_list) {
            this.series_list = series_list;
        }

        public static class SeriesListBean {
            /**
             * series_id : 1
             * s_infinite : 0
             * s_lightning : 0
             */

            private String series_id;
            private int s_infinite;
            private int s_lightning;

            public String getSeries_id() {
                return series_id;
            }

            public void setSeries_id(String series_id) {
                this.series_id = series_id;
            }

            public int getS_infinite() {
                return s_infinite;
            }

            public void setS_infinite(int s_infinite) {
                this.s_infinite = s_infinite;
            }

            public int getS_lightning() {
                return s_lightning;
            }

            public void setS_lightning(int s_lightning) {
                this.s_lightning = s_lightning;
            }
        }
    }

    public static class CatchStatus implements Serializable {
        private String username;
        private int status;

        public String getUsername() {
            return username;
        }

        public void setUsername(String name) {
            username = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class MessageInfoBean implements Serializable {
        /**
         * nickname : zhuawawa114
         * content : 这是一条测试信息
         * type : 1
         */

        private String nickname;
        private String content;
        private String user_id;
        private String room;
        private int type;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }
    }
}

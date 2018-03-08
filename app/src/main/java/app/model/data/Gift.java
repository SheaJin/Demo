package app.model.data;

/**
 * Created by mjn_ziyu on 2017/8/24.
 */

public class Gift {

    /**
     * create_time : 放入时间
     * toy_name : 娃娃名称
     * front_cover : 娃娃图片
     * exchange : 兑换砖石
     */
    private String sh_id;
    private long create_time;
    private long surplus;
    private String toy_name;
    private String front_cover;
    private String exchange;
    private String catch_id;
    private String toy_id;
    private boolean select;

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSh_id() {
        return sh_id;
    }

    public void setSh_id(String sh_id) {
        this.sh_id = sh_id;
    }

    public String getCatch_id() {
        return catch_id;
    }

    public void setCatch_id(String catch_id) {
        this.catch_id = catch_id;
    }

    public String getToy_id() {
        return toy_id;
    }

    public void setToy_id(String toy_id) {
        this.toy_id = toy_id;
    }

    public long getSurplus() {
        return surplus;
    }

    public void setSurplus(long surplus) {
        this.surplus = surplus;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}

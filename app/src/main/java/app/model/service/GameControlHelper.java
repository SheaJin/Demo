package app.model.service;

import app.model.data.DollMachineSocket;

/**
 * Created by jxy on 2018/3/16.
 */

public class GameControlHelper {

    /**
     * 列表娃娃机状态改变
     */
    public void dollMachineStatusChange(DollMachineSocket.DollMachineStatus dollMachineStatusList) {}

    /**
     * 游戏内娃娃机状态改变
     */
    public void machineStatusChange(DollMachineSocket dollMachineSocket) {}

    /**
     * 抓取状态改变
     */
    public void catchStatusChange(DollMachineSocket.CatchStatus catchStatusList) {}

    /**
     * 游客或玩家状态改变
     */
    public void playerChange(DollMachineSocket.IntoRoomBean intoRoomBeen) {}

    /**
     * 游戏状态改变
     */
    public void gameStatusChange(int status, String lod_id) {}

    public void catchSuccess() {}
}

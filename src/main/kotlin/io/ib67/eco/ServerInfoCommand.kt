package io.ib67.eco

import io.ib67.eco.util.ServerListPing17
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import java.net.InetSocketAddress

object ServerInfoCommand : SimpleCommand(
    MiraiMcTools, "serverinfo",
    description = "查看服务器信息"
) {
    @Handler
    suspend fun CommandSender.handle() {
        if (Setting.enableProcessingMessage) {
            sendMessage(Setting.processingMessage)
        }
        GlobalScope.launch {
            var b= InetSocketAddress(Setting.serverAddr,Setting.serverPort)
            val a= ServerListPing17()
            a.address=b
            lateinit var data: ServerListPing17.StatusResponse
            runCatching {
               data = a.fetchData()
            }.onSuccess {
                sendMessage(Setting.processMessage(true, data.players.online, data.players.max))
            }.onFailure {
                sendMessage(Setting.processMessage(false, 0, 0))
            }
//            val ms = MineStat(Setting.serverAddr, Setting.serverPort);
  //          val isUp = ms.isServerUp()
    //        sendMessage(Setting.processMessage(isUp, ms.currentPlayers, ms.maximumPlayers))
        }
    }
}
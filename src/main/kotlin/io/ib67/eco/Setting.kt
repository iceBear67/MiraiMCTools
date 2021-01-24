package io.ib67.eco

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value
import java.lang.StringBuilder

object Setting : AutoSavePluginConfig() {
    var serverAddr by value("mc.hypixel.net")
    var serverPort by value(25565)
    var serverName by value("Hypixel")
    var enableProcessingMessage by value(true)
    var processingMessage by value("正在处理中..")
    var template by value<List<String>>(
        mutableListOf(
            "%serverName",
            "=---------=",
            "连接IP: %serverAddr:%serverPort",
            "在线状态: %statusOnline",
            "在线人数: %now/%max",
            "=---------="
        )
    )
    fun List<String>.asText() : String{
        val sb=StringBuilder()
        for (s in this) {
            sb.append(s).append("\n")
        }
        return sb.toString()
    }
    fun processMessage(statusOnline: Boolean, nowPlayers: Int, maxPlayers: Int): String {
        return template.asText()
            .replaceFirst("%serverAddr", serverAddr)
            .replace("%serverName", serverName)
            .replace("%serverPort", serverPort.toString())
            .replace(
                "%statusOnline", if (statusOnline) {
                    "在线"
                } else {
                    "离线"
                }
            )
            .replace("%now", nowPlayers.toString())
            .replace("%max", maxPlayers.toString())

    }
}
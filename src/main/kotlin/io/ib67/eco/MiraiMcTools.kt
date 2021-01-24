package io.ib67.eco

import com.google.auto.service.AutoService
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
@AutoService(JvmPlugin::class)
object MiraiMcTools : KotlinPlugin(
    JvmPluginDescription(
        "MiraiMcTools",
        "1.0"
    )
) {
    override fun onEnable() {
        Setting.reload()
        ServerInfoCommand.register()
    }

    override fun onDisable() {
        ServerInfoCommand.unregister() // 取消注册指令
    }
}
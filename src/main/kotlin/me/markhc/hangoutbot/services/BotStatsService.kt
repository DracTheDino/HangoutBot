package me.markhc.hangoutbot.services

import com.gitlab.kordlib.core.entity.Guild
import me.jakejmattson.discordkt.api.Discord
import me.jakejmattson.discordkt.api.annotations.Service
import me.markhc.hangoutbot.utilities.TimeFormatter
import java.util.*

@Service
class BotStatsService(private val persistentData: PersistentData, private val discord: Discord) {
    private var startTime: Date = Date()

    suspend fun commandExecuted(guild: Guild?) {
        totalCommands++
        persistentData.setGlobalProperty {
            totalCommandsExecuted++
        }

        if (guild != null) {
            persistentData.setGuildProperty(guild) {
                totalCommandsExecuted++
            }
        }
    }

    val uptime: String
        get() = TimeFormatter.toLongDurationString(Date().time - startTime.time)

    val ping: String
        get() = "${discord.api.gateway.averagePing} ms"

    private var _totalCommands: Int = 0
    var totalCommands: Int
        get() = _totalCommands
        private set(value) {
            _totalCommands = value
        }
}
package me.markhc.hangoutbot.arguments

import me.jakejmattson.discordkt.api.arguments.*
import me.jakejmattson.discordkt.api.dsl.CommandEvent
import me.markhc.hangoutbot.services.PermissionLevel

open class PermissionLevelArg(override val name: String = "Permission Level") : ArgumentType<PermissionLevel>() {
    companion object : PermissionLevelArg()

    override fun generateExamples(event: CommandEvent<*>) = mutableListOf("BotOwner", "GuildOwner", "Administrator", "Staff", "Everyone")

    override suspend fun convert(arg: String, args: List<String>, event: CommandEvent<*>): ArgumentResult<PermissionLevel> {
        val level = PermissionLevel.values().firstOrNull {
            it.name.equals(arg, true)
        } ?: return Error("Could not retrieve permission level: $arg")

        return Success(level, 1)
    }
}

package me.markhc.hangoutbot.extensions

import me.aberrantfox.kjdautils.api.annotation.CommandSet
import me.aberrantfox.kjdautils.api.dsl.command.*
import me.markhc.hangoutbot.services.*
import java.util.*

val commandPermissions: MutableMap<Command, Permission> = mutableMapOf()

var Command.requiredPermissionLevel: Permission
    get() = commandPermissions[this] ?: DEFAULT_REQUIRED_PERMISSION
    set(value) {
        commandPermissions[this] = value
    }
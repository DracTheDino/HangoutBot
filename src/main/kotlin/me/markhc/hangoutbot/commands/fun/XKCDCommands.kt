package me.markhc.hangoutbot.commands.`fun`

import me.jakejmattson.discordkt.api.arguments.*
import me.jakejmattson.discordkt.api.dsl.commands
import me.markhc.hangoutbot.commands.`fun`.services.XKCDService
import kotlin.random.Random

fun xkcdCommands(xkcd: XKCDService) = commands("XKCD") {
    command("xkcd") {
        description = "Returns the XKCD comic number specified, or a random comic if you don't supply a number."
        execute(IntegerArg("Comic Number").makeNullableOptional()) {
            val (id) = args

            val latest = xkcd.getLatest()

            if (latest == null) {
                respond("Sorry, failed to get a comic.")
                return@execute
            }

            val response = when {
                id == null -> xkcd.getUrl(Random.nextInt(1, latest))
                id < 1 || id > latest -> "Please enter a valid comic number between 1 and $latest"
                else -> xkcd.getUrl(id)
            }

            respond(response)
        }
    }

    command("xkcd-latest") {
        description = "Grabs the latest XKCD comic."
        execute {
            val latest = xkcd.getLatest()

            if (latest == null) {
                respond("Sorry, failed to get latest comic.")
                return@execute
            }

            respond(xkcd.getUrl(latest))
        }
    }

    command("xkcd-search") {
        description = "Returns a XKCD comic that most closely matches your query."
        execute(EveryArg("Query")) {
            val (what) = args

            val result = xkcd.search(what)

            if (result == null) {
                respond("Sorry, the search failed.")
                return@execute
            }

            respond(xkcd.getUrl(result))
        }
    }
}
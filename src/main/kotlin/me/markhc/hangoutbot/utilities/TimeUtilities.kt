package me.markhc.hangoutbot.utilities

import java.util.concurrent.TimeUnit

fun Long.toShortDurationString()  =
        String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(this),
                TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(this)),
                TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this)))

fun Long.toLongDurationString(): String {
    val hours = TimeUnit.MILLISECONDS.toHours(this)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(hours)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))
    return String.format(
            "%02d hour${if(hours > 1) "s" else ""}, " +
            "%02d minute${if(minutes > 1) "s" else ""}, " +
            "%02d second${if(seconds > 1) "s" else ""}", hours, minutes, seconds)
}

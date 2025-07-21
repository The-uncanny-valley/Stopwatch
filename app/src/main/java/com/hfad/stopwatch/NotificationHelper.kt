package com.hfad.stopwatch

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(private val context: Context) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val CHANNEL_ID = "stopwatch_channel"
        const val NOTIFICATION_ID = 1
    }

    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Stopwatch",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Shows stopwatch status"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showRunningNotification(): Notification {
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stopwatch)
            .setContentTitle("Секундомер запущен")
            .setContentText("Секундомер работает в фоне")
            .setOngoing(true)
            .build()
        notificationManager.notify(NOTIFICATION_ID, notification)
        return notification
    }

    fun cancelNotification() {
        notificationManager.cancel(NOTIFICATION_ID)
    }
}
package com.mellagusty.hacigo_mobileapp.data.local.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.utils.Constant.CHANNEL_ID
import com.mellagusty.hacigo_mobileapp.utils.Constant.CHANNEL_NAME
import com.mellagusty.hacigo_mobileapp.utils.Constant.EXTRA_MESSAGE
import com.mellagusty.hacigo_mobileapp.utils.Constant.EXTRA_TYPE
import com.mellagusty.hacigo_mobileapp.utils.Constant.ID_REPEATING
import com.mellagusty.hacigo_mobileapp.utils.Constant.NOTIFICATION_ID
import com.mellagusty.hacigo_mobileapp.utils.Constant.TIME_FORMAT
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        sendNotification(context)
    }

    private fun sendNotification(context: Context?) {
        val intent =
            context?.packageManager?.getLaunchIntentForPackage("com.mellagusty.haigo_mobileapp")
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //make a builder
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(context.resources.getString((R.string.app_name)))
            .setContentText(context.getString(R.string.search))
            .setAutoCancel(true)

        //If the android SDK Oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)

        }

        //for the outside version
        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    //function for set Alarm again
    fun setRepeatingAlarm(context: Context, type: String, time: String, message: String) {
        if (isDateInvalid(time, TIME_FORMAT)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, NotificationReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)
        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(context, context.getString(R.string.reminder), Toast.LENGTH_SHORT).show()
    }

    //function for checking the Date
    private fun isDateInvalid(time: String, timeFormat: String): Boolean {
        return try {
            val df = SimpleDateFormat(timeFormat, Locale.getDefault())
            df.isLenient = false
            df.parse(time)
            false
        } catch (e: ParseException) {
            true
        }
    }

    //function for cancelling the alarm
    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        val requestCode = ID_REPEATING
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        Toast.makeText(context, context.getString(R.string.remindcancel), Toast.LENGTH_SHORT).show()
    }
}
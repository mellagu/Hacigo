package com.mellagusty.hacigo_mobileapp.data.local.notification

import android.content.Context
import com.mellagusty.hacigo_mobileapp.utils.Constant.PREFS_NAME
import com.mellagusty.hacigo_mobileapp.utils.Constant.REMINDER

class NotificationPreferences(context: Context) {
    
    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setReminder(value: NotificationData){
        val editor = preference.edit()
        editor.putBoolean(REMINDER, value.isReminded)
        editor.apply()
    }

    fun getReminder(): NotificationData{
        val model = NotificationData()
        model.isReminded = preference.getBoolean(REMINDER, false)
        return model
    }
}
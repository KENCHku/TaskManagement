package kg.geeks.taskmanagement.data.local.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

    //on boarding
    fun isBoardingSeen(): Boolean {
        return pref.getBoolean(BOARDING_SEEN_KEY, false)
    }

    fun boardingIsSeen() {
        return pref.edit().putBoolean(BOARDING_SEEN_KEY, true).apply()
    }

    //user name profile
    fun saveUserName(name: String?) {
        pref.edit().putString(USER_NAME_PROFILE, name).apply()
    }

    fun getUserName(): String? {
        return pref.getString(USER_NAME_PROFILE, "")
    }

    //user image profile
    fun saveUserImage(uri: String?){
        pref.edit().putString(USER_IMAGE_PROFILE, uri).apply()
    }

    fun getUserImage(): String?{
        return pref.getString(USER_IMAGE_PROFILE, "")
    }

    companion object {
        const val SHARED_PREF_NAME = "task_management"
        const val BOARDING_SEEN_KEY = "on_boarding_seen"
        const val USER_NAME_PROFILE = "user_name.profile"
        const val USER_IMAGE_PROFILE = "user_image.profile"

    }
}
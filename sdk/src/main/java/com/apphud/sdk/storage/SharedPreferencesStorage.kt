package com.apphud.sdk.storage

import android.content.Context
import com.apphud.sdk.domain.AppsflyerInfo
import com.apphud.sdk.domain.Customer
import com.apphud.sdk.domain.FacebookInfo
import com.apphud.sdk.parser.Parser
import com.google.gson.reflect.TypeToken

class SharedPreferencesStorage(
    context: Context,
    private val parser: Parser
) : Storage {

    companion object {
        private const val NAME = "apphud_storage"
        private const val MODE = Context.MODE_PRIVATE

        private const val USER_ID_KEY = "userIdKey"
        private const val CUSTOMER_KEY = "customerKey"
        private const val DEVICE_ID_KEY = "deviceIdKey"
        private const val ADVERTISING_DI_KEY = "advertisingIdKey"
        private const val NEED_RESTART_KEY = "needRestartKey"

        private const val FACEBOOK_KEY = "facebookKey"
        private const val APPSFLYER_KEY = "appsflyerKey"
    }

    private val preferences = context.getSharedPreferences(
        NAME,
        MODE
    )

    override var userId: String?
        get() = preferences.getString(USER_ID_KEY, null)
        set(value) {
            val editor = preferences.edit()
            editor.putString(USER_ID_KEY, value)
            editor.apply()
        }

    override var customer: Customer?
        get() {
            val source = preferences.getString(CUSTOMER_KEY, null)
            val type = object : TypeToken<Customer>() {}.type
            return parser.fromJson<Customer>(source, type)
        }
        set(value) {
            val source = parser.toJson(value)
            val editor = preferences.edit()
            editor.putString(CUSTOMER_KEY, source)
            editor.apply()
        }

    override var deviceId: String?
        get() = preferences.getString(DEVICE_ID_KEY, null)
        set(value) {
            val editor = preferences.edit()
            editor.putString(DEVICE_ID_KEY, value)
            editor.apply()
        }

    override var advertisingId: String?
        get() = preferences.getString(ADVERTISING_DI_KEY, null)
        set(value) {
            val editor = preferences.edit()
            editor.putString(ADVERTISING_DI_KEY, value)
            editor.apply()
        }

    override var isNeedSync: Boolean
        get() = preferences.getBoolean(NEED_RESTART_KEY, false)
        set(value) {
            val editor = preferences.edit()
            editor.putBoolean(NEED_RESTART_KEY, value)
            editor.apply()
        }

    override var facebook: FacebookInfo?
        get() {
            val source = preferences.getString(FACEBOOK_KEY, null)
            val type = object : TypeToken<FacebookInfo>() {}.type
            return parser.fromJson<FacebookInfo>(source, type)
        }
        set(value) {
            val source = parser.toJson(value)
            val editor = preferences.edit()
            editor.putString(FACEBOOK_KEY, source)
            editor.apply()
        }

    override var appsflyer: AppsflyerInfo?
        get() {
            val source = preferences.getString(APPSFLYER_KEY, null)
            val type = object : TypeToken<AppsflyerInfo>() {}.type
            return parser.fromJson<AppsflyerInfo>(source, type)
        }
        set(value) {
            val source = parser.toJson(value)
            val editor = preferences.edit()
            editor.putString(APPSFLYER_KEY, source)
            editor.apply()
        }
}
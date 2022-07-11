package com.aliucord.plugins.accountswitcher

import com.aliucord.Http
import com.aliucord.plugins.AccountSwitcher
import com.aliucord.utils.ReflectUtils
import com.discord.models.user.MeUser
import com.discord.stores.StoreAuthentication
import com.discord.utilities.rest.RestAPI
import com.google.gson.reflect.TypeToken

private val accountsType = TypeToken.getParameterized(ArrayList::class.java, Account::class.javaObjectType).getType()

fun fetchUser(token: String): MeUser? = try {
    Http.Request("https://discord.com/api/v9/users/@me")
        .setHeader("Authorization", token)
        .setHeader("User-Agent", RestAPI.AppHeadersProvider.INSTANCE.userAgent)
        .setHeader("accept", "application/json")
        .execute()
        .json(MeUser::class.java)
} catch (e: Throwable) {
    null
}

fun getAccounts(): ArrayList<Account> = try {
    AccountSwitcher.mSettings.getObject("accounts", ArrayList(), accountsType)
} catch (e: Throwable) {
    arrayListOf<Account>().also { AccountSwitcher.mSettings.setObject("accounts", it) }
}
package com.software.sobai.data.local.cache

import com.software.sobai.utils.SUNNY_BEACH_API_BASE_URL
import com.tencent.mmkv.MMKV
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import java.net.URI
import java.net.URL


/**
 * @Author: xiaolianyi
 * @Date:  2025-10-15
 * @Description: 缓存管理

 */
object CacheManager {

    //验证码字段名
    private const val SOB_CAPTCHA_KEY_NAME = "l_c_i"

    //token字段名
    private const val SOB_TOKEN_NAME = "sob_token"

    //SOB的账户map
    private const val SOB_ACCOUNT_MAP = "SOB_ACCOUNT_MAP"

    //mmkv实例
    private val mmkv = MMKV.mmkvWithID(SOB_ACCOUNT_MAP)

    //后端验证码key
    private var sobCaptchaKey = ""

    //需要验证码的url
    private val captchaUrls = listOf(
        "uc/user/login",
        "uc/ut/join/send-sms",
        "uc/user/register",
        "uc/ut/forget/send-sms"
    )

    private val topDomain = SUNNY_BEACH_API_BASE_URL.toHttpUrl().topPrivateDomain() ?: ""

    fun addHeader(request: Request, requestBuilder: Request.Builder) {
        val path = request.url.encodedPath.removePrefix("/")
        val hostTopDomain = request.url.topPrivateDomain() ?: ""
        //判断域名是否为我们的api
        if (hostTopDomain != topDomain) {
            return
        }

        when (path) {
            in captchaUrls -> {
                requestBuilder.addHeader(SOB_CAPTCHA_KEY_NAME, sobCaptchaKey)
            }
            else -> {
                val token = "123";
                requestBuilder.addHeader("", "")
            }

        }

    }

}
package com.software.sobai.data.model

/**
 * @Author: xiaolianyi
 * @Date:  2025-07-18
 * @Description:基类需要实现的接口

 */
interface IApiResponse<T> {
    fun getCode(): Int
    fun isSuccess(): Boolean
    fun getMessage(): String
    fun getData(): T
}
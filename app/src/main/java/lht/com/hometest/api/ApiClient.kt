package lht.com.hometest.api

import lht.com.hometest.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Use this calss for calling Retrofit API
 */
class ApiClient private constructor() {
    private var keywordAPI: KeywordAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.KEYWORD_JSON_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        keywordAPI = retrofit.create(KeywordAPI::class.java)
    }

    companion object{
        private var instance: ApiClient? = null

        /**
         * Get ApiClient instance. This instance is created once during application life cycle
         */
        @Synchronized
        fun getInstance(): ApiClient{
            if(instance == null){
                instance = ApiClient()
            }
            return instance!!
        }
    }

    /**
     * Return the Object for calling api fetch keyword list
     */
    fun getKeywordAPI(): KeywordAPI{
        return keywordAPI
    }
}
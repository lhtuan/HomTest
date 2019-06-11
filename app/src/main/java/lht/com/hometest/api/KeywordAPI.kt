package lht.com.hometest.api

import io.reactivex.Single
import retrofit2.http.GET

interface KeywordAPI{
    @GET("/tikivn/android-home-test/v2/keywords.json")
    fun getKeywords(): Single<ArrayList<String>>
}
package lht.com.hometest.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Class for handle common logic with network
 */
class NetworkUtil{
    companion object {
        /**
         * Check if device is connected to network
         * @return true if device is connected to network, false if otherwise
         */
        fun isNetWorkConnected(context: Context): Boolean{
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetWork = cm.activeNetworkInfo
            return activeNetWork?.isConnected ?: false
        }
    }
}
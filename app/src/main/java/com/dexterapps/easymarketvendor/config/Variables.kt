package com.dexterapps.easymarketvendor.config

object Variables {
    @JvmField
    var TAG = "market_"
    var SHARED_PREFERENCE_USER_FILE_NAME = "EasyMarket"

    //end points
    var BASE_URL = "https://joogadoo.com/easymarket/api/v1/"
    var ASSETS_URL = "http://joogadoo.com/easymarket/public/"
    var BANNER_URL = BASE_URL + "banners"

    //////////////name////////////////
    const val isLogin = "isLogin"
    const val USER_ID = "USER_ID"
    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val TOKEN_TYPE = "TOKEN_TYPE"
    const val TAG_DASHBOARD = "dashboard"
    const val TAG_ORDER_HISTORY = "order_history"
    const val TAG_HOME_FRAGMENT = "home_fragment"

}
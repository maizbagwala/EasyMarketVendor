package com.dexterapps.easymarketvendor.login.model


class LoginResponse {
    var access_token: String? = null
    var token_type: String? = null
    var expires_at: String? = null
    var user: User? = null
    var message: String? = null
    var success = 0
}
class User {
    var id = 0
    var phone: String? = null
}
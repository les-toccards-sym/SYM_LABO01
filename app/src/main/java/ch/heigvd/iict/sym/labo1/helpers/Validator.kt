package ch.heigvd.iict.sym.labo1.helpers

enum class ValidatorResult {
    OK,
    EMPTY_EMAIL,
    EMPTY_PASSWD,
    EMPTY_BOTH,
    INVALID_EMAIL,
    INVALID_PASSWD
}

fun validateEmail(email: String): Boolean {
    val regex = """^[a-zA-Z0-9_]+(?:.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$""".toRegex()

    return regex.matches(email)
}

fun validatePassword(pwd: String): Boolean {
    // in a real world setting, the min number of character would be 8
    val min: Int = 4
    // setting a maxing number of characters helps to prevent DoS attacks
    val max: Int = 64

    // we only check the password length because passwords
    // such as `CoinsRiverTreeJournalElephant` are strong and imposing
    // special characters, numbers and so on make it very annoying creating a password
    // #SecureCoding
    return pwd.length in min..max
}

fun authValidation(email: String?, passwd: String?, checkPwdPolicy: Boolean): ValidatorResult {

    if (email.isNullOrEmpty() or passwd.isNullOrEmpty()) {
        var emptynessResult = ValidatorResult.EMPTY_BOTH

        //  set the error messages
        if (email.isNullOrEmpty())
            emptynessResult = ValidatorResult.EMPTY_EMAIL
        if (passwd.isNullOrEmpty())
            emptynessResult = ValidatorResult.EMPTY_PASSWD
        if (email.isNullOrEmpty() and passwd.isNullOrEmpty())
            emptynessResult = ValidatorResult.EMPTY_BOTH

        return emptynessResult
    }

    if (!validateEmail(email.toString()))
        return ValidatorResult.INVALID_EMAIL

    if (checkPwdPolicy and !validatePassword(passwd.toString()))
        return ValidatorResult.INVALID_PASSWD

    return ValidatorResult.OK
}
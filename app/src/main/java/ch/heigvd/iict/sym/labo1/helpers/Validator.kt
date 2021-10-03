package ch.heigvd.iict.sym.labo1.helpers

import android.content.Context
import android.content.res.Resources
import android.widget.EditText
import ch.heigvd.iict.sym.labo1.R

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

fun authFieldsValidation(
    email: String?,
    passwd: String?,
    policyCheck: Boolean,
    context: Context): Map<String, String> {

    val errors = mutableMapOf(
        "email_error" to "",
        "passwd_error" to ""
    )

    if (email.isNullOrEmpty() or passwd.isNullOrEmpty()) {
        //  set the error messages
        if (email.isNullOrEmpty())
            errors["email_error"] = context.getString(R.string.main_mandatory_field)
        if (passwd.isNullOrEmpty())
            errors["passwd_error"] = context.getString(R.string.main_mandatory_field)

        return errors
    }

    if (!validateEmail(email.toString()))
        errors["email_error"] = context.getString(R.string.invalid_email)

    if (policyCheck and !validatePassword(passwd.toString()))
        errors["passwd_error"] = context.getString(R.string.invalid_password)

    return errors
}
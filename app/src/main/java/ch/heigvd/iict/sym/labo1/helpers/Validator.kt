package ch.heigvd.iict.sym.labo1.helpers

import android.content.Context
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
    email: EditText,
    passwd: EditText,
    policyCheck: Boolean,
    context: Context): Boolean {

    //on réinitialise les messages d'erreur
    email.error = null
    passwd.error = null

    //on récupère le contenu de deux champs dans des variables de type String
    val emailInput = email.text?.toString()
    val passwdInput = passwd.text?.toString()

    if (emailInput.isNullOrEmpty() or passwdInput.isNullOrEmpty()) {
        if (emailInput.isNullOrEmpty())
            email.error = context.getString(R.string.main_mandatory_field)

        if (passwdInput.isNullOrEmpty())
            passwd.error = context.getString(R.string.main_mandatory_field)

        return false
    } else {
        if (!validateEmail(emailInput.toString())) {
            email.error = context.getString(R.string.invalid_email)
            return false
        }

        if (policyCheck and !validatePassword(passwdInput.toString())) {
            passwd.error = context.getString(R.string.invalid_password)
            return false
        }
    }

    return true
}
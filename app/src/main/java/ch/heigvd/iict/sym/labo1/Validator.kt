package ch.heigvd.iict.sym.labo1

class Validator {

    companion object {
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
    }
}
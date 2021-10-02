package ch.heigvd.iict.sym.labo1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import ch.heigvd.iict.sym.labo1.data.IUserRepository
import ch.heigvd.iict.sym.labo1.helpers.Validator
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button

    private val usersRepository by inject<IUserRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.main_email)
        password = findViewById(R.id.main_password)
        cancelButton = findViewById(R.id.main_cancel)
        validateButton = findViewById(R.id.main_validate)

        cancelButton.setOnClickListener {
            // return to calling activity
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        validateButton.setOnClickListener {
            // reset error messages
            email.error = null
            password.error = null

            // get the inputed values
            val emailInput = email.text?.toString()
            val passwordInput = password.text?.toString()

            // make sure the fields were filled
            if (emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {

                // set the error messages
                if(emailInput.isNullOrEmpty())
                    email.error = getString(R.string.main_mandatory_field)
                if(passwordInput.isNullOrEmpty())
                    password.error = getString(R.string.main_mandatory_field)

                return@setOnClickListener
            }

            // check if the email syntax is valid
            if (!Validator.validateEmail(emailInput.toString())) {
                email.error = getString(R.string.invalid_email)
                return@setOnClickListener
            }

            // check if the password follows the
            if (!Validator.validatePassword(passwordInput.toString())) {
                password.error = getString(R.string.invalid_password)
                return@setOnClickListener
            }

            // check if the user already exists
            if (usersRepository.findByEmail(emailInput.toString()) != null) {
                toast("User already exists")
                return@setOnClickListener
            }

            // everything is gucci, we can save the new users
            usersRepository.save(Pair(emailInput.toString(), passwordInput.toString()))

            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
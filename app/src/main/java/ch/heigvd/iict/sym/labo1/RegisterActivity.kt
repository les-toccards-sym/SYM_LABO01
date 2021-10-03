package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import ch.heigvd.iict.sym.labo1.helpers.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button

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

            val res = authFieldsValidation(
                emailInput, passwordInput, policyCheck = true, this
            )

            if (!res["email_error"].isNullOrBlank() or !res["passwd_error"].isNullOrBlank()) {
                if (!res["email_error"].isNullOrBlank())
                    email.error = res["email_error"].toString()

                if (!res["passwd_error"].isNullOrBlank())
                    password.error = res["passwd_error"].toString()

                return@setOnClickListener
            }

            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("email", emailInput.toString())
                putExtra("password", passwordInput.toString())
            })
            finish()
        }
    }
}
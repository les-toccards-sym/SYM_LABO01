/**
 * @author Yohann Polus <yohann.polus@heig-vd.ch>
 * @author Melvin Merk <melvin.merk@heig-vd.ch>
 * @author Doran Kayoumi <doran.kayoumi@heig-vd.ch>
 */

package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import ch.heigvd.iict.sym.labo1.helpers.*

class RegisterActivity : BaseActivity() {

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
            if (authFieldsValidation(email, password, policyCheck = true, this)) {
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra("email", email.text?.toString().toString())
                    putExtra("password", password.text?.toString().toString())
                })
                finish()
            }

            return@setOnClickListener
        }
    }
}
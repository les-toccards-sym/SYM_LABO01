package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import ch.heigvd.iict.sym.labo1.data.InMemoryUserRepository
import ch.heigvd.iict.sym.labo1.helpers.authFieldsValidation

class MainActivity : AppCompatActivity() {

    private val intentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                usersRepository.save(Pair(
                    result.data?.getStringExtra("email").toString(),
                    result.data?.getStringExtra("password").toString()
                ))
                toast("Account successfully created")
            }
        }

    // le modifieur lateinit permet de définir des variables avec un type non-null
    // sans pour autant les initialiser immédiatement
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button
    private lateinit var registerButton: TextView

    private val usersRepository = InMemoryUserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_main)

        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        email = findViewById(R.id.main_email)
        password = findViewById(R.id.main_password)
        cancelButton = findViewById(R.id.main_cancel)
        validateButton = findViewById(R.id.main_validate)
        registerButton = findViewById(R.id.main_new_account)

        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        //mise en place des événements
        cancelButton.setOnClickListener {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            email.text?.clear()
            password.text?.clear()

            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            email.error = null
            password.error = null
        }

        validateButton.setOnClickListener {
            if (authFieldsValidation(email, password, policyCheck = false, this)) {
                val emailInput = email.text?.toString()
                val passwordInput = password.text?.toString()

                if (usersRepository.findByEmailAndPasswd(emailInput.toString(), passwordInput.toString())) {
                    toast(getString(R.string.main_success_auth))
                    startActivity(Intent(this, ProfileActivity::class.java).apply {
                        putExtra("email", emailInput)
                    })
                } else {
                    AlertDialog.Builder(this).apply {
                        setTitle(R.string.failed_auth_title)
                        setMessage(getString(R.string.main_failed_auth))
                        setPositiveButton(R.string.ok) {dialog, _ ->
                            dialog.dismiss()
                        }
                    }.create().show()
                }
            }

            return@setOnClickListener
        }

        registerButton.setOnClickListener {
            // registerForActivityResult(intent, 1)
            this.intentLauncher.launch(Intent(this, RegisterActivity::class.java))
        }

    }

    // En Kotlin, les variables static ne sont pas tout à fait comme en Java
    // pour des raison de lisibilité du code, les variables et méthodes static
    // d'une classe doivent être regroupées dans un bloc à part: le companion object
    // cela permet d'avoir un aperçu plus rapide de tous les éléments static d'une classe
    // sans devoir trouver le modifieur dans la définition de ceux-ci, qui peuvent être mélangé
    // avec les autres éléments non-static de la classe
    companion object {
        private const val TAG: String = "MainActivity"
    }

}

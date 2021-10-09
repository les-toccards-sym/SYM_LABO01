package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val email=intent.getStringExtra("email")
        toast("Bonjour "+email+" !")
        val textView = findViewById<TextView>(R.id.main_email2)
        textView.setText(email).toString()
        //val textViewValue = textView.text
        val connectedImage = findViewById<ImageView>(R.id.connected_image)
        ImageDownloader(connectedImage, "https://thispersondoesnotexist.com/image").show()

    }
}
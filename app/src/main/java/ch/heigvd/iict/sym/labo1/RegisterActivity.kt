package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val data = Intent()
        data.putExtra("streetkey", "streetname")
        data.putExtra("citykey", "cityname")
        data.putExtra("homekey", "homename")

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
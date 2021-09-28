package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.widget.Toast

fun Activity.toast(message: String,
                   duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show();
}
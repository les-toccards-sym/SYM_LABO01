/**
 * @author Yohann Polus <yohann.polus@heig-vd.ch>
 * @author Melvin Merk <melvin.merk@heig-vd.ch>
 * @author Doran Kayoumi <doran.kayoumi@heig-vd.ch>
 */

package ch.heigvd.iict.sym.labo1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        print("onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        print("onStart")
        super.onStart()
    }

    override fun onRestart() {
        print("onRestart")
        super.onRestart()
    }

    override fun onResume() {
        print("onResume")
        super.onResume()
    }

    override fun onPause() {
        print("onPause")
        super.onPause()
    }

    override fun onStop() {
        print("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        print("onDestroy")
        super.onDestroy()
    }

    private fun print(cycle: String) {
        // Log info
        Log.i("Cycle", cycle + ": ${this.javaClass.simpleName}")
    }
}

package otus.gpb.homework.fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentA: FragmentA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            if (savedInstanceState == null) {
                fragmentA = FragmentA()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_a, fragmentA, "A")
                    .commit()
            }
        }
    }
}
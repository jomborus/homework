package otus.gpb.homework.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class MainActivity2 : AppCompatActivity() {
    private var isTablet = false
    private lateinit var fragmentBA: FragmentBA
    private lateinit var fragmentBB: FragmentBB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        determinePaneLayout()
    }

    private fun determinePaneLayout() {
        val fragmentItemDetail: FrameLayout? = findViewById(R.id.container_bb)
        if (fragmentItemDetail != null) {
            isTablet = true
            fragmentBA = FragmentBA()
            fragmentBB = FragmentBB()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_ba, FragmentBA.create(isTablet), "BA")
                .replace(R.id.container_bb, fragmentBB, "BB")
                .commit()
        } else {
            isTablet = false
            fragmentBA = FragmentBA()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_ba, FragmentBA.create(isTablet), "BA")
                .commit()
        }
    }
}
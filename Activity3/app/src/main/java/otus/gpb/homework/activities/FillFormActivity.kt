package otus.gpb.homework.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class FillFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_form)
        findViewById<Button>(R.id.apply_button).setOnClickListener {
            val intent = Intent().apply {
                putExtra("name", findViewById<EditText>(R.id.name_text).text.toString())
                putExtra("surname", findViewById<EditText>(R.id.surname_text).text.toString())
                putExtra("age", findViewById<EditText>(R.id.age_text).text.toString())
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}

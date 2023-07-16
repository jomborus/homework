package otus.gpb.homework.activities.receiver

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReceiverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver)

        val titleTextView = findViewById<TextView>(R.id.titleTextView)

        titleTextView.text = intent.getStringExtra("title")
        findViewById<TextView>(R.id.yearTextView).text = intent.getStringExtra("year")
        findViewById<TextView>(R.id.descriptionTextView).text = intent.getStringExtra("description")
        val imageId = when(titleTextView.text) {
            "Славные парни" -> R.drawable.niceguys
            "Интерстеллар" -> R.drawable.interstellar
            else -> null
        }
        imageId?.let {
            findViewById<ImageView>(R.id.posterImageView).setImageDrawable(getDrawable(it))
        }
    }
}
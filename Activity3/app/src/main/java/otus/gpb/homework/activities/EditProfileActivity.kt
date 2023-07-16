package otus.gpb.homework.activities

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class EditProfileActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var nameText: TextView
    private lateinit var surnameText: TextView
    private lateinit var ageText: TextView

    private var imageUri: Uri? = null

    private val preferences = Preferences()

    private val gallery =
        registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
            result?.let {
                imageUri = result
                populateImage(result)
            }
        }

    private val resultContract =
        registerForActivityResult(Contract()) { profile ->
            profile?.let {
                nameText.text = profile.name
                surnameText.text = profile.surname
                ageText.text = profile.age
            }
        }

    private val permissionCamera =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> {
                    preferences.putAttempt(0, this)
                    imageView.setImageDrawable(getDrawable(R.drawable.cat))
                }

                !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    onThirdCancelPermission()
                }

                else -> {
                    val attempt = preferences.getAttempt(this)
                    when (attempt) {
                        0 -> {}
                        1 -> onSecondCancelPermission()
                        else -> onThirdCancelPermission()
                    }
                    preferences.putAttempt(attempt + 1, this)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        imageView = findViewById(R.id.imageview_photo)
        nameText = findViewById(R.id.textview_name)
        surnameText = findViewById(R.id.textview_surname)
        ageText = findViewById(R.id.textview_age)

        findViewById<Toolbar>(R.id.toolbar).apply {
            inflateMenu(R.menu.menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.send_item -> {
                        Log.d("MainActivity", "Click1")
                        openSenderApp()
                        true
                    }

                    else -> false
                }
            }
        }
        findViewById<ImageView>(R.id.imageview_photo).setOnClickListener {
            onClickImage()
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            resultContract.launch(null)
        }
    }

    /**
     * Используйте этот метод чтобы отобразить картинку полученную из медиатеки в ImageView
     */
    private fun populateImage(uri: Uri) {
        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
        imageView.setImageBitmap(bitmap)
    }

    private fun openSenderApp() {
        imageUri?.let {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/jpeg"
            intent.setPackage("org.telegram.messenger")
            intent.putExtra(Intent.EXTRA_STREAM, it)
            startActivity(intent)
        }
    }

    private fun onClickImage() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Выбор есть всегда")
            .setPositiveButton("Сделать фото") { _, _ ->
                permissionCamera.launch(Manifest.permission.CAMERA)
            }
            .setNegativeButton("Выбрать фото") { _, _ ->
                gallery.launch("image/*")
            }
        builder.create().show()
    }

    private fun onSecondCancelPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Чтобы сделать фото, необходимо разрешение на использование камеры")
            .setPositiveButton("Хорошо") { _, _ ->
                permissionCamera.launch(Manifest.permission.CAMERA)
            }
            .setNegativeButton("Назад") { dialog, _ ->
                dialog.cancel()
            }
        builder.create().show()
    }

    private fun onThirdCancelPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Чтобы сделать фото, необходимо разрешение на использование камеры")
            .setPositiveButton("Хорошо") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    val uri = Uri.fromParts("package", packageName, null)
                    data = uri
                }
                startActivity(intent)
            }
        builder.create().show()
    }
}
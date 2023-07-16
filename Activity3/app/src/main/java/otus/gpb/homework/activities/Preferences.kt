package otus.gpb.homework.activities

import android.content.Context

class Preferences {
    fun putAttempt(attempt: Int, context: Context) {
        val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
            .edit().putInt(ATTEMPT, attempt).apply()
    }

    fun getAttempt(context: Context): Int {
        return context.getSharedPreferences("preferences", Context.MODE_PRIVATE).getInt(ATTEMPT, 0)
    }

    private companion object {
        const val ATTEMPT = "attempt"
    }
}

package otus.gpb.recyclerview

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {

    private val list = mutableListOf<MainState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemData = list[position]
        holder.bind(itemData)
    }

    open class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(itemData: MainState) {
            itemView.findViewById<ImageView>(R.id.ava_image)
                .setImageDrawable(itemView.context.getDrawable(R.drawable.baseline_person_pin))
            itemView.findViewById<TextView>(R.id.title_text).also {
                var text = itemData.title
                val sb = SpannableStringBuilder(text)
                if (itemData.imagesAfterTitle[0] == 1) {
                    val imageSpan = ImageSpan(itemView.context, R.drawable.baseline_volume_off)
                    sb.setSpan(
                        imageSpan,
                        text.length - 1,
                        text.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                it.text = sb
            }
            itemView.findViewById<TextView>(R.id.subtitle_text).text = itemData.subtitle
            itemView.findViewById<TextView>(R.id.message_text).text = itemData.message
            itemView.findViewById<TextView>(R.id.date_text).text = itemData.date
            val jackdawImage = itemView.findViewById<ImageView>(R.id.jackdaw_image)
            when (itemData.jackdawImage) {
                1 -> jackdawImage.setImageDrawable(itemView.context.getDrawable(R.drawable.baseline_done))

                2 -> jackdawImage.setImageDrawable(itemView.context.getDrawable(R.drawable.baseline_done_all))

                else -> jackdawImage.visibility = View.GONE
            }
            if (itemData.unreadNum == 0) {
                itemView.findViewById<View>(R.id.unread_group).visibility = View.GONE
            } else {
                itemView.findViewById<View>(R.id.unread_group).visibility = View.VISIBLE
                itemView.findViewById<TextView>(R.id.unread_text).text =
                    itemData.unreadNum.toString()
            }

        }
    }

    fun submitData(data: MutableList<MainState>) {
        list.clear()
        list.addAll(data)
    }

    fun removeItem(adapterPosition: Int) {
        list.removeAt(adapterPosition)
    }
}

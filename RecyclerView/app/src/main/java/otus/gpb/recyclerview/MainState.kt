package otus.gpb.recyclerview

data class MainState(
    val image: String,
    val title: String,
    val subtitle: String,
    val message: String,
    val date: String,
    val jackdawImage: Int,
    val unreadNum: Int,
    val imagesAfterTitle: List<Int>,
)

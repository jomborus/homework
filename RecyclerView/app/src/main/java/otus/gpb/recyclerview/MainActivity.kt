package otus.gpb.recyclerview

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private val mainAdapter by lazy {
        MainAdapter()
    }
    private val dataList = mutableListOf<MainState>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val layoutManager = LinearLayoutManager(this@MainActivity)
        val paging = PageScrollListener(layoutManager).apply {
            setOnLoadMoreListener {
                Toast.makeText(this@MainActivity, "Load More", Toast.LENGTH_SHORT).show()
                val position = dataList.size
                for (i in 1..10) {
                    dataList.add(
                        MainState(
                            image = "0",
                            title = "Title$i$i ",
                            subtitle = "Subtitle$i",
                            message = "Message$i",
                            date = "date$i",
                            jackdawImage = 1 % 3,
                            unreadNum = i % 5,
                            imagesAfterTitle = listOf(i % 2),
                        )
                    )
                    mainAdapter.submitData(dataList)
                    isLoading = false
                    mainAdapter.notifyItemInserted(position)
                }
            }
        }
        recyclerView.apply {
            this.layoutManager = layoutManager
            adapter = mainAdapter
            addOnScrollListener(paging)
        }

//        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
//            override fun getMovementFlags(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder
//            ): Int {
//                return makeMovementFlags(ItemTouchHelper.DOWN, ItemTouchHelper.START)
//            }
//
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val position = viewHolder.adapterPosition
//                mainAdapter.removeItem(position)
//                mainAdapter.notifyItemRemoved(position)
//            }
//
//        })
        val itemTouchHelper = ItemTouchHelper(object : SwipeToDeleteCallback(applicationContext){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                mainAdapter.removeItem(position)
                mainAdapter.notifyItemRemoved(position)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        for (i in 1..10) {
            dataList.add(
                MainState(
                    image = "0",
                    title = "Title$i ",
                    subtitle = "Subtitle$i",
                    message = "Message$i",
                    date = "date$i",
                    jackdawImage = i % 3,
                    unreadNum = i % 5,
                    imagesAfterTitle = listOf(i % 2),
                )
            )
        }

        mainAdapter.submitData(dataList)
        mainAdapter.notifyDataSetChanged()
    }
}

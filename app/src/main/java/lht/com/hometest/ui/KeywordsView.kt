package lht.com.hometest.ui

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lht.com.hometest.R
import lht.com.hometest.utils.StringUtil

/**
 * View for displaying keyword list
 */
class KeywordsView: LinearLayout{
    private lateinit var recyclerView: RecyclerView
    private var adapter: KeywordsAdapter? = null
    private var keywords: ArrayList<String> = ArrayList()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        LayoutInflater.from(context).inflate(R.layout.keywords_view, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        recyclerView = findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        ContextCompat.getDrawable(context, R.drawable.divider)?.let {
            decoration.setDrawable(it)
        }
        recyclerView.addItemDecoration(decoration)
        adapter = KeywordsAdapter()
        recyclerView.adapter = adapter
    }

    /**
     * Set data for this adapter then notify UI
     */
    fun setKeywords(kw: ArrayList<String>?){
        keywords.clear()
        if(kw != null){
            keywords.addAll(kw)
        }
        adapter?.notifyDataSetChanged()
    }

    inner class KeywordsAdapter: RecyclerView.Adapter<KeywordHolder>(){
        private var colors: Array<String> = resources.getStringArray(R.array.keyword_colors)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordHolder {
            return KeywordHolder(LayoutInflater.from(parent.context).inflate(R.layout.keyword_item, parent, false))
        }

        override fun getItemCount(): Int {
            return keywords.size
        }

        override fun onBindViewHolder(holder: KeywordHolder, position: Int) {
            holder.bind(keywords[position], colors[position%colors.size])
        }
    }

    class KeywordHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tv: TextView = itemView.findViewById(R.id.tv)

        /**
         * Bind keyword data to view
         * @param keyword: keyword text for displaying
         * @param color: background color
         */
        fun bind(keyword: String, color: String){
            val kw = StringUtil.breakKeyword(keyword)
            if(kw == null){
                tv.text = ""
            }else{
                tv.text = kw
            }
            itemView.background.colorFilter = PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
        }
    }
}
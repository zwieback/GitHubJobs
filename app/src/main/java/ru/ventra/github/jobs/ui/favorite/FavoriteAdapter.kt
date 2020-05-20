package ru.ventra.github.jobs.ui.favorite

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.databinding.FavoriteItemBinding
import ru.ventra.github.jobs.persistence.entity.Position
import ru.ventra.github.jobs.ui.position.OnPositionClickListener

class FavoriteAdapter(private val clickListener: OnPositionClickListener) : BaseAdapter() {

    init {
        addSection(arrayListOf<Position>())
    }

    fun replacePositions(positions: List<Position>) {
        sections().firstOrNull()?.run {
            clear()
            addAll(positions)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.favorite_item

    override fun viewHolder(layout: Int, view: View): FavoriteViewHolder {
        return FavoriteViewHolder(
            view,
            FavoriteItemBinding.bind(view),
            clickListener
        )
    }
}

package ru.ventra.github.jobs.ui.position

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.databinding.PositionsItemBinding
import ru.ventra.github.jobs.persistence.entity.Position

class PositionsAdapter(private val clickListener: OnPositionClickListener) : BaseAdapter() {

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

    override fun layout(sectionRow: SectionRow) = R.layout.positions_item

    override fun viewHolder(layout: Int, view: View): PositionsViewHolder {
        return PositionsViewHolder(
            view,
            PositionsItemBinding.bind(view),
            clickListener
        )
    }
}

package ru.ventra.github.jobs.ui.position

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import ru.ventra.github.jobs.R
import ru.ventra.github.jobs.databinding.PositionsItemBinding
import ru.ventra.github.jobs.persistence.entity.Position

class PositionsAdapter : BaseAdapter() {

    init {
        addSection(arrayListOf<Position>())
    }

    fun replacePositions(currencies: List<Position>) {
        sections().first().run {
            clear()
            addAll(currencies)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.positions_item

    override fun viewHolder(layout: Int, view: View): PositionsViewHolder {
        return PositionsViewHolder(
            view,
            PositionsItemBinding.bind(view)
        )
    }
}

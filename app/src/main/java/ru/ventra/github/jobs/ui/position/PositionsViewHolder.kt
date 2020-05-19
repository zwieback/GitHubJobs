package ru.ventra.github.jobs.ui.position

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import ru.ventra.github.jobs.databinding.PositionsItemBinding
import ru.ventra.github.jobs.persistence.entity.Position

class PositionsViewHolder(
    view: View,
    private val binding: PositionsItemBinding
) : BaseViewHolder(view) {

    private lateinit var position: Position

    override fun bindData(data: Any) {
        if (data is Position) {
            this.position = data
            drawCurrency()
        }
    }

    private fun drawCurrency() {
        with(binding) {
            title.text = position.title
            company.text = position.company
        }
    }

    override fun onClick(view: View?) = Unit //clickListener.onCurrencyClick(currency)

    override fun onLongClick(view: View?) = false //longClickListener.onCurrencyLongClick(currency)
}

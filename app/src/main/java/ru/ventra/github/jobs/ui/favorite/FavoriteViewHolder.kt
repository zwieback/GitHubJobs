package ru.ventra.github.jobs.ui.favorite

import android.view.View
import androidx.annotation.DrawableRes
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import ru.ventra.github.jobs.databinding.FavoriteItemBinding
import ru.ventra.github.jobs.persistence.entity.Position
import ru.ventra.github.jobs.ui.position.OnPositionClickListener

class FavoriteViewHolder(
    view: View,
    private val binding: FavoriteItemBinding,
    private val clickListener: OnPositionClickListener
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
            favorite.setImageResource(getFavoriteDrawableRes(position))
        }
    }

    override fun onClick(view: View?) {
        clickListener.onPositionClick(position)
        binding.favorite.setImageResource(getFavoriteDrawableRes(position))
    }

    override fun onLongClick(view: View?) = false //longClickListener.onPositionLongClick(position)

    @DrawableRes
    private fun getFavoriteDrawableRes(position: Position): Int {
        return if (position.favorite) {
            android.R.drawable.btn_star_big_on
        } else {
            android.R.drawable.btn_star_big_off
        }
    }
}

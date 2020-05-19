package ru.ventra.github.jobs.ui.position

import ru.ventra.github.jobs.persistence.entity.Position

interface OnPositionClickListener {
    fun onPositionClick(position: Position)
}

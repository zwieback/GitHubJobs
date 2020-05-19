package ru.ventra.github.jobs.extensions

import android.content.Context
import android.widget.Toast

/**
 * Extension method to show toast for [Context].
 */
fun Context?.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    this?.let { Toast.makeText(it, text, duration).show() }

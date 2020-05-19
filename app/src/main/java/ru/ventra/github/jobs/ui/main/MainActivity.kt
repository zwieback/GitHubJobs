package ru.ventra.github.jobs.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ru.ventra.github.jobs.databinding.MainActivityBinding
import ru.ventra.github.jobs.extensions.viewBinding
import ru.ventra.github.jobs.ui.base.OnFragmentEventListener

class MainActivity : AppCompatActivity(), OnFragmentEventListener {

    private val binding by viewBinding(MainActivityBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        showBackButtonInActionBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onChangeToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showBackButtonInActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

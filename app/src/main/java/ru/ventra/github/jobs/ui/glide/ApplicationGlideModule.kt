package ru.ventra.github.jobs.ui.glide

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class ApplicationGlideModule : AppGlideModule() {

    /**
     * See [why do need to turn off manifest parsing](http://bumptech.github.io/glide/doc/configuration.html#manifest-parsing)
     */
    override fun isManifestParsingEnabled(): Boolean = false
}

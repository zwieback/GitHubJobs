package ru.ventra.github.jobs.persistence.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "t_position",
    indices = [Index(value = ["title", "description"], name = "idx_title_description")]
)
data class Position(
    @PrimaryKey
    val id: String,
    val url: String,
    val type: String,
    val title: String,
    val location: String,
    val company: String,
    @ColumnInfo(name = "company_logo")
    val companyLogo: String?,
    @ColumnInfo(name = "company_url")
    val companyUrl: String?,
    val description: String,
    @ColumnInfo(name = "how_to_apply")
    val howToApply: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    var favorite: Boolean = false
) : Parcelable

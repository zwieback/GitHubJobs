package ru.ventra.github.jobs.persistence.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "t_position")
data class Position(
    @PrimaryKey
    val id: String,
    val url: String,
    val type: String,
    val title: String,
    val location: String,
    val company: String,
    @SerializedName("company_logo")
    @ColumnInfo(name = "company_logo")
    val companyLogo: String?,
    @SerializedName("company_url")
    @ColumnInfo(name = "company_url")
    val companyUrl: String?,
    val description: String,
    @SerializedName("how_to_apply")
    @ColumnInfo(name = "how_to_apply")
    val howToApply: String,
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    val createdAt: String
) : Parcelable

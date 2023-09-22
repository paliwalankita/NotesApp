package com.example.notesapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity( tableName = "notes_table")
@Parcelize
class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "note")
    val note: String,
    @ColumnInfo(name = "date")
    val date: String
): Parcelable

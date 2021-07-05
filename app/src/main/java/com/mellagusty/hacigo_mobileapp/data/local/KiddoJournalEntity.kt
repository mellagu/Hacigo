package com.mellagusty.hacigo_mobileapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "KiddoJournal")
class KiddoJournalEntity : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    @ColumnInfo(name = "title")
    var title:String? = null

    @ColumnInfo(name = "sub_title")
    var subTitle:String? = null

    @ColumnInfo(name = "weight")
    var weight:String? = null

    @ColumnInfo(name = "height")
    var height:String? = null

    @ColumnInfo(name = "ageInMonth")
    var ageInMonth:String? = null

    @ColumnInfo(name = "date_time")
    var dateTime:String? = null

    @ColumnInfo(name = "journal_text")
    var journalText:String? = null

    @ColumnInfo(name = "img_path")
    var imgPath:String? = null

    @ColumnInfo(name = "web_link")
    var webLink:String? = null

    @ColumnInfo(name = "color")
    var color:String? = null




}
package com.example.newmvi.db.room.convertors

import androidx.room.TypeConverter
import java.util.*

class UUIDConvertor {

    @TypeConverter
    fun uuidToString(uuid: UUID): String = uuid.toString()

    @TypeConverter
    fun stringToUUID(string: String): UUID = UUID.fromString(string)

}
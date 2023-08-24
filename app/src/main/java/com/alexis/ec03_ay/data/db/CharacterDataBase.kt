package com.alexis.ec03_ay.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexis.ec03_ay.model.ChEntity
import com.alexis.ec03_ay.model.Character

@Database(entities = [ChEntity::class], version = 1)
abstract class CharacterDataBase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object{
        @Volatile
        private var instance: CharacterDataBase? = null
        fun getDataBase(context: Context): CharacterDataBase {
            val tempInstance = instance
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val _instance = Room.databaseBuilder(context.applicationContext, CharacterDataBase::class.java,"characterdb").build()
                instance = _instance
                return  _instance
            }
        }
    }
}
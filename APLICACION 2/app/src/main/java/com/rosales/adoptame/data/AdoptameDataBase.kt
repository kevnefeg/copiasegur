package com.rosales.adoptame.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rosales.adoptame.data.dao.*
import com.rosales.adoptame.data.model.*

@Database(
    entities = [Pet::class, Product::class, Service::class, Shelter::class,ToAdopt::class,User::class],
    version = 1,
    exportSchema = false
)
abstract class AdoptameDataBase : RoomDatabase() {
    abstract fun petDao(): PetDao
    abstract fun productDao(): ProductDao
    abstract fun serviceDao(): ServiceDao
    abstract fun shelterDao(): ShelterDao
    abstract fun toadoptDao(): ToAdoptDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AdoptameDataBase? = null

        fun getInstance(context: Context): AdoptameDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AdoptameDataBase::class.java,
                    "adoptame"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
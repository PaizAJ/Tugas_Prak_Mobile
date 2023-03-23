package id.ac.unpas.catatanpeminjamanbuku.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.catatanpeminjamanbuku.model.PeminjamanBuku

@Database(entities = [PeminjamanBuku::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PeminjamanBukuDao(): PeminjamanBukuDao
}


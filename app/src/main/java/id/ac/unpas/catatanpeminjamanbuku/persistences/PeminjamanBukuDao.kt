package id.ac.unpas.catatanpeminjamanbuku.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.catatanpeminjamanbuku.model.PeminjamanBuku

@Dao
interface PeminjamanBukuDao {
    @Query("SELECT * FROM PeminjamanBuku")
    fun loadAll(): LiveData<List<PeminjamanBuku>>
    @Query("SELECT * FROM PeminjamanBuku WHERE id = :id")
    fun find(id: String): PeminjamanBuku?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: PeminjamanBuku)
    @Delete
    fun delete(item: PeminjamanBuku)
}
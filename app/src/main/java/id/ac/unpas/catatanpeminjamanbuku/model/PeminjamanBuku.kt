package id.ac.unpas.catatanpeminjamanbuku.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PeminjamanBuku(
    @PrimaryKey val id: String,
    val tanggal: String,
    val nama: String,
    val judul: String,
    val waktu_pengembalian: String
)

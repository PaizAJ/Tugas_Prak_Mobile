package id.ac.unpas.catatanpeminjamanbuku.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.catatanpeminjamanbuku.model.PeminjamanBuku
import id.ac.unpas.catatanpeminjamanbuku.persistences.AppDatabase
import id.ac.unpas.catatanpeminjamanbuku.persistences.PeminjamanBukuDao
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PengelolaanBukuScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pengelolaan-buku"
    ).build()
    val PeminjamanBukuDao = db.PeminjamanBukuDao()

    val list : LiveData<List<PeminjamanBuku>> = PeminjamanBukuDao.loadAll()
    val items: List<PeminjamanBuku> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        FormPeminjamanBuku(PeminjamanBukuDao)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal", fontSize = 14.sp)
                        Text(
                            text = item.tanggal, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama", fontSize = 14.sp)
                        Text(
                            text = item.nama, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Judul", fontSize = 14.sp)
                        Text(text = item.judul, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Waktu Pengembalian", fontSize = 14.sp)
                        Text(text = item.waktu_pengembalian, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}
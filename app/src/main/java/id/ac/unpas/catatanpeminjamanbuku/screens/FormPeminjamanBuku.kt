package id.ac.unpas.catatanpeminjamanbuku.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.catatanpeminjamanbuku.model.PeminjamanBuku
import id.ac.unpas.catatanpeminjamanbuku.persistences.PeminjamanBukuDao
import id.ac.unpas.catatanpeminjamanbuku.ui.theme.Purple700
import id.ac.unpas.catatanpeminjamanbuku.ui.theme.Teal200
import kotlinx.coroutines.launch



@Composable
fun FormPeminjamanBuku (PeminjamanBukuDao: PeminjamanBukuDao) {
    val tanggal = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val judul = remember { mutableStateOf(TextFieldValue("")) }
    val waktu_pengembalian = remember { mutableStateOf(TextFieldValue("")) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        Text(
            text = "Aplikasi Pencatatan Peminjaman Buku",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A237E),
            modifier = Modifier.padding(vertical = 24.dp)
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal") },
            value = tanggal.value,
            onValueChange = {
                tanggal.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Judul") },
            value = judul.value,
            onValueChange = {
                judul.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Text),
            placeholder = { Text(text = "XXXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Waktu Pengembalian") },
            value = waktu_pengembalian.value,
            onValueChange = {
                waktu_pengembalian.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {

                val id = uuid4().toString()
                val item = PeminjamanBuku(id,tanggal.value.text, nama.value.text,
                    judul.value.text, waktu_pengembalian.value.text)

                if (tanggal.value.text.isEmpty()) {
                    Toast.makeText(context, "Tanggal harus diisi", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (nama.value.text.isEmpty()) {
                    Toast.makeText(context, "nama harus diisi", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (judul.value.text.isEmpty()) {
                    Toast.makeText(context, "judul harus diisi", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (waktu_pengembalian.value.text.isEmpty()) {
                    Toast.makeText(context, "waktu pengembalian harus diisi", Toast.LENGTH_SHORT).show()
                    return@Button
                }


                scope.launch {
                    PeminjamanBukuDao.insertAll(item)
                }
                tanggal.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                judul.value = TextFieldValue("")
                waktu_pengembalian.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                tanggal.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                judul.value = TextFieldValue("")
                waktu_pengembalian.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
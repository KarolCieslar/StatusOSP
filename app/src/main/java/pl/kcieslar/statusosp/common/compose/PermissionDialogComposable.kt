package pl.kcieslar.statusosp.common.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pl.kcieslar.statusosp.common.ext.alertDialog
import pl.kcieslar.statusosp.common.ext.textButton

@Composable
fun PermissionDialog(onRequestPermission: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(true) }

    if (showWarningDialog) {
        AlertDialog(
            modifier = Modifier.alertDialog(),
            title = { Text("Potrzebna permisja na powiadomienia") },
            text = { Text("Deskripszyn") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onRequestPermission()
                        showWarningDialog = false
                    },
                    modifier = Modifier.textButton(),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    )
                ) { Text(text = "Request") }
            },
            onDismissRequest = { }
        )
    }
}

@Composable
fun RationaleDialog() {
    var showWarningDialog by remember { mutableStateOf(true) }

    if (showWarningDialog) {
        AlertDialog(
            modifier = Modifier.alertDialog(),
            title = { Text("asdadsdas") },
            text = { Text("go to settings") },
            confirmButton = {
                TextButton(
                    onClick = { showWarningDialog = false },
                    modifier = Modifier.textButton(),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    )
                ) { Text(text = "OK") }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}
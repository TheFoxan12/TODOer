package fr.quarantedeuxmulhouse.tunsinge.todoer.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task(
    name: String,
    checked: Boolean,
    onStateChange: (Boolean) -> Unit,
    onNameChange: (String) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Column (
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (checked) Icons.Default.CheckCircle else Icons.Default.PlayArrow,
                contentDescription = "",
                tint = if (checked) Color(0xFF309709) else Color(0xff970707)
            )
        }
        Column (
            modifier = Modifier
                .weight(5f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                textStyle = if (checked) TextStyle(
                    textDecoration = TextDecoration.LineThrough,
                    fontSize = 20.sp,
                    background = Color(0x00FFFFFF)
                )
                else TextStyle(fontSize = 20.sp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0x00ffffff),
                    unfocusedBorderColor = Color(0x00ffffff)
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight(),
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onStateChange,
            )
            Text(
                if (checked) "Done" else "Todo"
            )
            IconButton(
                onClick = onClose,
                enabled = checked
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    tint = if (!checked) Color(0xFF444444) else Color(0xff970707)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    Column () {
        Task(
            "Tâche d'exemple",
            false,
            {}, {}, {}
        )
        Task(
            "Tâche d'exemple 2",
            true,
            {}, {}, {}
        )
    }
}

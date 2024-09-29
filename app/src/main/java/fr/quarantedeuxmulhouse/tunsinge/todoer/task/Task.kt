package fr.quarantedeuxmulhouse.tunsinge.todoer.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Task(
    name: String,
    checked: Boolean,
    date: Long?,
    onStateChange: (Boolean) -> Unit,
    onNameChange: (String) -> Unit,
    onDateChange: (Long?) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    testEdit: Boolean = false
) {
    var currentName by rememberSaveable { mutableStateOf(name) }
    var soon by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Column (
            modifier = modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CalendarButton(checked, date, onDateChange, { deadline -> soon = deadline },
                modifier = modifier)
        }
        Column (
            modifier = modifier
                .weight(5f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Row (
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = currentName,
                    onValueChange = { if (!checked) currentName = it },
                    textStyle = if (checked) TextStyle(
                        textDecoration = TextDecoration.LineThrough,
                        fontSize = 20.sp,
                        background = Color(0x00FFFFFF)
                    )
                    else {
                        if (soon) {
                            TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        } else {
                            TextStyle(fontSize = 20.sp)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0x00FFFFFF),
                        focusedBorderColor = Color(0x00FFFFFF)
                    )
                )
            }
        }
        if (!checked && currentName != name || testEdit) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxHeight(),
            ) {
                IconButton(
                    onClick = { currentName = name }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Dismiss"
                    )
                }
                IconButton(
                    onClick = { onNameChange(currentName) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Accept"
                    )
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
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
                    contentDescription = "Delete",
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
            null,
            {}, {}, {}, {},
            testEdit = true
        )
        Task(
            "Tâche d'exemple 2",
            true,
            1243546789,
            {}, {}, {}, {}
        )
    }
}

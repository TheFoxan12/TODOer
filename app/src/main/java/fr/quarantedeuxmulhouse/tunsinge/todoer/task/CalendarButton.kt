package fr.quarantedeuxmulhouse.tunsinge.todoer.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarButton(checked: Boolean, date: Long?, onChangeDate: (Long?) -> Unit,
                   updateDeadline: (Boolean) -> Unit,
                   modifier: Modifier = Modifier) {
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val difference = date?.minus(LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli())
    val soon = difference != null && difference < 259200000 // 3 days in milliseconds
    updateDeadline(soon)

    Icon(
        imageVector = if (checked) Icons.Default.CheckCircle
        else {
            if (!soon) Icons.Default.Menu
            else Icons.Default.Warning
        },
        contentDescription = "Status",
        tint = if (checked) Color(0xFF309709)
        else {
            if (!soon) Color(0xFFFFC107)
            else Color(0xff970707)
        }
    )
    IconButton(
        onClick = { showDatePicker = !showDatePicker }
    ) {
        Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = "Select deadline"
        )
    }
    Text(date?.let {
        convertMillisToDate(it)
    } ?: "")

    if (showDatePicker) {
        Popup(
            onDismissRequest = {
                onChangeDate(datePickerState.selectedDateMillis);
                showDatePicker = false },
            alignment = Alignment.TopStart
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .offset(y = 64.dp)
                    .shadow(elevation = 4.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = modifier
                ) {
                    DatePicker(
                        title = { Text("") },
                        state = datePickerState,
                        showModeToggle = false
                    )
                    Button(
                        onClick = {
                            onChangeDate(datePickerState.selectedDateMillis);
                            showDatePicker = false
                        },
                        modifier = modifier.padding(bottom = 10.dp)
                    ) {
                        Text("Ok")
                    }
                }
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM", Locale.getDefault())
    return formatter.format(Date(millis))
}

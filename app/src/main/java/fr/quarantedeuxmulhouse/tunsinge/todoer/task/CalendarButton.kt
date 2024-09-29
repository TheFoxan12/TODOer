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

// "bouton" sous forme de colonne pour choisir une date deadline de la tache, ainsi qu'afficher
// cette date, et une petite icone representant le status de la tache (faite, a faire, urgente)

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

    // icone representant le status de la tache
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

    // text pour afficher la date sous la forme 'jj/mm'
    Text(date?.let {
        convertMillisToDate(it)
    } ?: "")

    // bouton pour afficher la popup de choix de date
    IconButton(
        onClick = { showDatePicker = !showDatePicker }
    ) {
        Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = "Select deadline"
        )
    }

    // si le bouton a ete clique, showDatePicker sera true et on affiche la popup de choix
    if (showDatePicker) {

        // on utilise une popup et un datepicker pour afficher une fenetre de choix de date,
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
                        // on enregistre la date dans le viewmodel, et la base de donnee
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

// fonction pour le formattage de la date pour l'affichage
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM", Locale.getDefault())
    return formatter.format(Date(millis))
}

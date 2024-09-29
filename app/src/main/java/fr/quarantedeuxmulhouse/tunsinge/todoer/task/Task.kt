package fr.quarantedeuxmulhouse.tunsinge.todoer.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// composable task pour afficher une tache dans la liste

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
    val shape = RoundedCornerShape(20.dp)
    Row(
        modifier = modifier
            .padding(PaddingValues(
                horizontal = 10.dp,
                vertical = 10.dp)
            )
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .shadow(
                elevation = 20.dp,
                shape = shape
            )
            .background(
                color = Color.White,
                shape = shape
            )
            .border(
                border = ButtonDefaults.outlinedButtonBorder(),
                shape = shape
            )
    ) {
        // on affiche dans la premier colonne le bouton pour choisir la date
        Column (
            modifier = modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CalendarButton(checked, date, onDateChange, { deadline -> soon = deadline },
                modifier = modifier)
        }

        // la deuxieme colonne affiche le nom de la tache, qui est modifiable
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

                    // losque la value change, on affiche le changement si la tache n'est pas
                    // encore terminee
                    // on n'envoie pas directement le changement dans le viewmodel sinon ca cree des
                    // problemes de mise a jour et empeche la bonne edition
                    onValueChange = { if (!checked) currentName = it },

                    // si la tache est done, on affiche le texte barre
                    textStyle = if (checked) TextStyle(
                        textDecoration = TextDecoration.LineThrough,
                        fontSize = 20.sp,
                        background = Color(0x00FFFFFF)
                    )
                    else {

                        // sinon si la deadline se rapproche on affiche le texte en gras
                        if (soon) {
                            TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        } else {

                            // sinon rien de special
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
        // si jamais on a edite le contenu du nom
        if (!checked && currentName != name || testEdit) {

            // on affiche deux boutons, qui permettent l'enregistrement et l'annulation des
            // modifications
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxHeight(),
            ) {

                // si on clique sur le bouton d'annulation, on reset le nom au nom precedent les
                // modifications
                IconButton(
                    onClick = { currentName = name }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Dismiss"
                    )
                }

                // si on clique sur le bouton de sauvegarde on envoie les modifications au viewmodel
                // grace au lambda
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

        // la derniere colonne affiche le status, done/to-do, ainsi que la case a cocher pour le
        // modifier et le bouton de suppression
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxHeight(),
        ) {

            // case a cocher pour modifier le status
            Checkbox(
                checked = checked,

                // lors d'un changement on en notifie le viewmodel grace au lambda
                onCheckedChange = onStateChange,
            )

            // affichage du status sous forme textuelle
            Text(
                if (checked) "Done" else "Todo"
            )

            // bouton de suppression de la tache
            IconButton(
                // on notifie le viewmodel grace au lambda
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

// preview des taches
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

package fr.quarantedeuxmulhouse.tunsinge.todoer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import fr.quarantedeuxmulhouse.tunsinge.todoer.task.TaskScreen
import fr.quarantedeuxmulhouse.tunsinge.todoer.ui.theme.TODOerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    Column() {
                        Text(
                            modifier = Modifier.padding(padding),
                            text = "TODOer",
                            style = TextStyle(fontSize = 40.sp)
                        )
                        TaskScreen()
                    }
                }
            }
        }
    }
}

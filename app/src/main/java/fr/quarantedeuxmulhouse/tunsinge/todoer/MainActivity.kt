package fr.quarantedeuxmulhouse.tunsinge.todoer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import fr.quarantedeuxmulhouse.tunsinge.todoer.task.TaskScreen
import fr.quarantedeuxmulhouse.tunsinge.todoer.ui.theme.TODOerTheme
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskListViewModel
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskListViewModelFactory

class MainActivity : ComponentActivity() {
    private val taskManager: TaskListViewModel by viewModels {
        TaskListViewModelFactory((application as TodoerApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                taskManager.tasks.value == null
            }
        }
        enableEdgeToEdge()
        setContent {
            TODOerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    Column() {
                        Text(
                            modifier = Modifier
                                .padding(padding)
                                .align(Alignment.CenterHorizontally),
                            text = "TODOer",
                            style = TextStyle(fontSize = 40.sp)
                        )
                        TaskScreen(taskManager = taskManager,
                            modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing))
                    }
                }
            }
        }
    }
}

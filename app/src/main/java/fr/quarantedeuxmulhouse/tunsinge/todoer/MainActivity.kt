package fr.quarantedeuxmulhouse.tunsinge.todoer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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

    @OptIn(ExperimentalMaterial3Api::class)
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
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = colorScheme.background,
                                titleContentColor = colorScheme.primary,
                            ),
                            title = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.todo_icon),
                                        contentDescription = "todo_icon",
                                        Modifier.size(
                                            width = 100.dp,
                                            height = 100.dp
                                        )
                                    )
                                    Text(
                                        "TODOer",
                                        style = TextStyle(fontSize = 40.sp)
                                    )
                                }
                            }
                        )
                    },
                    containerColor = colorScheme.background
                ) { padding ->
                    Column(
                        modifier = Modifier.padding(padding)
                    ) {
                        TaskScreen(
                            modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
                            taskManager = taskManager,
                        )
                    }
                }
            }
        }
    }
}

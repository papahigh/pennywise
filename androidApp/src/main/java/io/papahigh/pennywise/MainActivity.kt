package io.papahigh.pennywise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.papahigh.pennywise.components.TopAppBar
import io.papahigh.pennywise.theme.PennywiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PennywiseTheme {
                Scaffold(
                    topBar = { TopAppBar() },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->

                    Column(
                        modifier =
                            Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                    ) {
                        ElevatedCard(
                            modifier =
                                Modifier
                                    .padding(16.dp)
                                    .fillMaxSize(),
                        ) {
                            Greeting("Android")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PennywiseTheme {
        Greeting("Android")
    }
}

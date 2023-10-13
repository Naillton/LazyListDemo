package com.example.lazylistdemo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazylistdemo.ui.theme.LazyListDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyListDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                val items: Array<String> = arrayOf(
                    "Zezin",
                    "Luizin",
                    "Marquin",
                    "Maikin",
                    "Juinin",
                    "Brunin",
                    "Doentin",
                    "Zezin",
                    "Luizin",
                    "Marquin",
                    "Maikin",
                    "Juinin",
                    "Brunin",
                    "Doentin",
                    "Zezin",
                    "Luizin",
                    "Marquin",
                    "Maikin",
                    "Juinin",
                    "Brunin",
                    "Doentin")
                    MainScreen(items)
                }
            }
        }
    }
}

/**
 * Criando LazyCloumn e LazyRow, com isso, temos listas mais responsivas uma sintaxe melhor,
 * diferente da ListColumn e ListRow que teriamos que definir um scrool, por padrao as Lazys
 * fazem isso!
 */

@Composable
fun MainScreen(array: Array<String>) {
     // acessando contexto local da aplicacao e guardando em uma variavel
    val context = LocalContext.current

    // criando funcao para usar um Toast quando clicar no item da lsta
    val onTouch = {it: String ->
        Toast.makeText(
            context,
            it,
            Toast.LENGTH_LONG
        ).show()
    }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        /*LazyColumn {
            items(array) {model ->
                CardElement(text = model, onClick = onTouch)
            }
        }*/
        LazyRow {
            items(array) {
                CardElement(text = it, onClick = onTouch)
            }
        }

    }
}

@Composable
private fun CardElement(text: String, onClick: (String) -> Unit) {
    Card(
        Modifier
            .size(200.dp, 120.dp)
            .padding(20.dp)
            .clickable { onClick(text) },
        border = BorderStroke(2.dp, Color.Blue),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Cyan),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "List item: $text",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(5.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LazyListDemoTheme {
        val items: Array<String> = arrayOf(
            "Zezin",
            "Luizin",
            "Marquin",
            "Maikin",
            "Juinin",
            "Brunin",
            "Doentin",
            "Zezin",
            "Luizin",
            "Marquin",
            "Maikin",
            "Juinin",
            "Brunin",
            "Doentin",
            "Zezin",
            "Luizin",
            "Marquin",
            "Maikin",
            "Juinin",
            "Brunin",
            "Doentin")
        MainScreen(items)
    }
}
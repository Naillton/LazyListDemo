package com.example.lazylistdemo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazylistdemo.ui.theme.LazyListDemoTheme
import kotlinx.coroutines.launch
import java.util.Collections

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyListDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                val items: MutableList<String> = arrayListOf(
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(array: MutableList<String>) {
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

    // agrupando itens
    // val groupedItems = array.groupBy { it.substringBefore(' ') }
    val ordenedItems = array.sorted()

    // criando estado da lista e corotinas para acessar posicoes diferentes da lista
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val displayButton = listState.firstVisibleItemIndex > 5
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        /*LazyColumn {
        groupedItems.forEach { (names: String) ->
                // usando stickyHeader para criar um cabecalho de elementos
                stickyHeader {
                    Text(
                        text = names,
                        color = Color.White,
                        modifier = Modifier
                            .background(Color.Gray)
                            .padding(5.dp)
                            .fillMaxWidth()
                    )
                }
            items(array) {model ->
                CardElement(text = model, onClick = onTouch)
            }
        }*/
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyRow(
                state = listState
            ) {
                items(ordenedItems) {
                    CardElement(text = it, onClick = onTouch)
                }
            }
            // criando visibilidade de animacao usando o display button para que o botao
            // apareca apos o determiando numero de elementos
            AnimatedVisibility(
                visible = displayButton) {
                    // botao criado para voltar ao topo da lista
                    OutlinedButton(onClick = {
                        coroutineScope.launch {
                            listState.scrollToItem(0)
                        }
                    },
                        border = BorderStroke(1.dp, Color.Gray),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.DarkGray),
                        modifier = Modifier.padding(5.dp)) {

                        Text(
                            text = "top"
                        )
                    }
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
        val items: MutableList<String> = arrayListOf(
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
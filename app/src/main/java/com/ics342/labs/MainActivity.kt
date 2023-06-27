package com.ics342.labs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ics342.labs.data.DataItem
import com.ics342.labs.ui.theme.LabsTheme

private val dataItems = listOf(
    DataItem(1, "Item 1", "Description 1"),
    DataItem(2, "Item 2", "Description 2"),
    DataItem(3, "Item 3", "Description 3"),
    DataItem(4, "Item 4", "Description 4"),
    DataItem(5, "Item 5", "Description 5"),
    DataItem(6, "Item 6", "Description 6"),
    DataItem(7, "Item 7", "Description 7"),
    DataItem(8, "Item 8", "Description 8"),
    DataItem(9, "Item 9", "Description 9"),
    DataItem(10, "Item 10", "Description 10"),
    DataItem(11, "Item 11", "Description 11"),
    DataItem(12, "Item 12", "Description 12"),
    DataItem(13, "Item 13", "Description 13"),
    DataItem(14, "Item 14", "Description 14"),
    DataItem(15, "Item 15", "Description 15"),
    DataItem(16, "Item 16", "Description 16"),
    DataItem(17, "Item 17", "Description 17"),
    DataItem(18, "Item 18", "Description 18"),
    DataItem(19, "Item 19", "Description 19"),
    DataItem(20, "Item 20", "Description 20"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DataItemList(dataItems = dataItems)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun DataItemView(dataItem: DataItem, onItemClick: () -> Unit) {
    /* Create the view for the data item her. */

    val showDialog = remember { mutableStateOf(false) }

    Column {
        Text(text = "ID: ${dataItem.id}")
        Text(text = "Name: ${dataItem.name}")
        Text(text = "Description: ${dataItem.description}")

        Button(onClick = {
            onItemClick()
        }) {
            Text(text = "Show Dialog")
        }
    }
}


@Composable
fun DataItemList(dataItems: List<DataItem>) {
    var selectedItem by remember { mutableStateOf<DataItem?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    /* Create the list here. This function will call DataItemView() */
    LazyColumn {
        items(dataItems) { dataItem ->
            DataItemView(dataItem = dataItem) {
                selectedItem = dataItem
                showDialog = true
            }
        }
    }

    if (selectedItem != null) {
        AlertDialog(
            onDismissRequest = {
                selectedItem = null
                showDialog = false
            },
            title = { Text(text = selectedItem?.name ?: "") },
            text = { Text(text = selectedItem?.description ?: "") },
            confirmButton = {
                Button(onClick = {
                    selectedItem = null
                    showDialog = false
                }) {
                    Text(text = "Okay")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabsTheme {
        Greeting("Android")
    }
}


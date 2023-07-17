package com.ics342.labs

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ics342.labs.ui.theme.LabsTheme
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonData = loadData(resources)
        val data = dataFromJsonString(jsonData)
        setContent {
            LabsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    /*
                    Display the items from the Json file in a LazyColumn
                     */
                    DisplayData(data)
                }
            }
        }
    }
}

private fun loadData(resources: Resources): String {
    return resources
        .openRawResource(R.raw.data)
        .bufferedReader()
        .use { it.readText() }
}

private fun dataFromJsonString(json: String): List<Person> {
    val moshi: Moshi = Moshi.Builder().build()
    val listType = Types.newParameterizedType(List::class.java, Person::class.java)
    val jsonAdapter: JsonAdapter<List<Person>> = moshi.adapter(listType)
    return jsonAdapter.fromJson(json)?: emptyList()
}

@Composable
fun DisplayData(dataList: List<Person>) {
    LazyColumn {
        items(dataList) { person ->
            Text("ID: ${person.id}")
            Text("First Name: ${person.firstName}")
            Text("Last Name: ${person.lastName}")
            Text("Age: ${person.age}")
        }
    }
}

@Preview
@Composable
fun DisplayDataPreview() {
    val sampleData = listOf(
        Person(1, "Valeria", "Hope", 31),
        Person(2, "Oscar", "Jenkins", 24),
        Person(3, "Stella", "Jones", 58),
        Person(4, "Giovanni", "Green", 72),
        Person(5, "Josie", "Brown", 65),
        Person(6, "Zion", "Thomas", 34),
        Person(7, "Remington", "Nora", 87),
        Person(8, "Theo", "Melenia", 48),
        Person(9, "Brantley", "Johnson", 39),
        Person(10, "Atlas", "Jenkins", 41)
    )
    DisplayData(sampleData)
}
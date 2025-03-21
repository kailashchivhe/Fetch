package com.kailash.fetch.ui.components.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kailash.fetch.R
import com.kailash.fetch.data.model.Item

@Composable
fun ItemListComponent(modifier: Modifier = Modifier, itemsMap: Map<Int, List<Item>>) {
    LazyColumn(modifier = modifier.padding(8.dp)) {
        itemsMap.forEach { (header, itemList) ->
            item {
                ExpandableListItem(header = header.toString(), innerItems = itemList)
            }
        }
    }
}

@Composable
private fun ExpandableListItem(header: String, innerItems: List<Item>) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${LocalContext.current.getString(R.string.expandable_header)} $header",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (expanded) {
                    LocalContext.current.getString(R.string.expandable_content_description_collapse)
                } else {
                    LocalContext.current.getString(
                        R.string.expandable_content_description_expand
                    )
                }
            )
        }

        if (expanded) {
            InnerListSection(innerItems = innerItems)
        }
        HorizontalDivider()
    }
}

@Composable
private fun InnerListSection(innerItems: List<Item>) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Column {
                innerItems.forEach { item ->
                    Text(
                        text = "${item.id}. ${item.name}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemListComponentPreview(){
    ItemListComponent(modifier = Modifier, itemsMap = emptyMap())
}
package com.civilunits.canada.ui.civiltools

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.civilunits.canada.data.units.RebarInfo

private val COL_WIDTH_US = 56.dp
private val COL_WIDTH_METRIC = 64.dp
private val COL_WIDTH_DIA_IN = 72.dp
private val COL_WIDTH_DIA_MM = 72.dp
private val COL_WIDTH_AREA_IN = 80.dp
private val COL_WIDTH_AREA_MM = 84.dp

@Composable
fun RebarScreen(
    viewModel: RebarViewModel = hiltViewModel()
) {
    val bars by viewModel.bars.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Rebar Reference",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.onSearchQueryChange(it) },
            label = { Text("Search by bar # or metric size") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        val horizontalScrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(horizontalScrollState)
        ) {
            // Table header
            RebarHeaderRow()
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primary)

            // Table body
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = bars,
                    key = { it.usBarNumber }
                ) { bar ->
                    RebarDataRow(bar = bar)
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun RebarHeaderRow() {
    Row(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        HeaderCell(text = "US #", width = COL_WIDTH_US)
        HeaderCell(text = "Metric", width = COL_WIDTH_METRIC)
        HeaderCell(text = "Dia (in)", width = COL_WIDTH_DIA_IN)
        HeaderCell(text = "Dia (mm)", width = COL_WIDTH_DIA_MM)
        HeaderCell(text = "Area (in\u00B2)", width = COL_WIDTH_AREA_IN)
        HeaderCell(text = "Area (mm\u00B2)", width = COL_WIDTH_AREA_MM)
    }
}

@Composable
private fun HeaderCell(text: String, width: Dp) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.width(width)
    )
}

@Composable
private fun RebarDataRow(bar: RebarInfo) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        DataCell(text = "#${bar.usBarNumber}", width = COL_WIDTH_US)
        DataCell(text = "${bar.metricSize}", width = COL_WIDTH_METRIC)
        DataCell(text = "${bar.diameterIn}", width = COL_WIDTH_DIA_IN)
        DataCell(text = "${bar.diameterMm}", width = COL_WIDTH_DIA_MM)
        DataCell(text = "${bar.areaIn2}", width = COL_WIDTH_AREA_IN)
        DataCell(text = "${bar.areaMm2}", width = COL_WIDTH_AREA_MM)
    }
}

@Composable
private fun DataCell(text: String, width: Dp) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier.width(width)
    )
}

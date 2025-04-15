package com.polina.android.weather.app.presentation.main.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polina.android.weather.app.R
import com.polina.android.weather.app.presentation.model.City

@Composable
fun CityPager(
    cities: List<City>,
    selectedCity: City,
    onCitySelected: (City) -> Unit,
    modifier: Modifier = Modifier
) {
    val initialPage = cities.indexOfFirst { it.name == selectedCity.name }.takeIf { it >= 0 } ?: 0
    val contentPadding = PaddingValues(horizontal = 16.dp)

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { cities.size }
    )

    LaunchedEffect(pagerState.settledPage) {
        if (pagerState.settledPage >= 0 && pagerState.settledPage < cities.size) {
            val newCity = cities[pagerState.settledPage]
            if (newCity.name != selectedCity.name) {
                onCitySelected(newCity)
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        pageSpacing = 8.dp,
        contentPadding = contentPadding,
        modifier = modifier.fillMaxWidth()
    ) { page ->
        CityCard(
            backgroundImg = painterResource(cities[page].iconRes),
            cityName = cities[page].name,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CityPagerPreview() {

    val city = City(
        name = "Warsaw",
        iconRes = R.drawable.card_1
    )
    val city2 = City(
        name = "Cracow",
        iconRes = R.drawable.card_1
    )

    CityPager(cities = listOf(city, city2), selectedCity = city, {})
}
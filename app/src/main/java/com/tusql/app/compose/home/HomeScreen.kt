/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tusql.app.compose.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tusql.app.R
import com.tusql.app.compose.garden.GardenScreen
import com.tusql.app.compose.plantlist.PlantListScreen
import com.tusql.app.data.Plant
import com.tusql.app.ui.TuSQLTheme
import com.tusql.app.viewmodels.PlantListViewModel
import kotlinx.coroutines.launch

enum class TuSQLPage(
    @StringRes val titleResId: Int,
    @DrawableRes val drawableResId: Int
) {
    MY_GARDEN(R.string.my_garden_title, R.drawable.ic_my_garden_active),
    PLANT_LIST(R.string.plant_list_title, R.drawable.ic_plant_list_active)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onPlantClick: (Plant) -> Unit = {},
    onDatabaseClick: () -> Unit = {},
    viewModel: PlantListViewModel = hiltViewModel(),
    pages: Array<TuSQLPage> = TuSQLPage.values()
) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(
                pagerState = pagerState,
                onFilterClick = { viewModel.updateData() },
                onDatabaseClick = onDatabaseClick,
                scrollBehavior = scrollBehavior
            )
        }
    ) { contentPadding ->
        HomePagerScreen(
            onPlantClick = onPlantClick,
            pagerState = pagerState,
            pages = pages,
            Modifier.padding(top = contentPadding.calculateTopPadding())
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePagerScreen(
    onPlantClick: (Plant) -> Unit,
    pagerState: PagerState,
    pages: Array<TuSQLPage>,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        val coroutineScope = rememberCoroutineScope()

        // Tab Row
        TabRow(
            selectedTabIndex = pagerState.currentPage
        ) {
            pages.forEachIndexed { index, page ->
                val title = stringResource(id = page.titleResId)
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = title) },
                    icon = {
                        Icon(
                            painter = painterResource(id = page.drawableResId),
                            contentDescription = title
                        )
                    },
                    unselectedContentColor = MaterialTheme.colorScheme.secondary
                )
            }
        }

        // Pages
        HorizontalPager(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { index ->
            when (pages[index]) {
                TuSQLPage.MY_GARDEN -> {
                    GardenScreen(
                        Modifier.fillMaxSize(),
                        onAddPlantClick = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(TuSQLPage.PLANT_LIST.ordinal)
                            }
                        },
                        onPlantClick = {
                            onPlantClick(it.plant)
                        })
                }

                TuSQLPage.PLANT_LIST -> {
                    PlantListScreen(
                        onPlantClick = onPlantClick,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar(
    pagerState: PagerState,
    onFilterClick: () -> Unit,
    onDatabaseClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier,
        actions = {
            IconButton(onClick = onDatabaseClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter_list_24dp), // Temporarily use filter icon or search for a better one
                    contentDescription = "Ver Tablas",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            if (pagerState.currentPage == TuSQLPage.PLANT_LIST.ordinal) {
                IconButton(onClick = onFilterClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter_list_24dp),
                        contentDescription = stringResource(
                            id = R.string.menu_filter_by_grow_zone
                        )
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun HomeScreenPreview() {
    TuSQLTheme {
        val pages = TuSQLPage.values()
        HomePagerScreen(
            onPlantClick = {},
            pagerState = rememberPagerState(pageCount = { pages.size }),
            pages = pages
        )
    }
}

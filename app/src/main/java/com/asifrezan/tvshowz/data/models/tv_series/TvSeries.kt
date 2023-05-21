package com.asifrezan.tvshowz.data.models.tv_series

data class TvSeries(
    val page: Int,
    val results: List<TvSeriesList>,
    val total_pages: Int,
    val total_results: Int
)
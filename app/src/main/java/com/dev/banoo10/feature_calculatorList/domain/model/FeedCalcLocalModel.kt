package com.dev.banoo10.feature_calculatorList.domain.model

data class FeedCalcLocalModel(
    val id: Int,
    val feedCalc_name: String,
    val startAt: String,
    val berat_tebar: Float,
    val dosis: Float,
    val species: String,
    val createdAt: String,
    val updatedAt: String,
    val uuid: String
)

data class SchedCalcLocalModel(
    val id: Int,
    val day: Int,
    val pakan_harian: Float,
    val penyerapan: Float,
    val berat_akhir: Float,
    val feedCalcId: String
)

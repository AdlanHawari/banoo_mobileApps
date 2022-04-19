package com.dev.banoo10.feature_calculatorList.data.remote.dto.add_calculator

import com.dev.banoo10.feature_calculatorList.util.DateAsLongSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*
import kotlin.collections.ArrayList

@Serializable
data class AddCalcResponse(
    val id: Int,
    val feedcalc_name: String,
    val startAt: String,
    val berat_tebar: Float,
    val dosis: Float,
    val species: String,
    val userId: String,
    val updatedAt: String,
    val uuid: String,
    val createdAt: String,
    val record: ArrayList<Record>




    )

package com.dev.banoo10.feature_calculatorList.data.remote.dto.add_calculator

import com.dev.banoo10.feature_calculatorList.domain.model.CalcElement
import com.dev.banoo10.feature_calculatorList.util.DateAsLongSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList



@Serializable
data class AddCalcRequest(
    @Serializable(with = DateAsLongSerializer::class)
    val startAt: Date,

    val feedcalc_name: String,
    val species: String,
    val berat_tebar: Float,
    val dosis: Float,
    val data: ArrayList<CalcElement>,

)

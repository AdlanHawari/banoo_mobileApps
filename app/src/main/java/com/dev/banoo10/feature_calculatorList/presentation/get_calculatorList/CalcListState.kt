package com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList

import com.dev.banoo10.feature_calculatorList.data.remote.dto.get_calculatorList.GetCalcListResponse

data class CalcListState(
    val isLoading: Boolean = false,
    val calculators: List<GetCalcListResponse> = emptyList(),

)

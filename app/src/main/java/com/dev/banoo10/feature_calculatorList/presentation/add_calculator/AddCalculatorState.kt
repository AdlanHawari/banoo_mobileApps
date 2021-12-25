package com.dev.banoo10.feature_calculatorList.presentation.add_calculator

data class AddCalculatorState (
    val year: Int,
    val month: Int,
    val day: Int,
    val date: String = "",
    val isLoading: Boolean = false,

    val cult_name: AddCalculatorItemComponent = AddCalculatorItemComponent(
        label = "Nama budidaya",
        placeholder = "",
        value = "",
        options = emptyList()
    ),

    val spesies: AddCalculatorItemComponent = AddCalculatorItemComponent(
        label = "Spesies",
        placeholder = "Pilih spesies",
        value = "",
        options = listOf("Nila merah")
    ),

    val berat_tebar: AddCalculatorItemComponent = AddCalculatorItemComponent(
        label = "Total berat tebar",
        placeholder = "",
        value = "",
        options = emptyList()
    ),

    val dosis: AddCalculatorItemComponent = AddCalculatorItemComponent(
        label = "Dosis",
        placeholder = "Pilih dosis",
        value = "",
        options = listOf("3 %", "5 %")
    ),

    val jadwal: AddCalculatorItemComponent = AddCalculatorItemComponent(
        label = "Jadwal tebar",
        placeholder = "",
        value = "",
        options = emptyList()
    )


)

data class AddCalculatorItemComponent(
    val label: String,
    val placeholder: String,
    val value: String,
    val options: List<String>
)
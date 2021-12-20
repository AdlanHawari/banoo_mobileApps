package com.dev.banoo10.feature_calculatorList.domain.use_case.add_calculator

import android.util.Log
import com.dev.banoo10.feature_calculatorList.domain.model.CalcElement
import com.dev.banoo10.feature_calculatorList.domain.model.NewCalculationModel
import java.util.ArrayList
import javax.inject.Inject

class AddCalculatorUseCase @Inject constructor(

) {

    operator fun invoke(newCalculationModel: NewCalculationModel) {
        Log.e("input", newCalculationModel.toString())

        val persenAbsorb = 0.7f

        val arrayData = ArrayList<CalcElement>(90)
//        Log.e("panjang",arrayData.)
        var a:Float = newCalculationModel.dosis * newCalculationModel.berat_tebar
        var b:Float = persenAbsorb * a
        var c:Float =  newCalculationModel.berat_tebar + b
            (0 until 90).forEach{ index ->
//            Log.e("ini", index.toString())
                if (index>0){
                    a = newCalculationModel.dosis * arrayData[index - 1].final_weight
                    b = persenAbsorb * a
                    c = arrayData[index - 1].final_weight + b
                }
                arrayData.add(CalcElement(
                    pakan_harian = a,
                    absorp = b,
                    final_weight = c
                ))


//
//                if (index<=0){
//                    arrayData.add(CalcElement(
//                        pakan_harian = a,
//                        absorp = b,
//                        final_weight = c
//                    ))
//                }
//                else{
//                    a = newCalculationModel.dosis * arrayData[index - 1].final_weight
//                    b = persenAbsorb * a
//                    c = arrayData[index - 1].final_weight + b
//
//                }



//            if (index<=0){
//                arrayData[index] = CalcElement(
//                    newCalculationModel.dosis * newCalculationModel.berat_tebar,
//                    persenAbsorb * arrayData[index].pakan_harian,
//                    newCalculationModel.berat_tebar + arrayData[index].absorp
//                )
//
//            }
//            else{
//                arrayData[index] = CalcElement(
//                    newCalculationModel.dosis * arrayData[index - 1].final_weight,
//                    persenAbsorb * arrayData[index].pakan_harian,
//                    arrayData[index - 1].final_weight + arrayData[index].absorp
//                )
//            }
            Log.e("data $index", arrayData[index].toString())

        }

    }
}
package com.jetpackprojects.simpleconvert.volume



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.jetpackprojects.simpleconvert.R
import java.math.RoundingMode


@Composable
fun VolumeScreen() {
    val inputOptions = listOf(
        "liter [L,l]",
        "milliliter",
        "gallon (US)[gal]",
        "quart (US)[qt]",
        "pint (US)[pt]",
        "cup (US)",
        "tablespoon (US)",
        "teaspoon (US)",
        "millimeter³ [mm³]",
        "centimeter³ [cm³]",
        "meter³ [m³]",
        "inch³ [in³]",
        "foot³ [ft³]",
        "yard³ [yd³]",
        "barrel oil [bbl]"
    )
    val outputOptions = listOf(
        "liter [L,l]",
        "milliliter",
        "gallon (US)[gal]",
        "quart (US)[qt]",
        "pint (US)[pt]",
        "cup (US)",
        "tablespoon (US)",
        "teaspoon (US)",
        "millimeter³ [mm³]",
        "centimeter³ [cm³]",
        "meter³ [m³]",
        "inch³ [in³]",
        "foot³ [ft³]",
        "yard³ [yd³]",
        "barrel oil [bbl]"
    )
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    var inputSelectedOptionText by remember { mutableStateOf(inputOptions[0]) }
    var outputSelectedOptionText by remember { mutableStateOf(outputOptions[0]) }
    var inputConversionFactor by remember { mutableDoubleStateOf(1.00) }
    var outputConversionFactor by remember { mutableDoubleStateOf(1.00) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    val inputIcon = if (inputExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    val outputIcon = if (outputExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    fun unitConvert() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        when (inputSelectedOptionText) {
            "liter [L,l]"-> inputConversionFactor=0.001
            "milliliter"-> inputConversionFactor=1.00
            "gallon (US)[gal]"-> inputConversionFactor=0.0002641721
            "quart (US)[qt]"-> inputConversionFactor=0.0010566882
            "pint (US)[pt]"-> inputConversionFactor=0.0021133764
            "cup (US)"-> inputConversionFactor=0.0042267528
            "tablespoon (US)"-> inputConversionFactor=0.0676280454
            "teaspoon (US)"-> inputConversionFactor=0.2028841362
            "millimeter³ [mm³]"-> inputConversionFactor=10000.00
            "centimeter³ [cm³]"-> inputConversionFactor=1.00
            "meter³ [m³]"-> inputConversionFactor=0.000001
            "inch³ [in³]"-> inputConversionFactor=0.0610237441
            "foot³ [ft³]"-> inputConversionFactor=0.0000353147
            "yard² [yd²]"-> inputConversionFactor=0.000001308
            "barrel oil [bbl]"-> inputConversionFactor=0.0000062898
        }
        when (outputSelectedOptionText) {
            "liter [L,l]"-> outputConversionFactor=0.001
            "milliliter"-> outputConversionFactor=1.00
            "gallon (US)[gal]"-> outputConversionFactor=0.0002641721
            "quart (US)[qt]"-> outputConversionFactor=0.0010566882
            "pint (US)[pt]"-> outputConversionFactor=0.0021133764
            "cup (US)"-> outputConversionFactor=0.0042267528
            "tablespoon (US)"-> outputConversionFactor=0.0676280454
            "teaspoon (US)"-> outputConversionFactor=0.2028841362
            "millimeter³ [mm³]"-> outputConversionFactor=10000.00
            "centimeter³ [cm³]"-> outputConversionFactor=1.00
            "meter³ [m³]"-> outputConversionFactor=0.000001
            "inch³ [in³]"-> outputConversionFactor=0.0610237441
            "foot³ [ft³]"-> outputConversionFactor=0.0000353147
            "yard² [yd²]"-> outputConversionFactor=0.000001308
            "barrel oil [bbl]"-> outputConversionFactor=0.0000062898
        }

        val result = (inputValueDouble / inputConversionFactor) * outputConversionFactor
        outputValue = result.toBigDecimal().setScale(10, RoundingMode.HALF_EVEN).toPlainString()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //1
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Convert Volume", fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround

            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "From", fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "To", fontSize = 20.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f), value = inputValue, onValueChange = {
                        inputValue = it
                        unitConvert()

                    }, keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done
                    )
                )
                // Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    painter = painterResource(R.drawable.equal_24px), contentDescription = "length"
                )

                OutlinedTextField(modifier = Modifier.weight(1f),
                    value = outputValue,
                    readOnly = true,
                    onValueChange = { outputValue = it })
            }

            //randul 2

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                {
                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                        readOnly = true,
                        value = inputSelectedOptionText,
                        onValueChange = { inputSelectedOptionText = it },
                        trailingIcon = {
                            Icon(imageVector = inputIcon,
                                contentDescription = "menu",
                                Modifier.clickable { inputExpanded = !inputExpanded })
                        })

                    DropdownMenu(
                        expanded = inputExpanded,
                        onDismissRequest = { inputExpanded = false },
                        modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })

                    ) {
                        inputOptions.forEachIndexed { index, label ->
                            if (index != 0) {
                                Divider()
                            }
                            DropdownMenuItem(text = { Text(text = label) }, onClick = {
                                inputSelectedOptionText = label
                                inputExpanded = false
                                unitConvert()
                            })
                        }
                    }
                }

                Spacer(modifier = Modifier.width(24.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        value = outputSelectedOptionText,
                        onValueChange = { outputSelectedOptionText = it },
                        trailingIcon = {
                            Icon(imageVector = outputIcon,
                                contentDescription = "menu",
                                Modifier.clickable { outputExpanded = !outputExpanded })
                        })

                    DropdownMenu(
                        expanded = outputExpanded,
                        onDismissRequest = { outputExpanded = false },
                        modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })

                    ) {
                        outputOptions.forEachIndexed { index, label ->
                            if (index != 0) {
                                Divider()
                            }
                            DropdownMenuItem(text = { Text(text = label) }, onClick = {
                                outputSelectedOptionText = label
                                outputExpanded = false
                                unitConvert()
                            })
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        ElevatedButton(modifier = Modifier.padding(10.dp),

            onClick = {
                inputValue = ""
                outputValue = ""
                inputSelectedOptionText = inputOptions[2]
                outputSelectedOptionText = outputOptions[2]
            }) {
            Text(
                modifier = Modifier.padding(15.dp), text = "RESET", fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VolumeScreen()
}
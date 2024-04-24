package com.jetpackprojects.simpleconvert.weight

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
fun WeightScreen() {
  val inputOptions = listOf(
    "carat [car,ct]",
    "ton(metric) [T]",
    "ton(imperial) [LT]",
    "ton(USA) [ST]",
    "kilogram [kg]",
    "gram [g]",
    "milligram [mg]",
    "microgram [µg]",
    "pound [lb]",
    "ounce [oz]",
    "stone UK [st]",
    "stone US [st]"

  )
  val outputOptions = listOf(
    "carat [car,ct]",
    "ton(metric) [T]",
    "ton(imperial) [LT]",
    "ton(USA) [ST]",
    "kilogram [kg]",
    "gram [g]",
    "milligram [mg]",
    "microgram [µg]",
    "pound [lb]",
    "ounce [oz]",
    "stone UK [st]",
    "stone US [st]"
  )
  var inputValue by remember { mutableStateOf("") }
  var outputValue by remember { mutableStateOf("") }
  var inputExpanded by remember { mutableStateOf(false) }
  var outputExpanded by remember { mutableStateOf(false) }
  var inputSelectedOptionText by remember { mutableStateOf(inputOptions[4]) }
  var outputSelectedOptionText by remember { mutableStateOf(outputOptions[4]) }
  val inputConversionFactor = remember { mutableStateOf(1.0) }
  val outputConversionFactor = remember { mutableStateOf(1.0) }
  var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
  var newtonResult by remember { mutableStateOf("") }
  var newtonFactor by remember { mutableStateOf(1.0) }

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
    val result =
      (inputValueDouble * inputConversionFactor.value * 100.0 / outputConversionFactor.value) / 100.0
    outputValue = result.toBigDecimal().setScale(10, RoundingMode.HALF_EVEN).toPlainString()
  }

  fun convertToNewton() {
    val inputValDouble = inputValue.toDoubleOrNull() ?: 0.0
    when (inputSelectedOptionText) {
      "carat [car,ct]" -> newtonFactor = 0.00196133
      "ton(metric) [T]" -> newtonFactor = 9806.65
      "ton(imperial) [LT]" -> newtonFactor = 9964.01642
      "ton(USA) [ST]" -> newtonFactor = 8896.44323
      "kilogram [kg]" -> newtonFactor = 9.80665
      "gram [g]" -> newtonFactor = 0.00980665
      "milligram [mg]" -> newtonFactor = 0.00000980665
      "microgram [µg]" -> newtonFactor = 9.80665e-9
      "pound [lb]" -> newtonFactor = 4.44822162
      "ounce [oz]" -> newtonFactor = 0.278013851
      "stone UK [st]" -> newtonFactor = 62.277324676875104
      "stone US [st]" -> newtonFactor = 55.602770190756
    }
    val result = inputValDouble * newtonFactor
    newtonResult = result.toBigDecimal().setScale(10, RoundingMode.HALF_EVEN).toPlainString()

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
        text = "Convert Mass",
        fontSize = 25.sp
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
            text = "From",
            fontSize = 20.sp
          )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Row(
          modifier = Modifier.weight(1f),
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "To",
            fontSize = 20.sp
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
          modifier = Modifier
            .weight(1f),
          value = inputValue,
          onValueChange = {
            inputValue = it
            unitConvert()
            convertToNewton()

          },
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
          )
        )
        // Spacer(modifier = Modifier.width(5.dp))
        Icon(
          painter = painterResource(R.drawable.equal_24px),
          contentDescription = "length"
        )

        OutlinedTextField(
          modifier = Modifier
            .weight(1f),
          value = outputValue,
          readOnly = true,
          onValueChange = { outputValue = it }
        )
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
          OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            readOnly = true,
            value = inputSelectedOptionText,
            onValueChange = { inputSelectedOptionText = it },
            trailingIcon = {
              Icon(
                imageVector = inputIcon,
                contentDescription = "menu",
                Modifier.clickable { inputExpanded = !inputExpanded })
            }
          )

          DropdownMenu(
            expanded = inputExpanded,
            onDismissRequest = { inputExpanded = false },
            modifier = Modifier
              .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })

          ) {
            inputOptions.forEachIndexed { index, label ->
              if (index != 0) {
                Divider()
              }
              DropdownMenuItem(
                text = { Text(text = label) },
                onClick = {
                  inputSelectedOptionText = label
                  inputExpanded = false
                  when (label) {
                    "carat [car,ct]" -> inputConversionFactor.value = 0.2
                    "ton(metric) [T]" -> inputConversionFactor.value = 1000000.0
                    "ton(imperial) [LT]" -> inputConversionFactor.value =1016046.9088
                    "ton(USA) [ST]" -> inputConversionFactor.value = 907184.74
                    "kilogram [kg]" -> inputConversionFactor.value = 1000.0
                    "gram [g]" -> inputConversionFactor.value = 1.0
                    "milligram [mg]" -> inputConversionFactor.value = 0.001
                    "microgram [µg]" -> inputConversionFactor.value = 0.000001
                    "pound [lb]" -> inputConversionFactor.value = 453.59237
                    "ounce [oz]" -> inputConversionFactor.value = 28.349523125
                    "stone UK [st]" -> inputConversionFactor.value = 6350.29318
                    "stone US [st]" -> inputConversionFactor.value = 5669.904625
                  }
                  unitConvert()
                  convertToNewton()
                }
              )
            }
          }
        }

        Spacer(modifier = Modifier.width(24.dp))

        Box(
          modifier = Modifier
              .fillMaxWidth()
              .weight(1f)
        )
        {
          OutlinedTextField(
            modifier = Modifier
              .fillMaxWidth(),
            readOnly = true,
            value = outputSelectedOptionText,
            onValueChange = { outputSelectedOptionText = it },
            trailingIcon = {
              Icon(
                imageVector = outputIcon,
                contentDescription = "menu",
                Modifier.clickable { outputExpanded = !outputExpanded })
            }
          )

          DropdownMenu(
            expanded = outputExpanded,
            onDismissRequest = { outputExpanded = false },
            modifier = Modifier
              .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })

          ) {
            outputOptions.forEachIndexed { index, label ->
              if (index != 0) {
                Divider()
              }
              DropdownMenuItem(
                text = { Text(text = label) },
                onClick = {
                  outputSelectedOptionText = label
                  outputExpanded = false
                  when (label) {
                    "carat [car,ct]" -> outputConversionFactor.value = 0.2
                    "ton(metric) [T]" -> outputConversionFactor.value =1000000.0
                    "ton(imperial) [LT]" -> outputConversionFactor.value =1016046.9088
                    "ton(USA) [ST]" -> outputConversionFactor.value = 907184.74
                    "kilogram [kg]" -> outputConversionFactor.value = 1000.0
                    "gram [g]" -> outputConversionFactor.value = 1.0
                    "milligram [mg]" -> outputConversionFactor.value = 0.001
                    "microgram [µg]" -> outputConversionFactor.value = 0.000001
                    "pound [lb]" -> outputConversionFactor.value = 453.59237
                    "ounce [oz]" -> outputConversionFactor.value = 28.349523125
                    "stone UK [st]" -> outputConversionFactor.value = 6350.29318
                    "stone US [st]" -> outputConversionFactor.value =5669.904625
                  }
                  unitConvert()
                }
              )
            }
          }
        }
      }
    }
    Spacer(modifier = Modifier.height(16.dp))

    ElevatedButton(
      modifier = Modifier
        .padding(10.dp),
      onClick = {
        inputValue = ""
        outputValue = ""
        newtonResult = ""
        inputSelectedOptionText = inputOptions[5]
        outputSelectedOptionText = outputOptions[5]

      }) {
      Text(
        modifier = Modifier
          .padding(15.dp),
        text = "RESET",
        fontSize = 20.sp
      )
    }

//newtons calculator
    Row(
      modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Conversion to Weight",
            fontSize = 25.sp
        )
    }
      Row(
          modifier = Modifier
              .fillMaxWidth()
              .padding(10.dp),
          horizontalArrangement = Arrangement.Center
      ) {
          Text(
              text = "For Earth gravity of g₀ = 9.80665m/s² ",
              fontSize = 15.sp
          )
      }
    Row(
      modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp)
    ) {


      OutlinedTextField(
        modifier = Modifier
          .fillMaxWidth(),
        readOnly = true,
        value = "$newtonResult Newtons",
        onValueChange = {
          newtonResult = "$it  Newtons"
         // convertToNewton()
        })
    }


  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  WeightScreen()
}
package com.jetpackprojects.simpleconvert.temperature

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


@Composable
fun TemperatureScreen() {
  val inputOptions = listOf(
    "Celsius [ᵒC]",
    "Fahrenheit [ᵒF]",
    "Kelvin"
  )
  val outputOptions = listOf(
    "Celsius [ᵒC]",
    "Fahrenheit [ᵒF]",
    "Kelvin"
  )
  var inputValue by remember { mutableStateOf("") }
  var outputValue by remember { mutableStateOf("") }
  var inputExpanded by remember { mutableStateOf(false) }
  var outputExpanded by remember { mutableStateOf(false) }
  var inputSelectedOptionText by remember { mutableStateOf(inputOptions[2]) }
  var outputSelectedOptionText by remember { mutableStateOf(outputOptions[2]) }
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
    if (inputSelectedOptionText == outputSelectedOptionText) {
      outputValue = inputValueDouble.toString()
    } else if (inputSelectedOptionText == "Celsius [ᵒC]" && outputSelectedOptionText == "Fahrenheit [ᵒF]") {
      outputValue = ((inputValueDouble * (9.0 / 5.0)) + 32.0).toString()
    } else if (inputSelectedOptionText == "Celsius [ᵒC]" && outputSelectedOptionText == "Kelvin") {
      outputValue = (inputValueDouble + 273.15).toString()
    } else if (inputSelectedOptionText == "Kelvin" && outputSelectedOptionText == "Celsius [ᵒC]") {
      outputValue = (inputValueDouble - 273.15).toString()
    } else if (inputSelectedOptionText == "Kelvin" && outputSelectedOptionText == "Fahrenheit [ᵒF]") {
      outputValue = (((inputValueDouble - 273.15) * 9.0 / 5.0) + 32.0).toString()
    } else if (inputSelectedOptionText == "Fahrenheit [ᵒF]" && outputSelectedOptionText == "Celsius [ᵒC]") {
      outputValue = ((inputValueDouble - 32.0) * 5.0 / 9.0).toString()
    } else if (inputSelectedOptionText == "Fahrenheit [ᵒF]" && outputSelectedOptionText == "Kelvin") {
      outputValue = (((inputValueDouble - 32.0) * 5.0 / 9.0) + 273.15).toString()
    }

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
        text = "Convert Temperature", fontSize = 25.sp
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
  TemperatureScreen()
}
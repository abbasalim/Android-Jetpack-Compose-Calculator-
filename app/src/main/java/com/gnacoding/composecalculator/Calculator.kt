package com.gnacoding.composecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gnacoding.composecalculator.ui.theme.*

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 12.dp,
    onAction: (CalculatorAction) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Text(
                text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                fontWeight = FontWeight.Light,
                fontSize = 55.sp,
                color = Color.White,
                maxLines = 2
            )
            Divider(
                modifier = Modifier
                    .padding(vertical = 28.dp),
                color = LightBlue,
                thickness = 1.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = Modifier
                        .background(Green)
                        .aspectRatio(2f)
                        .weight(2f),
                    color = Color.Black,
                    onClick = {
                        onAction(CalculatorAction.Clear)
                    }
                )
                /*CalculatorButton(
                    symbol = "( )",
                    modifier = Modifier
                        .background(LightBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    color = Color.Black,
                    onClick = {
                        // TODO
                    }
                )*/
                CalculatorButton(
                    symbol = "%",
                    modifier = Modifier
                        .background(LightBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    color = Color.Black,
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Modulo))
                    }
                )
                CalculatorButton(
                    symbol = "/",
                    modifier = Modifier
                        .background(LightBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    color = Color.Black,
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(7))
                    }
                )
                CalculatorButton(
                    symbol = "8",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(8))
                    }
                )
                CalculatorButton(
                    symbol = "9",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(9))
                    }
                )
                CalculatorButton(
                    symbol = "x",
                    modifier = Modifier
                        .background(LightBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    color = Color.Black,
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(4))
                    }
                )
                CalculatorButton(
                    symbol = "5",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(5))
                    }
                )
                CalculatorButton(
                    symbol = "6",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(6))
                    }
                )
                CalculatorButton(
                    symbol = "-",
                    modifier = Modifier
                        .background(LightBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    color = Color.Black,
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(1))
                    }
                )
                CalculatorButton(
                    symbol = "2",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(2))
                    }
                )
                CalculatorButton(
                    symbol = "3",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(3))
                    }
                )
                CalculatorButton(
                    symbol = "+",
                    modifier = Modifier
                        .background(LightBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    color = Color.Black,
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(0))
                    }
                )
                CalculatorButton(
                    symbol = ".",
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Decimal)
                    }
                )
                PainterCalculatorButton(
                    symbol = painterResource(id = R.drawable.ic_backspace_outlined),
                    modifier = Modifier
                        .background(DarkBlue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Delete)
                    }
                )
                CalculatorButton(
                    symbol = "=",
                    modifier = Modifier
                        .background(Blue)
                        .aspectRatio(1f)
                        .weight(1f),
                    color = Color.Black,
                    onClick = {
                        onAction(CalculatorAction.Calculate)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    ComposeCalculatorTheme {
        val viewModel = viewModel<CalculatorViewModel>()
        val state = viewModel.state
        val buttonSpacing = 8.dp
        Calculator(
            state = state,
            onAction = viewModel::onAction,
            buttonSpacing = buttonSpacing,
            modifier = Modifier
                .fillMaxSize()
                .background(MediumBlue)
                .padding(12.dp)
        )
    }
}
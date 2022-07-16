package ir.wave.composecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    onResult: (String) -> Unit = {},
) {

    val viewModel = viewModel<CalculatorViewModel>()
    viewModel.onResult = onResult
    val onAction = viewModel::onAction
    val state = viewModel.state
    val buttonSpacing = 8.dp

    val operatorBgColor = MaterialTheme.colorScheme.secondaryContainer
    val operatorColor = MaterialTheme.colorScheme.onSecondaryContainer

    val numberBgColor = MaterialTheme.colorScheme.surfaceVariant
    val numberColor = MaterialTheme.colorScheme.onSurfaceVariant

    val evalBgColor = MaterialTheme.colorScheme.primaryContainer
    val evalColor = MaterialTheme.colorScheme.onPrimaryContainer

    val acBgColor = MaterialTheme.colorScheme.tertiaryContainer
    val acColor = MaterialTheme.colorScheme.onTertiaryContainer

    val backgroundColor = MaterialTheme.colorScheme.background
    val mainTextBgColor = MaterialTheme.colorScheme.surfaceVariant
    val mainTextColor = MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = modifier.background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxWidth()
                    .background(mainTextBgColor, RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp))
                    .padding(24.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Light,
                    lineHeight = 50.sp,
                    fontSize = 50.sp,
                    maxLines = 3,
                    color = mainTextColor
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth()
                    .padding(buttonSpacing),
                verticalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "AC",
                        modifier = Modifier
                            .background(acBgColor)
                            .aspectRatio(2f)
                            .weight(2f),

                        onClick = {
                            onAction(CalculatorAction.Clear)
                        }, color = acColor
                    )
                    /*CalculatorButton(
                        symbol = "( )",
                        modifier = Modifier
                            .background(LightBlue)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
                            // TODO
                        }
                    )*/
                    CalculatorButton(
                        symbol = "%",
                        modifier = Modifier
                            .background(operatorBgColor)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
//                        onAction(CalculatorAction.Operation(CalculatorOperation.Modulo))
                            onAction(CalculatorAction.Number('%'))
                        },
                        color = operatorColor
                    )
                    CalculatorButton(
                        symbol = "/",
                        modifier = Modifier
                            .background(operatorBgColor)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                        },
                        color = operatorColor
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "7",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number('7'))
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = "8",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number('8'))
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = "9",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number('9'))
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = "×",
                        modifier = Modifier
                            .background(operatorBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                        },
                        color = operatorColor
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "4",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number('4'))
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = "5",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number('5'))
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = "6",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number('6'))
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = "-",
                        modifier = Modifier
                            .background(operatorBgColor)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                        },
                        color = operatorColor
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "1",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number('1'))
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = "2",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number('2'))
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = "3",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number('3'))
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = "+",
                        modifier = Modifier
                            .background(operatorBgColor)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                        },
                        color = operatorColor
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "0",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number('0'))
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = ".",
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Decimal)
                        },
                        color = numberColor
                    )
                    PainterCalculatorButton(
                        symbol = painterResource(id = R.drawable.ic_backspace_outlined),
                        modifier = Modifier
                            .background(numberBgColor)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Delete)
                        },
                        color = numberColor
                    )
                    CalculatorButton(
                        symbol = "=",
                        modifier = Modifier
                            .background(evalBgColor)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
                            onAction(CalculatorAction.Calculate)
                        },
                        color = evalColor
                    )
                }


            }
        }
    }
}

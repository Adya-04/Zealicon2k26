package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBorderColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TextFieldBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.TextFieldBorderColor
import com.gdg.zealicon2k25.presentation.ui.theme.TextFieldPlaceholderColor

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    enabled: Boolean = true,
    keyBoardType: KeyboardType
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .height(52.dp)
            .background(color = TextFieldBackgroundColor)
            .border(width = 2.dp, color = TextFieldBorderColor, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = HeadingTextColor,
                fontSize = 16.sp,
                fontFamily = Outfit,
                fontWeight = FontWeight.Medium
            ),
            cursorBrush = SolidColor(HeadingTextColor),
            modifier = modifier
                .align(Alignment.Center)
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = keyBoardType),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text =  placeholder,
                        color = TextFieldPlaceholderColor,
                        fontFamily = Outfit,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
                innerTextField()
            },
            singleLine = true,
            enabled = enabled,
        )
    }
}
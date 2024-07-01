package me.ashutoshkk.stonks.presentation.ui.search.componens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import me.ashutoshkk.stonks.presentation.ui.theme.StonksTheme

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onSearchClick: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = StonksTheme.colorScheme.textFieldBackground,
            unfocusedContainerColor = StonksTheme.colorScheme.textFieldBackground,
            disabledContainerColor = StonksTheme.colorScheme.textFieldBackground,
            errorContainerColor = StonksTheme.colorScheme.textFieldBackground,
            focusedLabelColor = StonksTheme.colorScheme.text2,
            unfocusedLabelColor = StonksTheme.colorScheme.text2,
            disabledLabelColor = StonksTheme.colorScheme.subText,
            focusedBorderColor = StonksTheme.colorScheme.focusedBorder,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            cursorColor = StonksTheme.colorScheme.cursor
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = StonksTheme.paddings.horizontal),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick()
            }
        )
    )
}
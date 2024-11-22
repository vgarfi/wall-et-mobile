import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Eye
import compose.icons.fontawesomeicons.solid.EyeSlash

@Composable
fun CustomTextField(
    hint: String,
    label: String,
    isPassword: Boolean,
    controller: MutableState<String>,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val containerColor = Color(0xFFEFEFEF)
    TextField(
        value = controller.value,
        onValueChange = { controller.value = it },
        label = {if (label.isNotEmpty()) Text(text = label) },
        placeholder = { Text(text = hint) },
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        leadingIcon = prefix,
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible) FontAwesomeIcons.Solid.Eye else FontAwesomeIcons.Solid.EyeSlash
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else {
                suffix?.invoke()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(containerColor, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(8.dp),
    )
}

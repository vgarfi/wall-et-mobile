package com.example.wall_etmobile.design_kit.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wall_etmobile.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Copy

@Composable
fun CvuDialog(onDismissRequest: () -> Unit,
              onCopyCvu: () -> Unit,
              cvu: String,
              username: String
) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "${stringResource(R.string.greeting)} ${username}!",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = stringResource(R.string.cvu_presenter)
                )
                ElevatedCard (
                    colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.surface),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                            text = cvu,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                        )
                        IconButton(
                            onClick = {
                                onCopyCvu()
                            }
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Regular.Copy,
                                contentDescription = "copy-cvu",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(6.dp)
                            )
                        }
                    }
                }
                Text(
                    text = stringResource(R.string.cvu_info),
                    fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
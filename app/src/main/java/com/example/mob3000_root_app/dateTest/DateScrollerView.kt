package com.example.mob3000_root_app.dateTest

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDateTime

@Composable
fun DateScrollerView() {
    Box(modifier = Modifier.fillMaxSize()){
        WheelDateTimePicker(
            startDateTime = LocalDateTime.of(
                2025, 10, 30, 5, 0
            ),
            size = DpSize(200.dp, 100.dp),
            backwardsDisabled = true,
            textStyle = MaterialTheme.typography.titleSmall,
            textColor = MaterialTheme.colorScheme.primary,
            selectorProperties = WheelPickerDefaults.selectorProperties(
                enabled = true,
                shape = RoundedCornerShape(0.dp),
                color = Color(0xFFf1faee).copy(alpha = 0.2f),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.surfaceVariant)
            )
        ){ snappedDateTime -> }
    }
}
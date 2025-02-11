package xyz.teamgravity.composeanimationtripleorbitloading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import xyz.teamgravity.composeanimationtripleorbitloading.ui.theme.ComposeAnimationTripleOrbitLoadingTheme

private const val PADDING_PERCENTAGE_SECOND_CIRCLE = 0.15f
private const val PADDING_PERCENTAGE_THIRD_CIRCLE = 0.3f
private const val POSITION_START_OFFSET_SECOND_CIRCLE = 90f
private const val POSITION_START_OFFSET_THIRD_CIRCLE = 135f

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAnimationTripleOrbitLoadingTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        val rotation by rememberInfiniteTransition(
                            label = "transition"
                        ).animateFloat(
                            initialValue = 0F,
                            targetValue = 360F,
                            animationSpec = infiniteRepeatable(
                                animation = tween(
                                    durationMillis = 1_000
                                )
                            ),
                            label = "rotation"
                        )
                        var width by remember { mutableIntStateOf(0) }

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .onSizeChanged {
                                    width = it.width
                                }
                        ) {
                            CircularProgressIndicator(
                                strokeWidth = 1.dp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .graphicsLayer {
                                        rotationZ = rotation
                                    }
                            )
                            CircularProgressIndicator(
                                strokeWidth = 1.dp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(with(LocalDensity.current) { (width * PADDING_PERCENTAGE_SECOND_CIRCLE).toDp() })
                                    .graphicsLayer {
                                        rotationZ = rotation + POSITION_START_OFFSET_SECOND_CIRCLE
                                    }
                            )
                            CircularProgressIndicator(
                                strokeWidth = 1.dp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(with(LocalDensity.current) { (width * PADDING_PERCENTAGE_THIRD_CIRCLE).toDp() })
                                    .graphicsLayer {
                                        rotationZ = rotation + POSITION_START_OFFSET_THIRD_CIRCLE
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}
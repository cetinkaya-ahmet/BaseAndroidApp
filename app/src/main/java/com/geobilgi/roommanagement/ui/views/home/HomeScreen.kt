package com.geobilgi.roommanagement.ui.views.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.geobilgi.roommanagement.R
import com.geobilgi.roommanagement.ui.theme.*
import com.geobilgi.roommanagement.ui.views.storyDetail.StoryDetailScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController) {
    Surface(
        color = White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        , horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmergencyButton()
            Spacer(modifier = Modifier.height(10.dp))
            IntroButton()
            Spacer(modifier = Modifier.height(10.dp))
            LightButton()
            Spacer(modifier = Modifier.height(10.dp))
            DoorButton()
            Spacer(modifier = Modifier.height(10.dp))
            VentilationButton()
            Spacer(modifier = Modifier.height(40.dp))

            val padding = 20.dp
            val density = LocalDensity.current
            Surface(
                shape = RectangleShape,
                color = Color.White,
                elevation = 12.dp,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .drawWithContent {
                        val paddingPx = with(density) { padding.toPx() }
                        clipRect(
                            left = -paddingPx,
                            top = 0f,
                            right = size.width + paddingPx,
                            bottom = size.height + paddingPx
                        ) {
                            this@drawWithContent.drawContent()
                        }
                    }
            ) {
                Column(verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text = "Hikayeler", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
                    Spacer(modifier = Modifier.height(20.dp))
                    StoryItem(navController = navController)
                    Spacer(modifier = Modifier.height(10.dp))
                    StoryItem(navController = navController)
                    Spacer(modifier = Modifier.height(10.dp))
                    StoryItem(navController = navController)
                }
            }



        }

    }
}

@Composable
fun EmergencyButton(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Button(onClick = {
        viewModel.emergencyStart()
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Red)
    ) {
        Icon(
            modifier = Modifier.size(34.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_warning_24),
            contentDescription = "Some icon",
            tint = Color.White
        )
        Text(text = "Acil Durum", color = White)
    }
}

@Composable
fun IntroButton(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val mCheckedState = remember{ mutableStateOf(false)}

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier.size(34.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_info_24),
                contentDescription = "Some icon",
                tint = Color.LightGray
            )


            Text(text = "Intro", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 8.dp)
            )

            Icon(
                modifier = Modifier
                    .size(34.dp)
                    .clickable {
                        mCheckedState.value = !mCheckedState.value
                    },
                imageVector = if(mCheckedState.value) ImageVector.vectorResource(id = R.drawable.ic_baseline_pause_circle_24) else ImageVector.vectorResource(id = R.drawable.ic_baseline_play_arrow_24),
                contentDescription = "Some icon",
                tint = Color.Black,
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = Color.Black))
    }
}

@Composable
fun LightButton(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val mCheckedState = remember{ mutableStateOf(false)}

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier.size(34.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_lightbulb_24),
                contentDescription = "Some icon",
                tint = if(mCheckedState.value) Color.Yellow else Color.LightGray
            )


            Text(text = "Işıklar", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 8.dp)
            )


            Switch(checked = mCheckedState.value,
                onCheckedChange = {mCheckedState.value = it},
                colors = SwitchDefaults.colors(uncheckedTrackColor = Color.DarkGray)

            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = Color.Black))
    }


}

@Composable
fun DoorButton(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val mCheckedState = remember{ mutableStateOf(false)}

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                if(mCheckedState.value) painterResource(R.drawable.opened_door) else  painterResource(R.drawable.door),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(34.dp)
            )


            Text(text = "Kapı", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 8.dp)
            )


            Switch(checked = mCheckedState.value,
                onCheckedChange = {mCheckedState.value = it},
                colors = SwitchDefaults.colors(uncheckedTrackColor = Color.DarkGray)
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = Color.Black))
    }
}
@Composable
fun VentilationButton(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val mCheckedState = remember{ mutableStateOf(false)}

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                if(mCheckedState.value) painterResource(R.drawable.running_fan) else  painterResource(R.drawable.fan),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(34.dp)
            )


            Text(text = "Havanlandırma", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 8.dp)
            )


            Switch(checked = mCheckedState.value,
                onCheckedChange = {mCheckedState.value = it},
                colors = SwitchDefaults.colors(uncheckedTrackColor = Color.DarkGray)
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = Color.Black))
    }

}


@Composable
fun StoryItem(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    var mCheckedState by remember{ mutableStateOf(false)}
    Row(modifier = Modifier
        .background(color = PinkishGrey, shape = RoundedCornerShape(10.dp))
        .fillMaxWidth(0.95f)
        .padding(8.dp)
    , horizontalArrangement = Arrangement.SpaceBetween
    , verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(R.drawable.ic_baseline_settings_24),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    navController.navigate(
                        "story_detail_screen/${12}"
                    )
                }
        )

        Text(text = "Tamek", color = White, style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(4.dp)
        )

        Button(onClick = {
            viewModel.emergencyStart()
            mCheckedState = !mCheckedState
        }, shape = RoundedCornerShape(10.dp)
            ,  colors = if(mCheckedState) ButtonDefaults.buttonColors(backgroundColor = Color.Green) else ButtonDefaults.buttonColors(backgroundColor = Color.Red) ) {
            Text(text = if(mCheckedState) "Durdur" else "Başlat", color = White)
            Icon(
                modifier = Modifier.size(34.dp),
                imageVector = if(mCheckedState) ImageVector.vectorResource(id = R.drawable.ic_baseline_pause_circle_24) else ImageVector.vectorResource(id = R.drawable.ic_baseline_play_arrow_24),
                contentDescription = "Some icon",
                tint = Color.White
            )
        }
    }

}

package com.geobilgi.roommanagement.ui.views.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.geobilgi.roommanagement.R
import com.geobilgi.roommanagement.ui.theme.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController) {
    Surface(
        color = White,
        modifier = Modifier.fillMaxSize()
    ) {
        val isLoading by remember {
            viewModel.isLoading
        }
        val infoMessage by remember {
            viewModel.infoMessage
        }

        val roomSetting by remember {
            viewModel.roomSettings
        }
        if(isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

        }

        if(roomSetting.isNotEmpty())
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
                , horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //EmergencyButton()
                //Spacer(modifier = Modifier.height(10.dp))
                IntroButton(status=  viewModel.roomSettings.value.find { it.Key == "IntoStatus" }!!.Value == "1"){
                    val item = viewModel.roomSettings.value.find { it.Key == "IntoStatus" }!!
                    item.Value = if(it) "1" else "0"
                    item.Description = if(it) "Intro Açık" else "Intro Kapalı"
                    viewModel.updateSettings(item)
                }
                Spacer(modifier = Modifier.height(10.dp))
                LightButton(status=  viewModel.roomSettings.value.find { it.Key == "LightStatus" }!!.Value == "1"){
                    val item = viewModel.roomSettings.value.find { it.Key == "LightStatus" }!!
                    item.Value = if(it) "1" else "0"
                    item.Description = if(it) "Işık Açık" else "Işık Kapalı"
                    viewModel.updateSettings(item)
                }

                Spacer(modifier = Modifier.height(10.dp))
                DoorButton(status=  viewModel.roomSettings.value.find { it.Key == "DoorStatus" }!!.Value == "1"){
                    val item = viewModel.roomSettings.value.find { it.Key == "DoorStatus" }!!
                    item.Value = if(it) "1" else "0"
                    item.Description = if(it) "Kapı Açık" else "Kapı Kapalı"
                    viewModel.updateSettings(item)
                }
                Spacer(modifier = Modifier.height(10.dp))
                VentilationButton(level=  viewModel.roomSettings.value.find { it.Key == "FanLevel" }!!.Value.toFloat()){
                    val item = viewModel.roomSettings.value.find { it.Key == "FanLevel" }!!
                    item.Value = it.toInt().toString()
                    item.Description = when(it){
                        0f-> "Kapalı"
                        1f-> "Düşük"
                        2f-> "Yüksek"
                        else -> {""}
                    }

                    viewModel.updateSettings(item)
                }
                Spacer(modifier = Modifier.height(40.dp))
                Column(verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text = "Hikayeler", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
                    Spacer(modifier = Modifier.height(20.dp))
                    var activeStorySetting = viewModel.roomSettings.value.find { it.Key == "ActiveGame" }!!
                    StoryItem(navController = navController, storyId = "1", isActive = activeStorySetting.Value == "1"){
                        val item = viewModel.roomSettings.value.find { it.Key == "ActiveGame" }!!
                        item.Value = if(it) "1" else "0"
                        item.Description = if(it) "1.Oyun" else "Kapalı"
                        viewModel.updateSettings(item)
                    }
                    StoryItem(navController = navController, storyId = "2", isActive = activeStorySetting.Value == "2"){
                        val item = viewModel.roomSettings.value.find { it.Key == "ActiveGame" }!!
                        item.Value = if(it) "2" else "0"
                        item.Description = if(it) "2.Oyun" else "Kapalı"
                        viewModel.updateSettings(item)
                    }
                    StoryItem(navController = navController, storyId = "3", isActive = activeStorySetting.Value == "3"){
                        val item = viewModel.roomSettings.value.find { it.Key == "ActiveGame" }!!
                        item.Value = if(it) "3" else "0"
                        item.Description = if(it) "2.Oyun" else "Kapalı"
                        viewModel.updateSettings(item)
                    }
                }
            }

       



    }
}

@Composable
private fun EmergencyButton(
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
private fun IntroButton(
    status : Boolean,
    onChanged : (Boolean) -> Unit
) {

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
                        onChanged(!status)
                    },
                imageVector = if(status) ImageVector.vectorResource(id = R.drawable.ic_baseline_pause_circle_24) else ImageVector.vectorResource(id = R.drawable.ic_baseline_play_arrow_24),
                contentDescription = "Some icon",
                tint = Color.Black,
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(0.95f)
            .background(color = Color.Black))
    }
}

@Composable
fun LightButton(
    status : Boolean,
    onChanged : (Boolean) -> Unit
) {
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
                tint = if(status) Color.Yellow else Color.DarkGray
            )


            Text(text = "Işıklar", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 8.dp)
            )


            Switch(checked = status,
                onCheckedChange = onChanged,
                colors = SwitchDefaults.colors(uncheckedTrackColor = Color.DarkGray)
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(0.95f)
            .background(color = Color.Black))
    }


}

@Composable
fun DoorButton(
    status : Boolean,
    onChanged : (Boolean) -> Unit
) {

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                if(status) painterResource(R.drawable.opened_door) else  painterResource(R.drawable.door),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(34.dp)
            )


            Text(text = "Kapı", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 8.dp)
            )


            Switch(checked = status,
                onCheckedChange = onChanged,
                colors = SwitchDefaults.colors(uncheckedTrackColor = Color.DarkGray)
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(0.95f)
            .background(color = Color.Black))
    }
}

@Composable
fun VentilationButton(
    level : Float,
    onChanged : (Float) -> Unit
) {

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                if(level > 0f) painterResource(R.drawable.running_fan) else  painterResource(R.drawable.fan),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(34.dp)
            )


            Text(text = "Havanlandırma", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(start = 8.dp)
            )


            var levelMessage by remember { mutableStateOf("Kapalı") }
            when(level){
                0f-> levelMessage = "Kapalı"
                1f-> levelMessage = "Düşük"
                2f-> levelMessage = "Yüksek"
            }
            Text(text = levelMessage, modifier = Modifier.padding(8.dp))

            Slider(
                value = level,
                onValueChange = onChanged,
                valueRange = 0f..2f,
                onValueChangeFinished = {
                    // launch some business logic update with the state you hold
                    // viewModel.updateSelectedSliderValue(sliderPosition)
                },
                steps = 1,
                colors = SliderDefaults.colors(
                    thumbColor = Color.Blue,
                    activeTrackColor = Color.Blue,
                    inactiveTrackColor = Color.LightGray
                ),
                modifier = Modifier.width(140.dp)
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(0.95f)
            .background(color = Color.Black))
    }

}


@Composable
private fun StoryItem(
    navController: NavController,
    storyId : String,
    isActive: Boolean,
    onChanged: (Boolean) -> Unit
) {
    val padding = 8.dp
    val density = LocalDensity.current
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        elevation = 12.dp,
        modifier = Modifier
            .padding(padding)
            .drawWithContent {
                val paddingPx = with(density) { padding.toPx() }
                clipRect(
                    left = 0f,
                    top = 0f,
                    right = size.width + paddingPx,
                    bottom = size.height + paddingPx
                ) {
                    this@drawWithContent.drawContent()
                }
            }
    ) {
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
                            "story_detail_screen/${storyId}"
                        )
                    }
            )

            Text(text = storyId, color = White, style = MaterialTheme.typography.h6, maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(4.dp)
            )

            Button(onClick = {
                onChanged(!isActive)
            }, shape = RoundedCornerShape(10.dp)
                ,  colors = if(isActive) ButtonDefaults.buttonColors(backgroundColor = Color.Green) else ButtonDefaults.buttonColors(backgroundColor = Color.Red) ) {
                Text(text = if(isActive) "Durdur" else "Başlat", color = White)
                Icon(
                    modifier = Modifier.size(34.dp),
                    imageVector = if(isActive) ImageVector.vectorResource(id = R.drawable.ic_baseline_pause_circle_24) else ImageVector.vectorResource(id = R.drawable.ic_baseline_play_arrow_24),
                    contentDescription = "Some icon",
                    tint = Color.White
                )
            }
        }
    }


}

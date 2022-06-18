package com.geobilgi.roommanagement.ui.views.storyDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.geobilgi.roommanagement.R
import com.geobilgi.roommanagement.ui.theme.Black
import com.geobilgi.roommanagement.ui.theme.White
import com.geobilgi.roommanagement.ui.views.home.DoorButton
import com.geobilgi.roommanagement.ui.views.home.LightButton
import com.geobilgi.roommanagement.ui.views.home.VentilationButton

@Composable
fun StoryDetailScreen(
    viewModel: StoryDetailViewModel = hiltViewModel(),
    storyId: String,
    navController: NavController
) {
    Surface(
        color = White,
        modifier = Modifier.fillMaxSize()
    ) {
        LaunchedEffect(key1 = Unit, block = {
            viewModel.getGameSettings(storyId)
        })

        val isLoading by remember {
            viewModel.isLoading
        }

        val gameSettings by remember {
            viewModel.gameSettings
        }
        if(isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

        }
        if(gameSettings.isNotEmpty())
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                , horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = storyId, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Red, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(color = Color.Black))
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Oda Ayarları", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
                LightButton(status=  viewModel.gameSettings.value.find { it.Key == "LightStatus" }!!.Value == "1"){
                    val item = viewModel.gameSettings.value.find { it.Key == "LightStatus" }!!
                    item.Value = if(it) "1" else "0"
                    item.Description = if(it) "Işık Açık" else "Işık Kapalı"
                    viewModel.updateSettings(item)
                }
                Spacer(modifier = Modifier.height(10.dp))
                DoorButton(status = viewModel.gameSettings.value.find { it.Key == "DoorStatus" }!!.Value == "1") {
                    val item = viewModel.gameSettings.value.find { it.Key == "DoorStatus" }!!
                    item.Value = if (it) "1" else "0"
                    item.Description = if (it) "Kapı Açık" else "Kapı Kapalı"
                    viewModel.updateSettings(item)
                }
                Spacer(modifier = Modifier.height(10.dp))
                VentilationButton(level=  viewModel.gameSettings.value.find { it.Key == "FanLevel" }!!.Value.toFloat()){
                    val item = viewModel.gameSettings.value.find { it.Key == "FanLevel" }!!
                    item.Value = it.toInt().toString()
                    item.Description = when(it){
                        0f-> "Kapalı"
                        1f-> "Düşük"
                        2f-> "Yüksek"
                        else -> {""}
                    }
                    viewModel.updateSettings(item)
                }
                Spacer(modifier = Modifier.height(10.dp))
                SoundButton(level=  viewModel.gameSettings.value.find { it.Key == "SoundLevel" }!!.Value.toFloat()){
                    val item = viewModel.gameSettings.value.find { it.Key == "SoundLevel" }!!
                    item.Value = it.toInt().toString()
                    item.Description = when(it){
                        0f-> "Kapalı"
                        1f-> "Çok Kısık"
                        2f-> "Kısık"
                        3f-> "Normal"
                        4f-> "Yüksek"
                        else -> {""}
                    }
                    viewModel.updateSettings(item)
                }
                Spacer(modifier = Modifier.height(10.dp))
                SmellButton(level=  viewModel.gameSettings.value.find { it.Key == "SmellLevel" }!!.Value.toFloat()){
                    val item = viewModel.gameSettings.value.find { it.Key == "SmellLevel" }!!
                    item.Value = it.toInt().toString()
                    item.Description = when(it){
                        0f-> "Kapalı"
                        1f-> "Düşük"
                        2f-> "Normal"
                        3f-> "Yüksek"
                        else -> {""}
                    }
                    viewModel.updateSettings(item)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Oyun Ayarları", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
                VRButton(status=  viewModel.gameSettings.value.find { it.Key == "VRActive" }!!.Value == "1"){
                    val item = viewModel.gameSettings.value.find { it.Key == "VRActive" }!!
                    item.Value = if(it) "1" else "0"
                    item.Description = if(it) "VR Açık" else "VR Kapalı"
                    viewModel.updateSettings(item)
                }
                Spacer(modifier = Modifier.height(20.dp))
                SaveButton()
            }
    }
}


/*@Composable
private fun LightButton(
    viewModel: StoryDetailViewModel = hiltViewModel()
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
                tint = if(mCheckedState.value) Color.Yellow else Color.DarkGray
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
            .fillMaxWidth(0.95f)
            .background(color = Color.Black))
    }


}*/

/*@Composable
private fun DoorButton(
    viewModel: StoryDetailViewModel = hiltViewModel()
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
            .fillMaxWidth(0.95f)
            .background(color = Color.Black))
    }
}*/

/*@Composable
private fun VentilationButton(
    viewModel: StoryDetailViewModel = hiltViewModel()
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
            .fillMaxWidth(0.95f)
            .background(color = Color.Black))
    }

}*/

@Composable
private fun VRButton(
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
                painterResource(R.drawable.oculus_rift),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(34.dp)
            )


            Text(text = "VR", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
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
private fun SoundButton(
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
                if(level > 0f) painterResource(R.drawable.volume) else painterResource(R.drawable.mute) ,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(34.dp)
            )


            Text(text = "Ses", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .padding(start = 8.dp)
            )

            var levelMessage by remember { mutableStateOf("Kapalı") }
            when(level){
                0f-> levelMessage = "Kapalı"
                1f-> levelMessage = "Çok Kısık"
                2f-> levelMessage = "Kısık"
                3f-> levelMessage = "Normal"
                4f-> levelMessage = "Yüksek"
            }
            Text(text = levelMessage, modifier = Modifier.padding(8.dp))

            Slider(
                value = level,
                onValueChange = onChanged,
                valueRange = 0f..4f,
                onValueChangeFinished = {
                    // launch some business logic update with the state you hold
                    // viewModel.updateSelectedSliderValue(sliderPosition)
                },
                steps = 3,
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
private fun SmellButton(
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
                painterResource(R.drawable.aroma) ,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(34.dp)
            )


            Text(text = "Koku", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .padding(start = 8.dp)
            )


            var sliderLevel by remember { mutableStateOf("Kapalı") }
            when(level){
                0f-> sliderLevel = "Kapalı"
                1f-> sliderLevel = "Düşük"
                2f-> sliderLevel = "Normal"
                3f-> sliderLevel = "Yüksek"
            }
            Text(text = sliderLevel, modifier = Modifier.padding(8.dp))

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
private fun SaveButton(
    viewModel: StoryDetailViewModel = hiltViewModel()
) {
    Button(onClick = {
        viewModel.saveStorySettings()
    },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
        enabled = !viewModel.isLoading.value,
        modifier = Modifier.fillMaxWidth(0.7f)
    ) {
        Icon(
            modifier = Modifier.size(34.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_save_24),
            contentDescription = "Some icon",
            tint = Color.White
        )
        Text(text = "KAYDET", color = White)
        Spacer(modifier = Modifier.width(20.dp))
        if(viewModel.isLoading.value) {
            CircularProgressIndicator(color = White, modifier = Modifier.size(20.dp))
        }
    }
}
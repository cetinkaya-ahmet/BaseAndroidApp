package com.geobilgi.roommanagement.ui.views.storyDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import com.geobilgi.roommanagement.ui.theme.Black
import com.geobilgi.roommanagement.ui.theme.Red
import com.geobilgi.roommanagement.ui.theme.Teal200
import com.geobilgi.roommanagement.ui.theme.White
import com.geobilgi.roommanagement.ui.views.home.*

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
        HomeScreenMainContent(storyId = storyId)
    }
}

@Composable
private fun HomeScreenMainContent(viewModel: StoryDetailViewModel = hiltViewModel(),
                                  storyId: String) {
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
        LightButton()
        Spacer(modifier = Modifier.height(10.dp))
        DoorButton()
        Spacer(modifier = Modifier.height(10.dp))
        VentilationButton()
        Spacer(modifier = Modifier.height(10.dp))
        SoundButton()
        Spacer(modifier = Modifier.height(10.dp))
        SmellButton()
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Oyun Ayarları", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
        VRButton()
        Spacer(modifier = Modifier.height(20.dp))
        SaveButton()
    }
}

@Composable
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


}

@Composable
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
}

@Composable
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

}

@Composable
private fun VRButton(
    viewModel: StoryDetailViewModel = hiltViewModel()
) {
    val mCheckedState = remember{ mutableStateOf(false) }

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


}

@Composable
private fun SoundButton(
    viewModel: StoryDetailViewModel = hiltViewModel()
) {
    val mCheckedState = remember{ mutableStateOf(true) }

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                if(mCheckedState.value) painterResource(R.drawable.volume) else painterResource(R.drawable.mute) ,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(34.dp)
            )


            Text(text = "Ses", color = Black, textAlign = TextAlign.Start, fontSize = 20.sp,
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


}

@Composable
private fun SmellButton(
    viewModel: StoryDetailViewModel = hiltViewModel()
) {
    val mCheckedState = remember{ mutableStateOf(true) }

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
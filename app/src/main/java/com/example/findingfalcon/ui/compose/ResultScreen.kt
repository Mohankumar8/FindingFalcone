package com.example.findingfalcon.ui.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.findingfalcon.data.response.FindFalconResponse
import com.example.findingfalcon.ui.viewmodel.ResultViewModel
import com.example.findingfalcon.ui.viewmodel.SharedViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ResultScreen(
    viewModel: ResultViewModel,
    sharedViewModel: SharedViewModel,
    onStartButtonClicked: () -> Unit
) {
    viewModel.findFalcone(sharedViewModel)
    val loading by viewModel.isLoading.collectAsState()
    val response: FindFalconResponse by viewModel.falconeResult.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center) {
            val message: String
            when (response) {
                is FindFalconResponse.SUCCESS -> {
                    if((response as FindFalconResponse.SUCCESS).status == "false") {
                        message = "Failed to find Falcone, Try again!"
                        Text(text = message)
                    } else {
                        message = "Success! Congratulations on finding Falcone. King Shan is mighty pleased."
                        Text(text = message)
                        Text(text = "Time taken: ${sharedViewModel.falconeRequest.value.time_taken}",
                        modifier = Modifier.padding(top = 16.dp))
                        Text(text = "Planet found: ${(response as FindFalconResponse.SUCCESS).planet_name}")
                    }
                }
                is FindFalconResponse.FAILURE -> {
                    message = "Failed to find Falcone, Try again!"
                    Text(text = message)
                }
                is FindFalconResponse.ERROR -> {
                    message = (response as FindFalconResponse.ERROR).error
                    Text(text = message)
                }
            }
            Button(
                onClick = onStartButtonClicked,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10)
            ) {
                Text(text = "Start Again")
            }
        }
        AddLoader(loading = loading)
    }
}




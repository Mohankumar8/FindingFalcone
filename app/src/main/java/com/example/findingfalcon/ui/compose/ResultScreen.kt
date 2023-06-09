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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.findingfalcon.R
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
                    if((response as FindFalconResponse.SUCCESS).status == stringResource(R.string.status_false)) {
                        message = stringResource(R.string.message_try_again)
                        Text(text = message)
                    } else {
                        message = stringResource(R.string.message_success)
                        Text(text = message)
                        Text(text = "${stringResource(R.string.time_taken)}: ${sharedViewModel.falconeRequest.value.time_taken}",
                        modifier = Modifier.padding(top = 16.dp))
                        Text(text = "${stringResource(R.string.planet_found)}: ${(response as FindFalconResponse.SUCCESS).planet_name}")
                    }
                }
                is FindFalconResponse.FAILURE -> {
                    message = stringResource(R.string.message_try_again)
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
                Text(text = stringResource(R.string.start_again))
            }
        }
        AddLoader(loading = loading)
    }
}




package com.example.kisileruygulamasi.uix.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kisileruygulamasi.R
import com.example.kisileruygulamasi.data.entity.Kisiler
import com.example.kisileruygulamasi.uix.viewmodel.AnaSayfaViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnaSayfa(navController: NavController,
             anaSayfaViewModel: AnaSayfaViewModel
){
    val aramaYapiliyorMu = remember { mutableStateOf(false) } //Arama yapıp yapmadığımızı anladığımız bir değer
    val tf = remember { mutableStateOf("") }//Arama sonucu değeri okuma
    val kisilerListesi = anaSayfaViewModel.kisilerListesi.observeAsState(listOf())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        anaSayfaViewModel.kisileriYukle()
        //anasayfaya geri dönüldüğünde çalışır.
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if(aramaYapiliyorMu.value){
                        TextField(
                            value = tf.value,
                            onValueChange = {
                                tf.value = it
                                anaSayfaViewModel.ara(it)
                            },
                            label = { Text(text = "Ara") },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                focusedLabelColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedContainerColor = Color.Transparent,
                                unfocusedLabelColor = Color.Black,
                                unfocusedIndicatorColor =  Color.White,
                            )
                        )
                    }else{
                        Text(text = "Kişiler")
                    } },
                actions = {
                    if(aramaYapiliyorMu.value){
                        IconButton(onClick = {
                            aramaYapiliyorMu.value = false
                            tf.value = ""
                        }) { Icon(painter = painterResource(id = R.drawable.kapat_resim), contentDescription = "")}
                    }else{
                        IconButton(onClick = {
                            aramaYapiliyorMu.value = true
                        }) { Icon(painter = painterResource(id = R.drawable.ara_resim), contentDescription = "")}
                    }
                }
            ) },
        floatingActionButton = {
            //KisiKayitSayfasına geçiş yapcağımız kod
            FloatingActionButton(onClick = { navController.navigate("kisiKayitSayfa") },
                content = {
                    Icon(painter = painterResource(id = R.drawable.ekle_resim), contentDescription = "")
                })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {paddingValues ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            items(
                count = kisilerListesi.value.count(), //ben kaç veri gödtereceğim
                itemContent = { //for döngüsü gibi çalışıyor
                    val kisi = kisilerListesi.value[it]
                    Card(modifier = Modifier.padding(all = 5.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().clickable {
                            val kisijson = Gson().toJson(kisi) //Burada Stringleştirdik
                            navController.navigate("kisiDetaySayfa/$kisijson")
                        },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                            Column(modifier = Modifier.padding(all = 10.dp))  {
                                Text(text = kisi.kisi_ad, fontSize = 20.sp)
                                Spacer(modifier = Modifier.size(10.dp))
                                Text(text = kisi.kisi_tel)
                            }
                            IconButton(onClick = {
                                scope.launch {
                                    val sb = snackbarHostState.showSnackbar(
                                        message = "${kisi.kisi_ad} silinsin mi?",
                                        actionLabel = "EVET")
                                    if (sb == SnackbarResult.ActionPerformed){
                                        anaSayfaViewModel.sil(kisi.kisi_id)
                                    }
                                }
                            }) { Icon(painter = painterResource(id = R.drawable.kapat_resim), tint = Color.Gray, contentDescription = "")}
                        }
                    }
                }
            )
        }
    }
}

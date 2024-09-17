package com.example.kisileruygulamasi.uix.view


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kisileruygulamasi.data.entity.Kisiler
import com.example.kisileruygulamasi.uix.viewmodel.AnaSayfaViewModel
import com.example.kisileruygulamasi.uix.viewmodel.KisiDetayViewModel
import com.example.kisileruygulamasi.uix.viewmodel.KisiKayitViewModel
import com.google.gson.Gson


@Composable
fun SayfaGecisleri(anaSayfaViewModel: AnaSayfaViewModel,
                   kisiKayitViewModel: KisiKayitViewModel,
                   kisiDetayViewModel: KisiDetayViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anaSayfa") {
        composable("anaSayfa"){
            AnaSayfa(navController = navController,anaSayfaViewModel)
        }
        composable("kisiKayitSayfa"){
            KisiKayitSayfa(kisiKayitViewModel)
        }
        composable("kisiDetaySayfa/{kisi}",
            arguments = listOf(
                navArgument("kisi"){type= NavType.StringType} //Nesnemizi Stringleştiriyoruz
            )){
            val json = it.arguments?.getString("kisi") //String olarak aldık
            val nesne = Gson().fromJson(json,Kisiler::class.java) //Nesneleştiriyoruz. İlk json modelimi sonra dönüştüreceğimiz class'ı yazdık
            KisiDetaySayfa(nesne,kisiDetayViewModel) //Nesneyi aktardık
        }
    }
}
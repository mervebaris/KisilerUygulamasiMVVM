package com.example.kisileruygulamasi.uix.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.data.repo.KisilerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KisiKayitViewModel : ViewModel() {
    val krepo = KisilerRepository()
    val grepo = KisilerRepository()

    fun kaydet(kisiAd:String, kisiTel:String){
        CoroutineScope(Dispatchers.Main).launch {
            krepo.kaydet(kisiAd,kisiTel)

        }
    }

}

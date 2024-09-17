package com.example.kisileruygulamasi.uix.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.data.repo.KisilerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KisiDetayViewModel : ViewModel() {

    val krepo = KisilerRepository()

    fun guncelle(kisiId:Int, kisiAd:String, kisiTel:String){
        CoroutineScope(Dispatchers.Main).launch {
            krepo.guncelle(kisiId,kisiAd,kisiTel)
        }
    }


}
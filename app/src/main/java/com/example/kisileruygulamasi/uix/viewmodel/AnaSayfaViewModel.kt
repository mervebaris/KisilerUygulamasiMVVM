package com.example.kisileruygulamasi.uix.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.data.entity.Kisiler
import com.example.kisileruygulamasi.data.repo.KisilerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnaSayfaViewModel :ViewModel() {
    var krepo = KisilerRepository()
    var kisilerListesi = MutableLiveData<List<Kisiler>>()

    init {
        kisileriYukle()
    }

    fun kisileriYukle() {
        CoroutineScope(Dispatchers.Main).launch {
           kisilerListesi.value = krepo.kisileriYukle()
        }
    }

    fun ara(aramaKelimesi:String) {
        CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = krepo.ara(aramaKelimesi)
        }
    }

     fun sil(kisiId:Int){
        CoroutineScope(Dispatchers.Main).launch {
            krepo.sil(kisiId)
            kisileriYukle()
        }
    }

}

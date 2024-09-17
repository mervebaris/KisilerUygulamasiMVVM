package com.example.kisileruygulamasi.data.datasource

import android.util.Log
import com.example.kisileruygulamasi.data.entity.Kisiler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KisilerDataSource {

    suspend fun kaydet(kisiAd:String, kisiTel:String){
        Log.e("Kişi Kaydet", "$kisiAd- $kisiTel")
    }

    suspend fun guncelle(kisiId: Int, kisiAd: String, kisiTel: String) {
        Log.e("Kişi Güncelle", "$kisiId - $kisiAd - $kisiTel")
    }

    suspend fun sil(kisiId:Int){
        Log.e(" Kişi Sil", kisiId.toString())
    }

    suspend fun kisileriYukle() : List<Kisiler> = withContext(Dispatchers.IO){

        val kisilerListesi = ArrayList<Kisiler>()

        val k1 = Kisiler(1, "Ahmet", "1111")
        val k2 = Kisiler(2, "Zeynep", "2222")
        val k3 = Kisiler(3, "Beyza", "3333")
        kisilerListesi.add(k1)
        kisilerListesi.add(k2)
        kisilerListesi.add(k3)

        return@withContext kisilerListesi
    }

    suspend fun ara(aramaKelimesi:String) : List<Kisiler> = withContext(Dispatchers.IO){

        val kisilerListesi = ArrayList<Kisiler>()
        val k1 = Kisiler(1, "Ahmet", "1111")
        kisilerListesi.add(k1)

        return@withContext kisilerListesi
    }


}


package com.example.kisileruygulamasi.data.repo

import android.util.Log
import com.example.kisileruygulamasi.data.datasource.KisilerDataSource
import com.example.kisileruygulamasi.data.entity.Kisiler

class KisilerRepository {
    val kds = KisilerDataSource()

    suspend fun kaydet(kisiAd:String, kisiTel:String) = kds.kaydet(kisiAd,kisiTel)

    suspend fun guncelle(kisiId:Int, kisiAd:String, kisiTel:String) = kds.guncelle(kisiId,kisiAd,kisiTel)

    suspend fun sil(kisiId:Int) = kds.sil(kisiId)

    suspend fun kisileriYukle() : List<Kisiler> = kds.kisileriYukle()

    suspend fun ara(aramaKelimesi:String) : List<Kisiler> = kds.ara(aramaKelimesi)

}

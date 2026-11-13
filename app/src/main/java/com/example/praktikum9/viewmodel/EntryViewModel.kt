package com.example.praktikum9.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.praktikum9.repositori.RepositoriSiswa


class EntryViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private fun validasiInput(uiState: DetailsSiswa = uiStateSiswa.detailsSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailsSiswa: DetailsSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailsSiswa = detailsSiswa, isEntryValid = validasiInput(detailsSiswa))
    }

    suspend fun saveSiswa() {
        if (validasiInput()) {
            repositoriSiswa.insertSiswa(uiStateSiswa.detailsSiswa.toSiswa())
        }
    }
}
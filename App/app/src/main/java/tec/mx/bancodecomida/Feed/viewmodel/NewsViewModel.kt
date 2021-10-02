package tec.mx.bancodecomida.Feed.viewmodel

import androidx.lifecycle.ViewModel
import tec.mx.bancodecomida.Feed.repository.NewRepository

class NewsViewModel(val newsRepo : NewRepository) : ViewModel() {

    fun getViewInfo() = newsRepo.getNewDetails()

}
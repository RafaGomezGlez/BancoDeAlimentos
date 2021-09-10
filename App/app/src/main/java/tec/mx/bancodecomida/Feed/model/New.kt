package tec.mx.bancodecomida.Feed.model


import java.io.Serializable

data class New (
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val imageId : Int,
    val year : Int
): Serializable
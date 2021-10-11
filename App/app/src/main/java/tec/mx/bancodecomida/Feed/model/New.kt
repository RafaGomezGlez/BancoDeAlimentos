package tec.mx.bancodecomida.Feed.model


import java.io.Serializable

data class New (
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val imageId : String,
    val year : Int
): Serializable {
    constructor() : this(0, "", "", "", "", 0)
}
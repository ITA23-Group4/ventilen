package com.example.ventilen_app.data.models

import com.google.firebase.firestore.DocumentId

data class Event(
    val title:String = "",
    var counter:Int = 0,
    @DocumentId var id: String? = null,

) {

}

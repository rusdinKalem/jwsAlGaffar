package com.roesch.jwsalgaffar.components

import androidx.compose.runtime.Composable

@Composable
fun listBilanganBulat (): List<String> {
    return listOf(
        "-20","-19","-18","-17","-16","-15","-14","-13","-12","-11",
        "-10","-9","-8","7","-6","-5","-4","-3","-2","-1","0",
        "1","2","3","4","5","6","7","8","9","10",
        "11","12","13","14","15","16","17","18","19","20"
    )
}
fun listBilanganBulatPositif (): List<String> {
    return listOf(
        "0", "1","2","3","4","5","6","7","8","9","10",
        "11","12","13","14","15","16","17","18","19","20",
        "21","22","23","24","25","26","27","28","29","30",
        "31","32","33","34","35","36","37","38","39","40"
    )
}

fun listType (): List<String> {
    return listOf(
        "MASJID",
        "MUSHOLLA",
        "SURAU",
        "LANGGAR"
    )
}

fun listZona (): List<String> {
    return listOf(
        "Waktu Indonesia Barat",
        "Waktu Indonesia Tengah",
        "Waktu Indonesia Timur"
    )
}

fun listMode (): List<String> {
    return listOf(
        "NORMAL",
        "JAM BESAR",
        "PADAM"
    )
}
fun listWaktuMalam (): List<String> {
    return listOf(
        "8", "9", "10", "11", "12"
    )
}
fun listWaktuPagi (): List<String> {
    return listOf(
        "1", "2", "3", "4", "5"
    )
}
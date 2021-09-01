package uz.androdev.shared.model

data class Zikr(
    val id: Long,
    val zikrUzbek: String,
    val zikrArabic: String,
    var count: Int
)
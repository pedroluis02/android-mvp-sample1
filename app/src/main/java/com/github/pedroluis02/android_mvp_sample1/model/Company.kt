package com.github.pedroluis02.android_mvp_sample1.model

class Company(
    val id: String,
    val name: String,
) {
    override fun toString(): String {
        return "$id, $name"
    }
}
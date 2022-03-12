package com.github.pedroluis02.android_mvp_sample1.model

 class Employee(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val companyId: String,
) {
     override fun toString(): String {
         return "$id, $name, $phone, $email"
     }
 }
package com.programmer270487.filamentapp.data

// data module
import com.google.gson.annotations.SerializedName

data class Coordinates(
    val lat: Double,
    val lng: Double
)

data class Employment(
    val title: String,
    @SerializedName("key_skill") val keySkill: String
)

data class Address(
    val city: String,
    @SerializedName("street_name") val streetName: String,
    @SerializedName("street_address") val streetAddress: String,
    @SerializedName("zip_code") val zipCode: String,
    val state: String,
    val country: String,
    val coordinates: Coordinates
)

data class CreditCard(
    @SerializedName("cc_number") val ccNumber: String
)

data class Subscription(
    val plan: String,
    val status: String,
    @SerializedName("payment_method") val paymentMethod: String,
    val term: String
)

data class User(
    val id: Int,
    val uid: String,
    val password: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val username: String,
    val email: String,
    val avatar: String,
    val gender: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("social_insurance_number") val socialInsuranceNumber: String,
    @SerializedName("date_of_birth") val dateOfBirth: String,
    val employment: Employment,
    val address: Address,
    @SerializedName("credit_card") val creditCard: CreditCard,
    val subscription: Subscription
)

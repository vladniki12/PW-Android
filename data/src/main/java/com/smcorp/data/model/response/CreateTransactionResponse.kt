package com.smcorp.data.model.response

import com.google.gson.annotations.SerializedName

class CreateTransactionResponse(
    @SerializedName("trans_token") val transaction: Transaction){

}
class Transaction(@SerializedName("id") val id: Int,
                  @SerializedName("date") val date: String,
                  @SerializedName("username") val username: String,
                  @SerializedName("amount") val amount: Float,
                  @SerializedName("balance") val balance: Float
)
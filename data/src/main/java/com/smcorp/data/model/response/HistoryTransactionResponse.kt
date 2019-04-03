package com.smcorp.data.model.response

import com.google.gson.annotations.SerializedName

class HistoryTransactionResponse(
    @SerializedName("trans_token") val transactions: List<Transaction>)
package com.avolodin.dogsapp.data.remote.model

import com.avolodin.dogsapp.data.remote.model.Constants.MESSAGE
import com.avolodin.dogsapp.data.remote.model.Constants.STATUS
import com.google.gson.annotations.SerializedName

data class BreedResponse(
    @SerializedName(MESSAGE) val message: List<String?>?,
    @SerializedName(STATUS) val status: String?
)
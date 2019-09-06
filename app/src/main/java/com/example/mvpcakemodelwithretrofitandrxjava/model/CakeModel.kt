package com.example.mvpcakemodelwithretrofitandrxjava.model

import com.google.gson.annotations.SerializedName

data class CakeModel (

	@SerializedName("title") val title : String,
	@SerializedName("desc") val desc : String,
	@SerializedName("image") val image : String
)
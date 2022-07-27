package com.dev.forvaritepet.repository

import android.content.Context
import com.dev.forvaritepet.R
import com.dev.forvaritepet.model.Pet

object PetData {
    fun getListOfPets(context: Context)  = listOf(

        Pet(
            position = 0,
            name = context.getString(R.string.bulldogs),
            picture = R.drawable.bulldogs,
            sku = "pick1",
            strength = 100
        ),
        Pet(
            position = 1,
            name = context.getString(R.string.frenchbulldog),
            picture = R.drawable.frenchbulldog,
            sku = "pick2",
            strength = 200
        ),
        Pet(
            position = 2,
            name = context.getString(R.string.germanshepherd),
            picture = R.drawable.germanshepherd,
            sku = "pick3",
            strength = 300
        ),
        Pet(
            position = 3,
            name = context.getString(R.string.labrador),
            picture = R.drawable.labrador,
            sku = "pick4",
            strength = 400
        ),
        Pet(
            position = 4,
            name = context.getString(R.string.malegolden),
            picture = R.drawable.malegolden,
            sku = "pick5",
            strength = 500
        ),
        Pet(
            position = 5,
            name = context.getString(R.string.poodlelying),
            picture = R.drawable.poodlelying,
            sku = "pick6",
            strength = 600
        )
    )



    fun getMyPet(context: Context) : Pet =
        Pet(
            position = 0,
            name = context.getString(R.string.bulldogs),
            picture = R.drawable.bulldogs,
            sku = null,
            strength = 100
        )

}
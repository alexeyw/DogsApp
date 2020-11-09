package com.avolodin.dogsapp.factory

import com.avolodin.dogsapp.data.remote.model.BreedImageResponse
import com.avolodin.dogsapp.data.remote.model.BreedResponse
import com.avolodin.dogsapp.domain.model.BreedModel
import com.avolodin.dogsapp.ui.model.BreedView
import com.avolodin.dogsapp.util.RandomDataUtil

object ModelFactory {

    fun makeBreedResponse(size: Int) = BreedResponse(
        message = makeStringList(size),
        status = RandomDataUtil.generateString()
    )

    fun makeBreedImageResponse(size: Int) = BreedImageResponse(
        message = makeStringList(size),
        status = RandomDataUtil.generateString()
    )

    fun makeBreedModelList(size: Int): List<BreedModel> {
        return if (size == 0) {
            arrayListOf()
        } else {
            (0..size).map { BreedModel(RandomDataUtil.generateString()) }
        }
    }

    fun makeBreedViewList(size: Int): List<BreedView> {
        return if (size == 0) {
            arrayListOf()
        } else {
            (0..size).map { BreedView(RandomDataUtil.generateString()) }
        }
    }

    private fun makeStringList(count: Int): List<String> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0..count).map { RandomDataUtil.generateString() }
        }
    }

}
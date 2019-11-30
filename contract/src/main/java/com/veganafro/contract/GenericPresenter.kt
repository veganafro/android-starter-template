package com.veganafro.contract

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

interface GenericPresenter: CoroutineScope {

    val fragment: GenericFragment?

    val job: Job

    suspend fun loadData()

    fun unsubscribe()

    fun subscribe()

    fun onDestroy()
}

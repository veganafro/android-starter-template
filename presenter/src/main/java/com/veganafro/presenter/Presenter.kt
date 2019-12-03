package com.veganafro.presenter

import com.veganafro.contract.GenericFragment
import com.veganafro.contract.GenericPresenter
import com.veganafro.networking.Service
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class Presenter @Inject constructor(
    private val service: Service
) : GenericPresenter {

    override var fragment: GenericFragment? = null

    override val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext = job
        .plus(Dispatchers.IO)
        .plus(CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
            launch(Dispatchers.Main) {
                fragment?.onFetchDataError(throwable)
            }
        })

    override suspend fun loadData() {
        withContext(Dispatchers.Main) {
            fragment?.onFetchDataStarted()
        }

        val dataModel = service
            .getEndpoint("", BuildConfig.API_KEY)

        withContext(Dispatchers.Main) {
            fragment?.onFetchDataSuccess(dataModel.results)
            fragment?.onFetchDataCompleted()
        }
    }

    override fun subscribe() {
        launch {
            loadData()
        }
    }

    override fun unsubscribe() {
        job.cancelChildren()
    }

    override fun onDestroy() {
        fragment = null
        job.cancel()
    }
}

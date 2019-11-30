package com.veganafro.presenter

import android.util.Log
import com.veganafro.contract.GenericFragment
import com.veganafro.contract.GenericPresenter
import com.veganafro.networking.Service
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class Presenter @Inject constructor(
    private val service: Service
) : GenericPresenter {

    override var fragment: GenericFragment? = null
        set(value) {
            field?.let {} ?: run { field = value }
        }

    override val job: Job = Job()
    override val coroutineContext: CoroutineContext = job
        .plus(Dispatchers.IO)
        .plus(CoroutineExceptionHandler { coroutineContext: CoroutineContext, throwable: Throwable ->
            Log.e(
                "AST|Presenter",
                "Active status ${coroutineContext.isActive}",
                throwable
            )
        })

    override suspend fun loadData() {
        withContext(Dispatchers.Main) {
            fragment?.onFetchDataStarted()
        }

        try {
            val dataModel = service
                .getEndpoint("", BuildConfig.API_KEY)

            withContext(Dispatchers.Main) {
                fragment?.onFetchDataSuccess(dataModel.results)
                fragment?.onFetchDataCompleted()
            }
        } catch (httpException: IOException) {
            withContext(Dispatchers.Main) {
                fragment?.onFetchDataError(httpException)
            }
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

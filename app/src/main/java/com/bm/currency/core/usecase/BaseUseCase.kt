package com.bm.currency.core.usecase


import com.bm.currency.core.network.BaseResponse
import com.bm.currency.core.network.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * Created by Mahmoud Ayman on 12/8/2021.
 */
abstract class BaseUseCase<RequestType : BaseResponse, ResultType : Any, in Params> {
    //map class from response to the result needed in View

    abstract fun mapper(req: RequestType): ResultType

    //run the remote api
    abstract fun executeRemote(
        params: Params?
    ): Flow<RequestType>


    fun handler(onResult: (Resource<ResultType>) -> Unit) =
        CoroutineExceptionHandler { _, exception ->
            onResult.invoke(Resource.loading(false))
            showFailureMessage(onResult, exception.message ?: exception.toString())
        }

    fun <T> runFlow(resFlow: Flow<T>, onResult: (Resource<ResultType>) -> Unit): Flow<T> {
        return resFlow.catch { e ->
            showFailureMessage(onResult, e.cause?.toString() ?: e.toString())
        }.flowOn(Dispatchers.IO)
    }

    private fun showFailureMessage(
        onResult: (Resource<ResultType>) -> Unit,
        message: String?,
        code: Int? = null
    ) {
        onResult.invoke(Resource.loading(false))
        onResult.invoke(Resource.failure(message, code))
    }

    fun invoke(
        scope: CoroutineScope,
        params: Params? = null,
        onResult: (Resource<ResultType>) -> Unit = {}
    ) {
        scope.launch(handler(onResult) + Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            runFlow(executeRemote(params), onResult).collect {
                if (it.isSuccess == true) {
                    val mapper = mapper(it)
                    onResult.invoke(Resource.success(mapper))
                    onResult.invoke(Resource.loading(false))
                } else {
                    if (!it.error?.message.isNullOrBlank())
                        showFailureMessage(onResult, it.error?.message)
                    else
                        showFailureMessage(onResult, "something wrong happened, please try again")
                }
            }
        }
    }

}
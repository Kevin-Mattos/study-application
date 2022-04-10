package com.example.datasource.adapter

import android.accounts.NetworkErrorException
import com.example.datasource.Resource
import com.example.datasource.models.response.ApiException
import com.example.datasource.models.response.ErrorResponse
import com.example.datasource.models.response.fromJson
import com.google.gson.JsonSyntaxException
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

class ResourceCallAdapter(private val type: Type) :
	CallAdapter<Resource<Type>, ResourceCall<Resource<Type>>> {
	override fun responseType(): Type {
		return type
	}

	override fun adapt(call: Call<Resource<Type>>): ResourceCall<Resource<Type>> {
		return ResourceCall(call)
	}
}


class ResourceCall<R>(
	private val delegate: Call<R>
) : Call<Resource<R>> {

	override fun enqueue(callback: Callback<Resource<R>>) = delegate.enqueue(
		object : Callback<R> {

			override fun onResponse(call: Call<R>, response: Response<R>) {
				val result = if (response.isSuccessful) {
					mapSuccess(response)
				} else {
					mapError(response)
				}

				callback.onResponse(this@ResourceCall, Response.success(result))
			}

			override fun onFailure(call: Call<R>, throwable: Throwable) {
				val error = when (throwable) {
					is IOException -> NetworkErrorException(throwable)
					else -> throwable
				}
				callback.onResponse(this@ResourceCall, Response.success(Resource.error(error)))
			}
		}
	)


	fun mapSuccess(response: Response<R>): Resource<R> = Resource.success(response.body())

	fun mapError(response: Response<R>): Resource<R> {
		val error = response.errorBody()?.serializeErrorBody()
		return Resource.error(ApiException(error))
	}

	fun ResponseBody.serializeErrorBody(): ErrorResponse? {
		return try {
			charStream().fromJson<ErrorResponse>()
		} catch (e: JsonSyntaxException) {
			ErrorResponse()
		} catch (e: IOException) {
			ErrorResponse()
		}
	}

	override fun clone(): Call<Resource<R>> {
		return ResourceCall(delegate.clone())
	}

	override fun execute(): Response<Resource<R>> {
		throw NotImplementedError()
	}

	override fun isExecuted(): Boolean = delegate.isExecuted
	override fun isCanceled(): Boolean = delegate.isCanceled
	override fun cancel() = delegate.cancel()
	override fun request(): Request = delegate.request()

	override fun timeout(): Timeout =
		delegate.timeout()

}
package ru.sibsutis.mockapiserver.assets

import android.content.Context
import android.net.Uri
import android.util.Log
import java.io.InputStreamReader
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_OK
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer

internal fun getFake(context: Context, uri: Uri, response: Response.Builder): Response.Builder =
	when (uri.path) {
		"insert/your/request/here" -> {
			response.createResponse(
				description = context.readFileFromAssets(TestJsonAsset.testAsset),
				body = context.readFileFromAssets(TestJsonAsset.testAsset)
			)
		}

		"/groups_list"             -> {
			response.createResponse(
				description = context.readFileFromAssets(GroupJsonAsset.groupAsset),
				body = context.readFileFromAssets(GroupJsonAsset.groupAsset)
			)
		}

		"/teachers_list"           -> {
			response.createResponse(
				description = context.readFileFromAssets(TeachersJsonAsset.teachersAsset),
				body = context.readFileFromAssets(TeachersJsonAsset.teachersAsset)
			)
		}

		"/lessons/ис-841"          -> {
			response.createResponse(
				description = context.readFileFromAssets(LessonsJsonAsset.is841Asset),
				body = context.readFileFromAssets(LessonsJsonAsset.is841Asset)
			)
		}

		"/lessons/ив-821"          -> {
			response.createResponse(
				description = context.readFileFromAssets(LessonsJsonAsset.iv821Asset),
				body = context.readFileFromAssets(LessonsJsonAsset.iv821Asset)
			)
		}

		"/lessons/ив-822"          -> {
			response.createResponse(
				description = context.readFileFromAssets(LessonsJsonAsset.iv822Asset),
				body = context.readFileFromAssets(LessonsJsonAsset.iv822Asset)
			)
		}

		"/lessons/ив-823"          -> {
			response.createResponse(
				description = context.readFileFromAssets(LessonsJsonAsset.iv823Asset),
				body = context.readFileFromAssets(LessonsJsonAsset.iv823Asset)
			)
		}

		else                       -> {
			error404(response)
		}
	}

internal fun postFake(
	uri: Uri,
	response: Response.Builder,
	chain: Interceptor.Chain
): Response.Builder =
	when (uri.path) {
		"insert/your/request/here" -> {
			logBody(chain)
			response.createResponse(
				code = HTTP_OK,
				description = "Insert your message here",
				body = """{ "ok" : "ok" }"""
			)
		}

		else                       -> {
			error404(response)
		}
	}

internal fun Response.Builder.createResponse(
	code: Int = HTTP_OK,
	description: String,
	body: String? = null
) =
	this.code(code)
		.message(description)
		.apply {
			body?.let {
				body(it.toResponseBody("application/json".toMediaTypeOrNull()))
			}
		}

internal fun logBody(chain: Interceptor.Chain) {
	val requestBuffer = Buffer()
	chain.request().body?.writeTo(requestBuffer)
	Log.d("fakeDataInterceptor", requestBuffer.readUtf8())
}

internal fun error404(response: Response.Builder) =
	response.createResponse(
		code = HTTP_NOT_FOUND,
		description = "NOT API",
		body = """{ "error": "NOT API" }"""
	)

internal fun Context.readFileFromAssets(path: String): String =
	assets.open(path)
		.reader()
		.use(InputStreamReader::readText)

internal fun <K> Response.Builder.createWithQuery(
	key: K,
	context: Context,
	queryByJson: Map<K, String>,
	defaultResponsePath: String = "",
	response: Response.Builder
): Response.Builder {
	val responseByKey = queryByJson[key]
	return when {
		responseByKey != null         -> {
			createResponse(
				code = HTTP_OK,
				description = context.readFileFromAssets(responseByKey),
				body = context.readFileFromAssets(responseByKey)
			)
		}

		defaultResponsePath.isEmpty() -> {
			error404(response)
		}

		else                          -> {
			createResponse(
				code = HTTP_OK,
				description = context.readFileFromAssets(defaultResponsePath),
				body = context.readFileFromAssets(defaultResponsePath)
			)
		}
	}
}
package ru.sibsutis.mockapiserver.changer

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

const val MOCK = "MOCK_SERVER"
const val ORIGINAL = "ORIGINAL_SERVER"

class NetworkChanger(context: Context) {

	private val preferences = context.getSharedPreferences("NETWORK_CHANGER", Context.MODE_PRIVATE)

	var networkState: String = preferences.getString("NETWORK_STATE", ORIGINAL) ?: ORIGINAL
		private set

	fun switchNetworkType(mocksEnabled: Boolean) {
		this.networkState = if (mocksEnabled) {
			MOCK
		} else {
			ORIGINAL
		}
		preferences.edit {
			putString("NETWORK_STATE", this@NetworkChanger.networkState)
			commit()
		}
	}
}

inline fun <reified T> Scope.getRetrofit(attachedValue: String? = null): T {
	val qualifier = attachedValue ?: get<NetworkChanger>().networkState
	Log.d("getRetrofit", qualifier)
	return get(named(qualifier))
}
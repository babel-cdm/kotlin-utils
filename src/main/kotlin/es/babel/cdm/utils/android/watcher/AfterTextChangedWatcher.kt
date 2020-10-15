package es.babel.cdm.utils.android.watcher

import android.text.Editable
import android.text.TextWatcher

class AfterTextChangedWatcher(private val afterTextChanged: (s: Editable?) -> Unit) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Nothing to do
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Nothing to do
    }

    override fun afterTextChanged(s: Editable?) {
        afterTextChanged.invoke(s)
    }
}

package es.babel.cdm.utils.android.watcher

import android.text.SpanWatcher
import android.text.Spannable

class OnSpanChangedWatcher(
    private val onSpanChanged: (
        text: Spannable?,
        what: Any?,
        oStart: Int,
        oEnd: Int,
        nStart: Int,
        nEnd: Int
    ) -> Unit
) : SpanWatcher {
    override fun onSpanAdded(text: Spannable?, what: Any?, start: Int, end: Int) {
        // Nothing to do
    }

    override fun onSpanRemoved(text: Spannable?, what: Any?, start: Int, end: Int) {
        // Nothing to do
    }

    override fun onSpanChanged(
        text: Spannable?,
        what: Any?,
        oStart: Int,
        oEnd: Int,
        nStart: Int,
        nEnd: Int
    ) {
        onSpanChanged.invoke(text, what, oStart, oEnd, nStart, nEnd)
    }
}

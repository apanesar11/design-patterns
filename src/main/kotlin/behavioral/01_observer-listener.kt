import kotlin.properties.Delegates

interface TextChangedListener {
    fun onTextChanged(oldText: String, newText: String)
}

class PrintingTextChangedListener : TextChangedListener {
    private var text = ""
    override fun onTextChanged(oldText: String, newText: String) {
        text = newText
        println("Text changed from \"$oldText\" to \"$newText\"");
    }
}

class TextView {
    val listeners = mutableListOf<TextChangedListener>()
    var text: String by Delegates.observable("") { _, old, new ->
        listeners.forEach { it.onTextChanged(old, new) }
    }
}

fun main() {
    val listener = PrintingTextChangedListener()

    val textView = TextView().apply {
        listeners.add(listener)
    }
    with(textView) {
        text = "First text"
        text = "Second text"
    }
}

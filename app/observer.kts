/*
Наблюдатель — это поведенческий паттерн проектирования, который создаёт механизм подписки, позволяющий одним объектам следить и реагировать на события,
происходящие в других объектах.
 */


/*
Наблюдатель используется для передачи событий между объектами текстового редактора. Всякий раз когда объект редактора меняет своё состояние, он оповещает своих наблюдателей.
 */


import java.lang.Exception

class EventManager(vararg operations: String) {
    var listeners: MutableMap<String, MutableList<EventListener>> = HashMap()
    fun subscribe(eventType: String, listener: EventListener) {
        val users: MutableList<EventListener> = listeners[eventType]!!
        users.add(listener)
    }

    fun unsubscribe(eventType: String, listener: EventListener) {
        val users: MutableList<EventListener> = listeners[eventType]!!
        users.remove(listener)
    }

    fun notify(eventType: String, file: File?) {
        val users: List<EventListener> = listeners[eventType]!!
        for (listener in users) {
            listener.update(eventType, file)
        }
    }

    init {
        for (operation in operations) {
            listeners[operation] = ArrayList()
        }
    }
}


class Editor {
    var events: EventManager
    private var file: File? = null
    fun openFile(filePath: String?) {
        file = File(filePath)
        events.notify("open", file)
    }

    @Throws(Exception::class)
    fun saveFile() {
        if (file != null) {
            events.notify("save", file)
        } else {
            throw Exception("Please open a file first.")
        }
    }

    init {
        events = EventManager("open", "save")
    }
}


interface EventListener {
    fun update(eventType: String?, file: File?)
}
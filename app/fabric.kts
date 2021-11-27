/*
Абстрактная фабрика — это порождающий паттерн проектирования, который решает проблему создания целых семейств связанных продуктов,
без указания конкретных классов продуктов.
 */


/*
В роли двух семейств продуктов выступают кнопки и чекбоксы
 */


interface Button {
    fun paint()
}

interface Checkbox {
    fun paint()
}


interface GUIFactory {
    fun createButton(): Button?
    fun createCheckbox(): Checkbox?
}


class Application(factory: GUIFactory) {
    private val button: Button?
    private val checkbox: java.awt.Checkbox?
    fun paint() {
        button!!.paint()
        checkbox!!.paint()
    }

    init {
        button = factory.createButton()
        checkbox = factory.createCheckbox()
    }
}
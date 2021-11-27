/*
Адаптер — это структурный паттерн проектирования, который позволяет объектам с несовместимыми интерфейсами работать вместе
 */

/*
КруглоеОтверстие совместимо с КруглымиКолышками
 */

class RoundHole(val radius: Double) {

    fun fits(peg: RoundPeg): Boolean {
        val result: Boolean
        result = radius >= peg.getRadius()
        return result
    }
}


class RoundPeg {
    var radius = 0.0
        private set

    constructor() {}
    constructor(radius: Double) {
        this.radius = radius
    }
}
package com.example.hw4

import androidx.annotation.DrawableRes
import java.util.*

object Holder {
    private val name = arrayOf(
        "Адольф Гитлер",
        "Владимир Путин",
        "Сталин Иосиф",
        "Леонардо Давинчи",
        "Ада Лавлейс",
        "Хрущёв Никита",
        "Илон Маск",
        "Гиппократ",
        "Луи Дагер",
        "Курт Кабейн"
    )

    private val picture = arrayOf(
        R.drawable.person1,
        R.drawable.person2,
        R.drawable.person3,
        R.drawable.person4,
        R.drawable.person5,
        R.drawable.person6,
        R.drawable.person7,
        R.drawable.person8,
        R.drawable.person9,
        R.drawable.person10,

        )

    private val gender = arrayOf(
        "мужчина",
        "мужчина",
        "мужчина",
        "мужчина",
        "женщина",
        "мужчина",
        "мужчина",
        "мужчина",
        "мужчина",
        "мужчина"
    )

    private val date = arrayOf(
        "1889  - 1945",
        "1952 - 9999",
        "1878 - 1953",
        "1452 - 1519",
        "1815 - 1852",
        "1894 - 1971",
        "1971 — живёт ещё",
        "460 года до н. э. — 370 года до н. э.",
        "1787 — 1851",
        "1967 - 1994"
    )

    private val info = arrayOf(
        "Диктатор Нацистской Германии 1921—1945 годах, полководец и государственный деятель, немецкий государственный и политический деятель, основоположник и центральная фигура национал-социализма, основатель тоталитарной диктатуры нацистской Германии.",
        "Российский государственный, политический и военный деятель. Действующий Президент Российской Федерации, председатель Государственного Совета Российской Федерации и верховный главнокомандующий Вооружёнными силами Российской Федерации с 7 мая 2012 года.",
        "Российский революционер, советский политический, государственный, военный и партийный деятель.",
        "Итальянский художник и учёный, изобретатель, писатель, музыкант, один из крупнейших представителей искусства Высокого Возрождения, яркий пример «универсального человека».",
        "Английский математик. Известна прежде всего созданием описания вычислительной машины. Составила первую в мире программу. Считается первым программистом в истории.",
        "Советский государственный деятель. Первый секретарь ЦК КПСС. Председатель Совета министров СССР. Председатель Бюро ЦК КПСС по РСФСР. Герой Советского Союза, трижды Герой Социалистического Труда.",
        "Основатель, совладелец, генеральный директор и главный инженер компании SpaceX; генеральный директор и главный идейный вдохновитель компании Tesla.",
        "Древнегреческий целитель, врач и философ. Вошёл в историю как «отец медицины». Гиппократ является исторической личностью. Упоминания о «великом враче-асклепиаде» встречаются в произведениях его современников — Платона и Аристотеля. Собранные в т. н.",
        "Французский художник, химик и изобретатель, один из создателей фотографии.",
        "Американский рок-музыкант, вокалист, гитарист и автор песен. Наиболее известен как основатель и лидер рок-группы «Нирвана». В середине 1980-х годов Кобейн начал увлекаться панк-роком, а в 1987 году вместе с Кристом Новоселичем образовал группу «Нирвана»."
    )

    fun createCollectionPerson(): ArrayList<Person> {
        val persons: ArrayList<Person> = ArrayList<Person>()
        for (i in 0..9) {
            val person = Person(
                name[i],
                picture[i],
                gender[i],
                date[i],
                info[i]
            )
            persons.add(person)
        }
        return persons
    }
}

data class Person(
    val name: String,
    @DrawableRes val pictureRes: Int,
    val gender: String,
    val date: String,
    val info: String
)
package com.niks0n.effectivemobile.utils

import com.niks0n.effectivemobile.R

fun offerIcon(offerName: String): Int =
    when (offerName) {
        "near_vacancies" -> R.drawable.vacancy_near_icon
        "level_up_resume" -> R.drawable.offer_icon
        "temporary_job" -> R.drawable.time_work_icon
        else -> R.drawable.books_icon
    }

fun intToMonth(numberMonth: Int): String =
    when (numberMonth) {
        1 -> "января"
        2 -> "февраля"
        3 -> "марта"
        4 -> "апреля"
        5 -> "мая"
        6 -> "июня"
        7 -> "июля"
        8 -> "августа"
        9 -> "сентября"
        10 -> "октября"
        11 -> "ноября"
        12 -> "декабря"
        else -> ""
    }
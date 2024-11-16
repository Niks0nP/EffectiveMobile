package com.niks0n.data.mapper

import com.niks0n.data.models.Address
import com.niks0n.data.models.Button
import com.niks0n.data.models.Experience
import com.niks0n.data.models.JobData
import com.niks0n.data.models.Offer
import com.niks0n.data.models.Salary
import com.niks0n.data.models.Vacancy
import com.niks0n.domain.models.AddressModel
import com.niks0n.domain.models.ButtonModel
import com.niks0n.domain.models.ExperienceModel
import com.niks0n.domain.models.JobDataModel
import com.niks0n.domain.models.OfferModel
import com.niks0n.domain.models.SalaryModel
import com.niks0n.domain.models.VacancyModel

fun JobData.toJobDataModel() : JobDataModel {
    return JobDataModel(
        offers = offers.map { it.toOfferModel() },
        vacancies = vacancies.map { it.toVacancyModel() }
    )
}

fun Offer.toOfferModel(): OfferModel {
    return OfferModel(
        id = id ?: "",
        title = title,
        link = link,
        button = button?.toButtonModel() ?: ButtonModel("")
    )
}

fun Button.toButtonModel(): ButtonModel {
    return ButtonModel(
        text = text
    )
}

fun Vacancy.toVacancyModel(): VacancyModel {
    return VacancyModel(
        id = id,
        lookingNumber = lookingNumber ?: 0,
        title = title,
        address = address.toAddressModel(),
        company = company,
        experience = experience.toExperienceModel(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salary.toSalaryModel(),
        schedules = schedules,
        appliedNumber = appliedNumber ?: 0,
        description = description ?: "",
        responsibilities = responsibilities,
        questions = questions
    )
}

fun Address.toAddressModel(): AddressModel {
    return AddressModel(
        town = town,
        street = street,
        house = house
    )
}

fun Experience.toExperienceModel(): ExperienceModel {
    return ExperienceModel(
        previewText = previewText,
        text = text
    )
}

fun Salary.toSalaryModel(): SalaryModel {
    return SalaryModel(
        short = short ?: "",
        full = full
    )
}
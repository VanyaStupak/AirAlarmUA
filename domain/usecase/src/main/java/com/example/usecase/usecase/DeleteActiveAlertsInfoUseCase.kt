package com.example.usecase.usecase

interface DeleteActiveAlertsInfoUseCase {
    suspend operator fun invoke(titles: List<String>)
}

package com.example.impl

import dev.stupak.repository.AlertsRepository
import dev.stupak.repository.model.RepositoryAlertsList
import dev.stupak.usecase.impl.usecase.GetAlertsHistoryUseCaseImpl
import dev.stupak.usecase.model.DomainAlertsList
import dev.stupak.usecase.usecase.GetAlertsHistoryUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetAlertsHistoryUseCaseTest {
    private lateinit var getAlertsHistoryUseCase: GetAlertsHistoryUseCase
    private val alertsRepository: AlertsRepository = mockk()

    @Before
    fun setUp() {
        getAlertsHistoryUseCase = GetAlertsHistoryUseCaseImpl(alertsRepository)
    }

    @Test
    fun `invoke should return success with transformed data`() =
        runTest {
            val uid = 5
            val period = "month"
            val domainAlertsList = DomainAlertsList(emptyList())
            val repositoryList = RepositoryAlertsList(emptyList())

            coEvery { alertsRepository.getAlertsForPeriod(uid, period) } returns repositoryList

            val result: Result<DomainAlertsList> = getAlertsHistoryUseCase.invoke(uid, period)

            assertTrue(result.isSuccess)
            assertEquals(domainAlertsList, result.getOrNull())
        }

    @Test
    fun `invoke should return failure when repository call throws exception`() =
        runTest {
            val uid = 5
            val period = "month"
            val exception = IOException("Network error")

            coEvery { alertsRepository.getAlertsForPeriod(uid, period) } throws exception

            val result = getAlertsHistoryUseCase(uid, period)

            assertEquals(Result.failure<DomainAlertsList>(exception), result)
        }
}

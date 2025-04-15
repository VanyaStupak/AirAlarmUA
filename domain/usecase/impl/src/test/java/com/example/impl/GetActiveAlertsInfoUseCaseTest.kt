package com.example.impl

import dev.stupak.repository.AlertsRepository
import dev.stupak.repository.model.RepositoryAlertsList
import dev.stupak.usecase.impl.usecase.GetActiveAlertsInfoUseCaseImpl
import dev.stupak.usecase.model.DomainAlertsList
import dev.stupak.usecase.usecase.GetActiveAlertsInfoUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetActiveAlertsInfoUseCaseTest {
    private lateinit var useCase: GetActiveAlertsInfoUseCase
    private val alertsRepository: AlertsRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetActiveAlertsInfoUseCaseImpl(alertsRepository)
    }

    @Test
    fun `invoke should return success with transformed data`() =
        runTest {
            val domainAlertsList = DomainAlertsList(emptyList())
            val repositoryList = RepositoryAlertsList(emptyList())

            coEvery { alertsRepository.getActiveAlertsInfo() } returns flowOf(repositoryList)

            val resultFlow: Flow<Result<DomainAlertsList>> = useCase.invoke()

            resultFlow.collect { result ->
                assertTrue(result.isSuccess)
                assertEquals(domainAlertsList, result.getOrNull())
            }
        }

    @Test
    fun `invoke should return failure when repository throws an exception`() =
        runTest {
            val exception = IOException("Network error")
            coEvery { alertsRepository.getActiveAlertsInfo() } returns flow { throw exception }

            val resultFlow: Flow<Result<DomainAlertsList>> = useCase.invoke()

            resultFlow.collect { result ->
                assertTrue(result.isFailure)
                assertEquals(exception, result.exceptionOrNull())
            }
        }
}

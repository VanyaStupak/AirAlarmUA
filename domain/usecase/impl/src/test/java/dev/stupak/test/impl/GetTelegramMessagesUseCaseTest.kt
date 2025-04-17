package dev.stupak.test.impl

import dev.stupak.repository.TelegramRepository
import dev.stupak.repository.model.TelegramRepositoryModel
import dev.stupak.usecase.impl.usecase.GetTelegramMessagesUseCaseImpl
import dev.stupak.usecase.usecase.GetTelegramMessagesUseCase
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

class GetTelegramMessagesUseCaseTest {
    private lateinit var useCase: GetTelegramMessagesUseCase
    private val telegramRepository: TelegramRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetTelegramMessagesUseCaseImpl(telegramRepository)
    }

    @Test
    fun `invoke should return success with transformed data`() =
        runTest {
            val telegramMessages = listOf("Message 1", "Message 2", "Message 3")
            val telegramMessageEntities = telegramMessages.map { TelegramRepositoryModel(it) }

            coEvery { telegramRepository.getTgAlerts() } returns flowOf(telegramMessageEntities)

            val resultFlow: Flow<Result<List<String>>> = useCase.invoke()

            resultFlow.collect { result ->
                assertTrue(result.isSuccess)
                assertEquals(telegramMessages, result.getOrNull())
            }
        }

    @Test
    fun `invoke should return failure when repository throws an exception`() =
        runTest {
            val exception = IOException("Network error")

            coEvery { telegramRepository.getTgAlerts() } returns
                flow {
                    throw exception
                }

            val resultFlow: Flow<Result<List<String>>> = useCase.invoke()

            resultFlow.collect { result ->
                assertTrue(result.isFailure)
                assertEquals(exception, result.exceptionOrNull())
            }
        }
}

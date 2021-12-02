package com.bravedroid.jobby.auth

import com.bravedroid.jobby.auth.dto.login.toLoginRequestDto
import com.bravedroid.jobby.auth.dto.register.toRegisterRequestDto
import com.bravedroid.jobby.domain.auth.Authentication
import com.bravedroid.jobby.domain.log.Logger
import com.bravedroid.jobby.domain.usecases.LoginUserUseCase.LoginRequest
import com.bravedroid.jobby.domain.usecases.LoginUserUseCase.LoginResponse
import com.bravedroid.jobby.domain.usecases.RegisterUserUseCase.RegistrationRequest
import com.bravedroid.jobby.domain.usecases.RegisterUserUseCase.RegistrationResponse
import com.bravedroid.jobby.domain.utils.DomainResult
import com.bravedroid.jobby.domain.utils.DomainResult.Companion.getDataOrNull
import com.bravedroid.jobby.domain.utils.DomainResult.Companion.mapToResultSuccessOrKeepSameResultError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthenticationImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenProvider: TokenProvider,
    private val logger: Logger,

    ) : Authentication {

    override fun register(registrationRequest: RegistrationRequest): Flow<DomainResult<RegistrationResponse>> {
        return authDataSource.registerUser(registrationRequest.toRegisterRequestDto())
            .map { result ->
                result.mapToResultSuccessOrKeepSameResultError {
                    logger.log(
                        tag = "AuthenticationImpl",
                        msg = "${(result.getDataOrNull() ?: "No Data")}"
                    )
                    RegistrationResponse.Registered
                }
            }
    }

    override fun login(loginRequest: LoginRequest): Flow<DomainResult<LoginResponse>> {

        return authDataSource.loginUser(loginRequest.toLoginRequestDto())
            .map { result ->
                result.mapToResultSuccessOrKeepSameResultError {
                    logger.log(
                        tag = "AuthenticationImpl",
                        msg = "${(result.getDataOrNull() ?: "No Data")}"
                    )
                    tokenProvider.accessToken = it.accessToken
                    LoginResponse.LoggedIn
                }
            }
    }

}

package com.dev.banoo10.di

import android.content.Context
import com.dev.banoo10.Database
import com.dev.banoo10.feature_auth.data.repository.AuthRepoImpl
import com.dev.banoo10.feature_auth.domain.repository.AuthRepo
import com.dev.banoo10.feature_auth.domain.use_case.AuthUseCases
import com.dev.banoo10.feature_auth.domain.use_case.login.*
import com.dev.banoo10.feature_auth.domain.use_case.otp_form.OtpFormSend
import com.dev.banoo10.feature_auth.domain.use_case.phone_form.PhoneFormSend
import com.dev.banoo10.feature_personalForm.data.repository.PersonalDataRepoImpl
import com.dev.banoo10.feature_personalForm.domain.repository.PersonalDataRepo
import com.dev.banoo10.feature_personalForm.domain.use_case.PersonalDataUseCase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAndroidDriver(@ApplicationContext applicationContext: Context): AndroidSqliteDriver {
        return AndroidSqliteDriver(Database.Schema, applicationContext, "user.db")
    }

    @Provides
    @Singleton
    fun provideSQLDelightDatabase(driver: AndroidSqliteDriver): Database {
        return Database(driver)
    }

//    @Provides
//    @Singleton
//    fun provideAuthRepo(database: Database): AuthRepo {
//        return AuthRepoImpl(database)
//    }

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
//            install(Auth){
//
//            }
        }
    }

    @Provides
    @Singleton
    fun provideAuthRepo(client: HttpClient, database: Database): AuthRepo {
        return AuthRepoImpl(client,database)
    }


    @Provides
    @Singleton
    fun provideAuthUseCases(repo: AuthRepo): AuthUseCases {
        return AuthUseCases(
            addUserLocal = AddUserLocal(repo),
            deleteUserLocal = DeleteUserLocal(repo),
            getUserLocal = GetUserLocal(repo),
            getAccTokenLocal = GetAccTokenLocal(repo),
            phoneFormSend = PhoneFormSend(repo),
            otpFormSend = OtpFormSend(repo)
        )
    }


    @Provides
    @Singleton
    fun providePersonalDataRepo(client: HttpClient, database: Database): PersonalDataRepo {
        return PersonalDataRepoImpl(client,database)
    }

    @Provides
    @Singleton
    fun providePersonalDataUseCase(repo: PersonalDataRepo): PersonalDataUseCase {
        return PersonalDataUseCase(repo)
    }


}
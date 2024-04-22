package com.mrjalal.randomgif.di.network.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrlQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiKeyQualifier

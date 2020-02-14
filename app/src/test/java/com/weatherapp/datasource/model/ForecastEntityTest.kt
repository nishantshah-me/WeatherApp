package com.weatherapp.datasource.model

import com.weatherapp.datasource.entity.mapToDomain
import com.weatherapp.forecastEntity
import org.junit.Assert.assertTrue
import org.junit.Test

class ForecastEntityTest {


    @Test
    fun `map entity to domain`() {
        // given

        // when
        val model = forecastEntity.mapToDomain()

        // then
        assertTrue(model.date == forecastEntity.date)

    }
}
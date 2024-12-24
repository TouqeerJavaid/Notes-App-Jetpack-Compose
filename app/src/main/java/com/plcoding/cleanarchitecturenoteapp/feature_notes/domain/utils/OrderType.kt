package com.plcoding.cleanarchitecturenoteapp.feature_notes.domain.utils

import androidx.room.FtsOptions.Order

sealed class OrderType {
    object Ascending : OrderType()
    object Descendig : OrderType()
}
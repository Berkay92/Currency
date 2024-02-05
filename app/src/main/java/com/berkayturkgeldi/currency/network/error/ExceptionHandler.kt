package com.berkayturkgeldi.currency.network.error

import com.berkayturkgeldi.currency.network.ErrorResponse
import com.berkayturkgeldi.currency.network.common.Err
import com.berkayturkgeldi.currency.network.common.Ok
import com.berkayturkgeldi.currency.network.common.Result
import com.haroldadmin.cnradapter.NetworkResponse

fun <T: Any> call(response: NetworkResponse<T, ErrorResponse>) : Result<T, CurrencyException> {
    return when (response) {
        is NetworkResponse.Success -> {
            if (response.code == 200) {
                Ok(response.body)
            } else {
                Err(CurrencyException.UnexpectedException)
            }
        }
        is NetworkResponse.ServerError -> {
            Err(CurrencyException.ServerException)
        }
        is NetworkResponse.NetworkError -> {
            Err(CurrencyException.NetworkException)
        }
        is NetworkResponse.UnknownError -> {
            Err(CurrencyException.UnkownException)
        }
        is NetworkResponse.Error -> {
            Err(CurrencyException.UnexpectedException)
        }
    }
}
package com.example.testmobile.utils

sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    fun apply(f: (L) -> Any, g: (R) -> Any) {
        when (this) {
            is Left -> f(a)
            is Right -> g(b)
        }
    }
}
fun <T, L, R> Either<L, R>.flatmap(f: (R) -> Either<L, T>): Either<L, T> {
    return when (this) {
        is Either.Right -> f(b)
        is Either.Left -> Either.Left(a)
    }
}

fun <L, R> Either<L, R>.map(f: (R) -> Unit) {
    when (this) {
        is Either.Right -> f(b)
    }
}

fun <L, R>Either<L, R>.right(): R? {
    return when (this) {
        is Either.Right -> b
        is Either.Left -> null
    }
}

fun <L, R>Either<L, R>.left(): L? {
    return when (this) {
        is Either.Right -> null
        is Either.Left -> a
    }
}
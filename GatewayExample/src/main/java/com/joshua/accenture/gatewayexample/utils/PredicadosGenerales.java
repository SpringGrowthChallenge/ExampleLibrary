package com.joshua.accenture.gatewayexample.utils;

import java.util.List;
import java.util.function.Predicate;

public class PredicadosGenerales {

    // Predicado para verificar si una cadena está vacía
    public static final Predicate<String> isEmpty = String::isEmpty;

    // Predicado para verificar si una cadena no está vacía
    public static final Predicate<String> isNotEmpty = s -> !s.isEmpty();

    // Predicado para verificar si un número es positivo
    public static final Predicate<Integer> isPositive = n -> n > 0;

    // Predicado para verificar si un número es negativo
    public static final Predicate<Integer> isNegative = n -> n < 0;

    // Predicado para verificar si un número es par
    public static final Predicate<Integer> isEven = n -> n % 2 == 0;

    // Predicado para verificar si un número es impar
    public static final Predicate<Integer> isOdd = n -> n % 2 != 0;

    // Predicado para verificar si una cadena contiene solo dígitos
    public static final Predicate<String> isNumeric = s -> s.chars().allMatch(Character::isDigit);

    // Predicado para verificar si una cadena contiene solo letras
    public static final Predicate<String> isAlphabetic = s -> s.chars().allMatch(Character::isLetter);

    // Predicado para verificar si una lista está vacía
    public static final Predicate<List<?>> isListEmpty = List::isEmpty;

    // Predicado para verificar si una lista no está vacía
    public static final Predicate<List<?>> isListNotEmpty = l -> !l.isEmpty();
}
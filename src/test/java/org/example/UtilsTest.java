package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class UtilsTest {
    @ParameterizedTest
    @ValueSource(strings = { "", "a", "aa", "aba", "abba", "abcba" })
    void isPalindrome_true(String text) {
        Assertions.assertTrue(Utils.isPalindrome(text));
    }

    @ParameterizedTest
    @ValueSource(strings = { "ab", "ba", "abc", "abcbd" })
    void isPalindrome_false(String text) {
        Assertions.assertFalse(Utils.isPalindrome(text));
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "a", "aa", "bbbbb" })
    void isSameLetter_true(String text) {
        Assertions.assertTrue(Utils.isSameLetter(text));
    }

    @ParameterizedTest
    @ValueSource(strings = { "ab", "aab", "aaaaaba", "aaaabaaaaa" })
    void isSameLetter_false(String text) {
        Assertions.assertFalse(Utils.isSameLetter(text));
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "a", "aa", "aab", "abc", "aabb", "abb", "abcd" })
    void isLexicographicallyOrdered_true(String text) {
        Assertions.assertTrue(Utils.isLexicographicallyOrdered(text));
    }

    @ParameterizedTest
    @ValueSource(strings = { "ba", "aba", "abcb", "abcdeabc" })
    void isLexicographicallyOrdered_false(String text) {
        Assertions.assertFalse(Utils.isLexicographicallyOrdered(text));
    }
}
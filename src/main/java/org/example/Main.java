package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;


public class Main {

    public static AtomicInteger beautifulWordsOfLengthThreeCount = new AtomicInteger();
    public static AtomicInteger beautifulWordsOfLengthFourCount = new AtomicInteger();
    public static AtomicInteger beautifulWordsOfLengthFiveCount = new AtomicInteger();

    public static void main(String[] args) {
        final int TEXTS_NUMBER = 100_000;
        final int LEN_LOWER_BOUND = 3;
        final int LEN_UPPER_BOUND = 6;
        Random random = new Random();
        List<Thread> threads = new ArrayList<>();

        String[] texts = new String[TEXTS_NUMBER];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", LEN_LOWER_BOUND + random.nextInt(LEN_UPPER_BOUND - LEN_LOWER_BOUND));
        }

        Thread thread1 = new Thread(() -> checkForBeauty(texts, Utils::isPalindrome));
        threads.add(thread1);
        thread1.start();

        Thread thread2 = new Thread(() -> checkForBeauty(texts, Utils::isLexicographicallyOrdered));
        threads.add(thread2);
        thread2.start();

        Thread thread3 = new Thread(() -> checkForBeauty(texts, Utils::isSameLetter));
        threads.add(thread3);
        thread3.start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println("Красивых слов с длиной 3: " + beautifulWordsOfLengthThreeCount.get());
        System.out.println("Красивых слов с длиной 4: " + beautifulWordsOfLengthFourCount.get());
        System.out.println("Красивых слов с длиной 5: " + beautifulWordsOfLengthFiveCount.get());
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void checkForBeauty(String[] texts, Predicate<String> predicate) {
        for (String text : texts) {
            if (predicate.test(text)) {
                switch (text.length()) {
                    case 3:
                        beautifulWordsOfLengthThreeCount.incrementAndGet();
                        break;
                    case 4:
                        beautifulWordsOfLengthFourCount.incrementAndGet();
                        break;
                    case 5:
                        beautifulWordsOfLengthFiveCount.incrementAndGet();
                        break;
                    default:
                        // do nothing
                }
            }
        }
    }
}
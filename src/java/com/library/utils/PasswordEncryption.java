/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.utils;

/**
 *
 * @author Blaccop Group
 */
public class PasswordEncryption {
    private final char[] alphabets = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public String encryptPassword(String word) {
        StringBuilder encryptedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < alphabets.length; j++) {
                if (word.charAt(i) == alphabets[j]) {
                    int index = j + 1;
                    int hashValue = 0;
                    if (index % 2 == 0) {
                        hashValue = index / 2;
                        encryptedWord.append("e").append(hashValue);
                    } else {
                        hashValue = (index * 3) + 1;
                        encryptedWord.append("o").append(hashValue);
                    }
                }
            }
        }
        return encryptedWord.toString();
    }

    public String decryptPassword(String encryptedWord) {
        StringBuilder decryptedWord = new StringBuilder();
        for (int i = 0; i < encryptedWord.length(); i++) {
            StringBuilder value = new StringBuilder();
            int j = i+1;
            if (encryptedWord.charAt(i) == 'e') {
                while(j < encryptedWord.length() && encryptedWord.charAt(j) != 'e' && encryptedWord.charAt(j) != 'o'){
                    value.append(encryptedWord.charAt(j));
                    j++;
                }
                int hashValue = (Integer.parseInt(value.toString()) * 2) - 1;
                decryptedWord.append(alphabets[hashValue]);
            } else if (encryptedWord.charAt(i) == 'o') {
                while(j < encryptedWord.length() && encryptedWord.charAt(j) != 'e' && encryptedWord.charAt(j) != 'o'){
                    value.append(encryptedWord.charAt(j));
                    j++;
                }
                int hashValue = ((Integer.parseInt(value.toString()) - 1) / 3) - 1;
                decryptedWord.append(alphabets[hashValue]);
            }
        }
        return decryptedWord.toString();
    }
}

package com.webkaps.user.util;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserUtility {

    public String getUserPwd(){
        String upperCaseLetters = RandomStringUtils .random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(1, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(1);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                                                .concat(numbers)
                                                .concat(specialChar)
                                                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }
}

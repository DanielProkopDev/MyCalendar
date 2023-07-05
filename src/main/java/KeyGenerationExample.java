import Data.Day;
import Service.DayService;
import io.jsonwebtoken.security.Keys;

import java.util.List;

public class KeyGenerationExample {
    public static void main(String[] args) {

        DayService dayService = DayService.getInstance();

        Day day = new Day(2023, 6,20);

       // dayService.save(day);
        List<Day> dayList = dayService.findByDate(day.getYearDate(), day.getMonthDate(), day.getDayDate());

        Day day1 = dayService.findByYear(day.getYearDate());

        System.out.println(dayList.get(0));
       System.out.println(dayList.get(0));
        System.out.println(dayList.get(0));
        System.out.println(day1);

        /*
        // Generate a secure key with 256 bits
        byte[] keyBytes = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded();

        // Convert the key to a Base64-encoded string
        String base64Key = java.util.Base64.getEncoder().encodeToString(keyBytes);

        System.out.println("Generated Key: " + base64Key);*/
    }
    }


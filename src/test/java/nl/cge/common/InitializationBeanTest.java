package nl.cge.common;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class InitializationBeanTest {

    @Test
    void foo() {
        DayOfWeek dayOfWeek = LocalDate.now().minusDays(2).getDayOfWeek();
        String strDayOfWeek = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        System.out.println(strDayOfWeek);
    }

}
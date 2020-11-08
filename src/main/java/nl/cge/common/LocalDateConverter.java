package nl.cge.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    public Date convertToDatabaseColumn(LocalDate locDate) {
        return locDate == null ? null : Date.valueOf(locDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return sqlDate == null ? null : sqlDate.toLocalDate();
    }
}

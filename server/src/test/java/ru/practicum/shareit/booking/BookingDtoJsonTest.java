package ru.practicum.shareit.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class BookingDtoJsonTest {
    @Autowired
    private JacksonTester<BookingDto> json;


    @Test
    void testSerialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JacksonTester.initFields(this, objectMapper);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        var dto = new BookingDto();
        dto.setId(1);
        dto.setStart(LocalDateTime.of(2026, 12, 12, 12, 0, 0, 0));
        dto.setEnd(LocalDateTime.of(2027, 12, 12, 12, 0, 0, 0));
        dto.setItemId(1);

        var result = json.write(dto);
        assertThat(result).hasJsonPath("$.id");
        assertThat(result).hasJsonPath("$.start");
        assertThat(result).hasJsonPath("$.end");
        assertThat(result).hasJsonPath("$.itemId");
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(dto.getId());
        assertThat(result).extractingJsonPathStringValue("$.start").isEqualTo(dto.getStart().format(formatter));
        assertThat(result).extractingJsonPathStringValue("$.end").isEqualTo(dto.getEnd().format(formatter));
        assertThat(result).extractingJsonPathNumberValue("$.itemId").isEqualTo(dto.getItemId());
    }
}

package com.user.model.dto;

import com.user.model.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getStatusFriendship();
    }

    @Override
    public Status convertToEntityAttribute(String status) {
        if (status == null) {
            return null;
        }

        return Stream.of(Status.values())
                .filter(c -> c.getStatusFriendship().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

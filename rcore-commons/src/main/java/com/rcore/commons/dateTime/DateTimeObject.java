package com.rcore.commons.dateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DateTimeObject {

    private LocalDateTime dateTime;
    private LocalDateTime timeZone;
    private TimeObject timeObject;
    private DateObject dateObject;
    private Long dateTimeUtcZero;
    private Long dateTimeWithTimeZone;
}

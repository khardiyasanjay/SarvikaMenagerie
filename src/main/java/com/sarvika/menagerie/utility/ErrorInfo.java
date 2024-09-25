package com.sarvika.menagerie.utility;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorInfo {
    private String errorMessage;
    private Integer errorCode;
    private LocalDateTime timestamp;
}

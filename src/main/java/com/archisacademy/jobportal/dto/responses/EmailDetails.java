package com.archisacademy.jobportal.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String sender;
    private String receiver;
    private String subject;
    private String body;
    private String attachment;
}

package com.archisacademy.jobportal.dto;

import com.archisacademy.jobportal.enums.ConnectionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionDto {
    private ConnectionStatus status;
    private Timestamp requestDate;

}

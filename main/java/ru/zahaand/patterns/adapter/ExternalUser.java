package ru.zahaand.patterns.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExternalUser {
    private String id;
    private String contactInfo;
    private String personalInfo;
}

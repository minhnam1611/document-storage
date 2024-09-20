package com.vnpt.interceptor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailLogRequest {
    private Long id;

    private String senderId;

    private String title;

    private String body;

    private List<String> lstEmailRecipentString;
}

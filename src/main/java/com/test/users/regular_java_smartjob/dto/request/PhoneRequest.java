package com.test.users.regular_java_smartjob.dto.request;

public record PhoneRequest(
    String number,
    String cityCode,
    String countryCode
) {}

package com.ryoga.bbs.domain.model.log;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LogDate {
    private final LocalDate logDate;
    public LogDate(String dateStr) {
        try{
            LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new DomainLogicInvalidException("作業日付が不正な形式です。");
        }
        this.logDate = LocalDate.parse(dateStr);
    }

    public LocalDate value() {
        return logDate;
    }
}

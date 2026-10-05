package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class StartedDate {
    private final LocalDate startedDate;

    public StartedDate(String dateStr) {
        if(dateStr == null) {
            throw new DomainLogicInvalidException("着手日は必須です");
        }
        try{
            this.startedDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new DomainLogicInvalidException("着手日が不正な形式です。");
        }
    }

    public LocalDate value() {
        return startedDate;
    }
}

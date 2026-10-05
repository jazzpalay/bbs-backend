package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CompletedDate {
    private final LocalDate completedDate;

    public CompletedDate(String dateStr) {
        if(dateStr == null) {
            this.completedDate = null;
            return;
        }
        try{
            this.completedDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new DomainLogicInvalidException("完了日が不正な形式です。");
        }
    }

    public LocalDate value() {
        return completedDate;
    }
}

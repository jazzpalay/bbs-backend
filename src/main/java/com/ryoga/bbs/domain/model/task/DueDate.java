package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DueDate {
    private final LocalDate dueDate;

    public DueDate(String dateStr) {
        if(dateStr == null) {
            this.dueDate = null;
            return;
        }
        try{
            this.dueDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new DomainLogicInvalidException("完了予定日が不正な形式です。");
        }
    }

    public LocalDate value() {
        return dueDate;
    }
}

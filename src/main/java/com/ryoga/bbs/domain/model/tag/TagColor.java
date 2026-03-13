package com.ryoga.bbs.domain.model.tag;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import lombok.EqualsAndHashCode;

import java.util.regex.Pattern;

@EqualsAndHashCode
public class TagColor {

    private static final Pattern HEX_PATTERN =
            Pattern.compile("^#[0-9a-fA-F]{6}$");
    private final String value;

    public TagColor(String value){
        if(value == null){
            throw new DomainLogicInvalidException("カラーは必須です");
        }
        if(!HEX_PATTERN.matcher(value).matches()){
            throw new DomainLogicInvalidException("HEX値が不正です。");
        }
        this.value = value.toUpperCase();
    }

    public String value() {
        return value;
    }

}

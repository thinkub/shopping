package com.ming.shopping.product.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Converter
public class BooleanYnConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData);
    }
}

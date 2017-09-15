package com.hd.kzscrm.common.util;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * 支持两个日期格式的属性转换器
 * spring mvc自还的转换器仅支持一种格式
 * 日期转换为string时默认使用dateFormat2的格式
 * @Filename MulitFormatDateEditor.java
 * @Data 2016-11-02 17:04
 * @Author xing.shi
 */
public class MulitFormatDateEditor extends PropertyEditorSupport {
    private final DateFormat dateFormat;

    private final DateFormat dateFormat2;

    private final boolean allowEmpty;

    private final int exactDateLength;


    /**
     * Create a new CustomDateEditor instance, using the given DateFormat
     * for parsing and rendering.
     * <p>The "allowEmpty" parameter states if an empty String should
     * be allowed for parsing, i.e. get interpreted as null value.
     * Otherwise, an IllegalArgumentException gets thrown in that case.
     * @param dateFormat DateFormat to use for parsing and rendering
     * @param allowEmpty if empty strings should be allowed
     */
    public MulitFormatDateEditor(DateFormat dateFormat, DateFormat dateFormat2, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.dateFormat2 = dateFormat2;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
    }

    /**
     * Create a new CustomDateEditor instance, using the given DateFormat
     * for parsing and rendering.
     * <p>The "allowEmpty" parameter states if an empty String should
     * be allowed for parsing, i.e. get interpreted as null value.
     * Otherwise, an IllegalArgumentException gets thrown in that case.
     * <p>The "exactDateLength" parameter states that IllegalArgumentException gets
     * thrown if the String does not exactly match the length specified. This is useful
     * because SimpleDateFormat does not enforce strict parsing of the year part,
     * not even with {@code setLenient(false)}. Without an "exactDateLength"
     * specified, the "01/01/05" would get parsed to "01/01/0005". However, even
     * with an "exactDateLength" specified, prepended zeros in the day or month
     * part may still allow for a shorter year part, so consider this as just
     * one more assertion that gets you closer to the intended date format.
     * @param dateFormat DateFormat to use for parsing and rendering
     * @param allowEmpty if empty strings should be allowed
     * @param exactDateLength the exact expected length of the date String
     */
    public MulitFormatDateEditor(DateFormat dateFormat, DateFormat dateFormat2, boolean allowEmpty, int exactDateLength) {
        this.dateFormat = dateFormat;
        this.dateFormat2 = dateFormat2;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
    }


    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        }
        else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
            throw new IllegalArgumentException(
                    "Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
        }
        else {
            try {
                setValue(this.dateFormat.parse(text));
            }
            catch (ParseException ex) {
                try {
                    setValue(this.dateFormat2.parse(text));
                } catch (ParseException ex2) {
                    throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
                }
            }
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? this.dateFormat2.format(value) : "");
    }
}

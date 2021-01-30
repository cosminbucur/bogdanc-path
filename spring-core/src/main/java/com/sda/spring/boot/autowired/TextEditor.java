package com.sda.spring.boot.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextEditor {

    // HAS-A

    // field injection
    @Autowired
    private SpellChecker spellChecker;

    private TextFormatter textFormatter;

    private ImageConverter imageConverter;

    // constructor injection
    @Autowired
    public TextEditor(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }

    // delegation
    public void spellCheck() {
        spellChecker.checkSpelling();
    }

    public void format() {
        textFormatter.format();
    }

    public void convertImage() {
        imageConverter.convert();
    }

    // setter injection
    @Autowired
    public void setImageConverter(ImageConverter imageConverter) {
        this.imageConverter = imageConverter;
    }
}

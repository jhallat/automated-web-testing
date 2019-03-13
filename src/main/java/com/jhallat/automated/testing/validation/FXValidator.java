package com.jhallat.automated.testing.validation;

import javafx.scene.control.TextInputControl;

import java.util.ArrayList;
import java.util.List;

public class FXValidator {

    private List<TextInputControl> required = new ArrayList<>();


    public void addRequiredText(TextInputControl control) {
        required.add(control);
    }

    
}

package com.jhallat.automated.testing.validation;

import javafx.scene.control.TextInputControl;

import java.util.ArrayList;
import java.util.List;

public class FXValidator {

    private List<TextInputControl> required = new ArrayList<>();


    public void required(TextInputControl control) {
        if (control == null) {
            //TODO replace with logger
            System.err.println("Control is null");
        } else {
            required.add(control);
        }
    }

    public boolean validate() {
        for (TextInputControl control : required) {
            if (control.getText() == null || control.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

package com.vaadin.form;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class RegisterForm extends FormLayout {
    TextField username = new TextField("username");
    TextField password = new TextField("password");
    Button register = new Button("Register");

    public RegisterForm(){
        addClassName("login-form");
        register.addClickShortcut(Key.ENTER);
        add(username, password, register);
    }
}

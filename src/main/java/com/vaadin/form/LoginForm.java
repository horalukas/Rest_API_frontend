package com.vaadin.form;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class LoginForm extends FormLayout {
    TextField username = new TextField("username");
    TextField password = new TextField("password");
    Button login = new Button("Login");

    public LoginForm(){
        addClassName("login-form");
        login.addClickShortcut(Key.ENTER);
        add(username, password, login);
    }
}

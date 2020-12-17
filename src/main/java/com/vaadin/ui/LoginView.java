package com.vaadin.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.form.LoginForm;
import com.vaadin.resource.MovieGoerResource;
import org.springframework.beans.factory.annotation.Autowired;


@Route("")
public class LoginView extends VerticalLayout {
    private LoginForm form;
    private MovieGoerResource movieGoerResource;

    public LoginView(MovieGoerResource movieGoerResource) {
        this.movieGoerResource = movieGoerResource;
        addClassName("login-view");
        setSizeFull();
        setWidth("50%");
        form = new LoginForm(movieGoerResource);
        Anchor register = new Anchor("register", "Do not have an account yet? Register here");
        add(form, register);
    }

}
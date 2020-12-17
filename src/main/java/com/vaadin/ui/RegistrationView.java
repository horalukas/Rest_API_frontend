package com.vaadin.ui;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.form.RegisterForm;
import com.vaadin.resource.MovieGoerResource;

@Route("register")
public class RegistrationView extends VerticalLayout {
    private RegisterForm form;
    private MovieGoerResource movieGoerResource;

    public RegistrationView(MovieGoerResource movieGoerResource){
        this.movieGoerResource = movieGoerResource;
        addClassName("login-view");
        setSizeFull();
        setWidth("50%");
        form = new RegisterForm(movieGoerResource);
        Anchor login = new Anchor("", "Have an account already? Login here");
        add(form, login);
    }
}

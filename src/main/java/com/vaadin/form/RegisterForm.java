package com.vaadin.form;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.model.MovieGoerDTO;
import com.vaadin.model.MovieGoerModel;
import com.vaadin.resource.MovieGoerResource;
import org.springframework.web.client.HttpClientErrorException;

public class RegisterForm extends FormLayout {
    MovieGoerResource movieGoerResource;
    TextField username = new TextField("username");
    TextField password = new TextField("password");
    Button register = new Button("Register");

    public RegisterForm(MovieGoerResource movieGoerResource){
        this.movieGoerResource = movieGoerResource;
        addClassName("login-form");
        register.addClickShortcut(Key.ENTER);
        register.addClickListener(click -> validateAndRegister());
        add(username, password, register);
    }

    private void validateAndRegister(){
        MovieGoerDTO movieGoerDTO;
        try {
            movieGoerDTO = movieGoerResource.findByEmail(username.getValue());
        }catch(HttpClientErrorException e){
            if(username.getValue().equals("") || password.getValue().equals("")){
                Notification notification = new Notification("Username and password cant be blank", 3000);
                notification.open();
            }
            else{
                MovieGoerModel data = new MovieGoerModel();
                data.setEmail(username.getValue());
                data.setPassword(password.getValue());
                movieGoerResource.create(data);
                MovieGoerResource.currentUser = data.getEmail();
                register.getUI().ifPresent(ui -> ui.navigate("mainpage"));
            }
        }
        Notification notification = new Notification("Username is already in use", 3000);
        notification.open();
    }
}

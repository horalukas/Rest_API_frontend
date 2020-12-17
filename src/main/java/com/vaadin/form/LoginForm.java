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



public class LoginForm extends FormLayout {
    MovieGoerResource movieGoerResource;
    TextField username = new TextField("username");
    TextField password = new TextField("password");
    Button login = new Button("Login");


    public LoginForm(MovieGoerResource movieGoerResource){
        this.movieGoerResource = movieGoerResource;
        addClassName("login-form");
        login.addClickShortcut(Key.ENTER);
        login.addClickListener(click-> validateAndLogin());
        add(username, password, login);
    }

    private void validateAndLogin(){
        MovieGoerDTO movieGoerModel;
        try {
            movieGoerModel = movieGoerResource.findByEmail(username.getValue());
        }catch(HttpClientErrorException e){
            Notification notification = new Notification("Incorrect login information", 3000);
            notification.open();
            return;
        }
        if(!password.getValue().equals(movieGoerModel.getPassword())){
            Notification notification = new Notification("Incorrect login information", 3000);
            notification.open();
        }
        else{
            MovieGoerResource.currentUser = movieGoerModel.getEmail();
            if(movieGoerModel.getEmail().equals("admin")){
                login.getUI().ifPresent(ui -> ui.navigate("admin/screenings"));
            }
            else{
                login.getUI().ifPresent(ui -> ui.navigate("mainpage"));
            }
        }
    }
}

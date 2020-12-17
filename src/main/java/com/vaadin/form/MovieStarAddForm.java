package com.vaadin.form;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.model.MovieStarDTO;
import com.vaadin.model.MovieStarModel;
import com.vaadin.resource.MovieStarResource;
import org.springframework.web.client.HttpClientErrorException;


public class MovieStarAddForm extends FormLayout {
    private MovieStarResource movieStarResource;
    private MovieStarDTO movieStarDTO;
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    Button save = new Button("Add");

    public MovieStarAddForm(MovieStarResource movieStarResource){
        this.movieStarResource = movieStarResource;
        addClassName("specialni");
        add(firstName,
                lastName,
                createButtonsLayout());
    }

    public void setStar(MovieStarDTO movieStarDTO){
        this.movieStarDTO = movieStarDTO;
    }

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickShortcut(Key.ENTER);
        save.addClickListener(click-> validateAndSave());

        return new HorizontalLayout(save);
    }

    private void validateAndSave(){
        if(firstName.getValue()!=null && !firstName.getValue().equals("") && lastName.getValue()!=null && !lastName.getValue().equals("")){
            try {
                movieStarResource.findByName(firstName.getValue(), lastName.getValue());
            }catch (HttpClientErrorException e) {
                MovieStarModel movieStarModel = new MovieStarModel(firstName.getValue(), lastName.getValue());
                movieStarResource.create(movieStarModel);
            }
        }
    }

    private void formClose(){
        UI.getCurrent().getPage().reload();
    }
}

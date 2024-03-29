package com.vaadin.form;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import com.vaadin.model.MovieStarDTO;
import com.vaadin.model.MovieStarModel;
import com.vaadin.resource.MovieStarResource;
import org.atmosphere.config.service.Delete;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


public class MovieStarForm extends FormLayout {
    private MovieStarResource movieStarResource;
    private MovieStarDTO movieStarDTO;
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    Button save = new Button("Save");
    Button close = new Button("Cancel");

    public MovieStarForm(MovieStarResource movieStarResource){
        this.movieStarResource = movieStarResource;
        addClassName("form");
        add(firstName,
                lastName,
                createButtonsLayout());
    }

    public void setStar(MovieStarDTO movieStarDTO){
        this.movieStarDTO = movieStarDTO;
    }

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click-> validateAndSave());
        close.addClickListener(click-> formClose());

        return new HorizontalLayout(save, close);
    }

    private void validateAndSave(){
        if(firstName.getValue()!=null && !firstName.getValue().equals("") && lastName.getValue()!=null && !lastName.getValue().equals("")){
            try {
                movieStarResource.findByName(firstName.getValue(), lastName.getValue());
            }catch (HttpClientErrorException e){
                MovieStarModel movieStarModel = new MovieStarModel(firstName.getValue(), lastName.getValue());
                movieStarResource.update(movieStarModel, movieStarDTO.getId());
                UI.getCurrent().getPage().reload();
            }
        }
    }

    private void formClose(){
        UI.getCurrent().getPage().reload();
    }
}

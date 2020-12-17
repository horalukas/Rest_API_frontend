package com.vaadin.form;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.model.MovieModel;
import com.vaadin.model.MovieStarModel;
import com.vaadin.resource.MovieStarResource;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.util.List;


public class MovieForm extends FormLayout {

    MovieStarResource movieStarResource;
    TextField name = new TextField("Name");
    TextField director = new TextField("Director");
    IntegerField minutes = new IntegerField("Length");
    TextField rating = new TextField("Rating");
    MultiselectComboBox<Integer> starIds = new MultiselectComboBox<>("Stars");
    Button save = new Button("Save");
    Button close = new Button("Cancel");

    Binder<MovieModel> binder = new BeanValidationBinder<>(MovieModel.class);

    public MovieForm(List<Integer> stars, MovieStarResource movieStarResource){
        this.movieStarResource = movieStarResource;
        addClassName("form");
        binder.bindInstanceFields(this);
        starIds.setItems(stars);
        starIds.setItemLabelGenerator( star -> {
            MovieStarModel movieStarModel = movieStarResource.findById(star);
            return movieStarModel.getFirstName() + movieStarModel.getLastName();
        });
        add(name,
                director,
                minutes,
                rating,
                starIds,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, close);
    }
}

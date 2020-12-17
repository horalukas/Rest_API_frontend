package com.vaadin.form;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.model.MovieDTO;
import com.vaadin.model.MovieModel;
import com.vaadin.model.MovieStarDTO;
import com.vaadin.model.MovieStarModel;
import com.vaadin.resource.MovieResource;
import com.vaadin.resource.MovieStarResource;
import org.springframework.web.client.HttpClientErrorException;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class MovieAddForm extends FormLayout {

    MovieStarResource movieStarResource;
    MovieResource movieResource;
    TextField name = new TextField("Name");
    TextField director = new TextField("Director");
    IntegerField minutes = new IntegerField("Length");
    TextField rating = new TextField("Rating");
    MultiselectComboBox<Integer> starIds = new MultiselectComboBox<>("Stars");
    Button add = new Button("Add");


    public MovieAddForm(MovieStarResource movieStarResource, MovieResource movieResource){
        this.movieStarResource = movieStarResource;
        this.movieResource = movieResource;
        addClassName("form");
        starIds.setItems(movieStarResource.findAll().stream().map(MovieStarDTO::getId));
        add.addClickListener(click->validateAndCreate());
        add(name,
                director,
                minutes,
                rating,
                starIds,
                createButtonsLayout());
    }

    private void validateAndCreate() {
        try{
            movieResource.findByName(name.getValue());
        }catch (HttpClientErrorException e){
            if(name.getValue()!=null && name.getValue()!=""
                    && director.getValue()!=null && director.getValue()!="" &&
            minutes.getValue()!=null && minutes.getValue()>0 && rating.getValue()!=null
            && rating.getValue()!=""){
                Set<Integer> set = starIds.getValue();
                List<Integer> res = new ArrayList<>();
                for (int starId:set) {
                    res.add(starId);
                    try {
                        movieStarResource.findById(starId);
                    }catch (HttpClientErrorException exc){
                        return;
                    }
                }
                MovieModel movieModel = new MovieModel(name.getValue(), director.getValue(),
                minutes.getValue(), rating.getValue(), res);
                movieResource.create(movieModel);
                Notification notification = new Notification("Successfully created", 3000);
                notification.open();
            }
        }
    }

    private HorizontalLayout createButtonsLayout(){
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add.addClickShortcut(Key.ENTER);

        return new HorizontalLayout(add);
    }
}

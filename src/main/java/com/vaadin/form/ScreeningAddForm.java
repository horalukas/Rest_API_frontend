package com.vaadin.form;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.model.*;
import com.vaadin.resource.AuditoriumResource;
import com.vaadin.resource.MovieResource;
import com.vaadin.resource.ScreeningResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class ScreeningAddForm extends FormLayout {
    DateTimePicker dateTimePicker = new DateTimePicker("DateTime");
    Checkbox _3D = new Checkbox("3D");
    ComboBox<Integer> auditorium = new ComboBox<>("Auditorium");
    ComboBox<Integer> movie = new ComboBox<>("Movie");
    Button save = new Button("Add");
    MovieResource movieResource;
    AuditoriumResource auditoriumResource;
    ScreeningResource screeningResource;

    public ScreeningAddForm(MovieResource movieResource, AuditoriumResource auditoriumResource, ScreeningResource screeningResource){
        addClassName("form");
        this.auditoriumResource=auditoriumResource;
        this.movieResource = movieResource;
        this.screeningResource = screeningResource;
        auditorium.setItems(auditoriumResource.findAll().stream().map(AuditoriumDTO::getId));
        movie.setItems(movieResource.findAll().stream().map(MovieDTO::getId));
        save.addClickListener(click->validateAndCreate());
        add(dateTimePicker,
                _3D,
                auditorium,
                movie,
                createButtonsLayout());
    }

    Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    private void validateAndCreate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
        String dateString = format.format(convertToDateViaInstant(dateTimePicker.getValue()));
        Date date = null;
        try {
            date = format.parse(dateString);
        }catch (ParseException e){
            Notification notification = new Notification("Wrong date", 3000);
            notification.open();
        }
        ScreeningModel screeningModel = new ScreeningModel(date, _3D.getValue(), auditorium.getValue(), movie.getValue());
        screeningResource.create(screeningModel);
        Notification notification = new Notification("Successfully created", 3000);
        notification.open();
    }

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickShortcut(Key.ENTER);
        return new HorizontalLayout(save);
    }
}

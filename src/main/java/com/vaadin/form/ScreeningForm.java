package com.vaadin.form;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.model.AuditoriumModel;
import com.vaadin.model.MovieModel;

import java.util.List;


public class ScreeningForm extends FormLayout {

    DateTimePicker dateTimePicker = new DateTimePicker("DateTime");
    Checkbox _3D = new Checkbox("3D");
    ComboBox<Integer> auditorium = new ComboBox<>("Auditorium");
    ComboBox<Integer> movie = new ComboBox<>("Movie");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public ScreeningForm(){
        addClassName("form");
        add(dateTimePicker,
                _3D,
                auditorium,
                movie,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }
}

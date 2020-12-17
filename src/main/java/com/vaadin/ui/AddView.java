package com.vaadin.ui;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.form.MovieAddForm;
import com.vaadin.form.MovieStarAddForm;
import com.vaadin.form.ScreeningAddForm;
import com.vaadin.resource.AuditoriumResource;
import com.vaadin.resource.MovieResource;
import com.vaadin.resource.MovieStarResource;
import com.vaadin.resource.ScreeningResource;

@Route(value = "add", layout = AdminLayout.class)
public class AddView extends HorizontalLayout {
    private ScreeningAddForm screeningAddForm;
    private MovieAddForm movieAddForm;
    private MovieStarAddForm movieStarAddForm;
    private MovieResource movieResource;
    private MovieStarResource movieStarResource;
    private AuditoriumResource auditoriumResource;
    private ScreeningResource screeningResource;

    public AddView(MovieStarResource movieStarResource, MovieResource movieResource, AuditoriumResource auditoriumResource, ScreeningResource screeningResource){
        this.movieResource = movieResource;
        this.movieStarResource = movieStarResource;
        this.auditoriumResource = auditoriumResource;
        this.screeningResource = screeningResource;
        addClassName("addview");
        setSizeFull();
        screeningAddForm = new ScreeningAddForm( movieResource, auditoriumResource, screeningResource);
        movieAddForm = new MovieAddForm(movieStarResource, movieResource);
        movieStarAddForm = new MovieStarAddForm(movieStarResource);
        screeningAddForm.setWidth("40%");
        movieAddForm.setWidth("30%");
        movieStarAddForm.setWidth("30%");
        add(screeningAddForm, movieAddForm, movieStarAddForm);
    }

}

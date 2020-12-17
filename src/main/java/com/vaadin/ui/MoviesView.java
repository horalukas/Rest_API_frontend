package com.vaadin.ui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.form.MovieForm;
import com.vaadin.model.MovieModel;
import com.vaadin.model.MovieStarModel;
import com.vaadin.resource.MovieGoerResource;
import com.vaadin.resource.MovieResource;
import com.vaadin.resource.MovieStarResource;

import java.util.ArrayList;
import java.util.List;

@Route("admin/movies")
@CssImport("./styles/shared-styles.css")
public class MoviesView extends VerticalLayout {

    private MovieGoerResource movieGoerResource;
    private MovieResource movieResource;
    private MovieStarResource movieStarResource;
    private Grid<MovieModel> grid = new Grid<>(MovieModel.class);
    private MovieForm form;

    public MoviesView(MovieGoerResource movieGoerResource, MovieResource movieResource, MovieStarResource movieStarResource){
        this.movieGoerResource = movieGoerResource;
        this.movieResource = movieResource;
        this.movieStarResource = movieStarResource;
        addClassName("form-view");
        setSizeFull();
        configureGrid();
        List<MovieStarModel> stars = movieStarResource.findAll();
        List<Integer> starIds = new ArrayList<>();
        form = new MovieForm(starIds ,movieStarResource);
        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();
        add(content);
        updateList();
    }

    private void configureGrid(){
        grid.addClassName("form-grid");
        grid.setSizeFull();
        grid.setColumns("name", "director", "minutes", "rating");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList(){
        grid.setItems(movieResource.findAll());
    }
}

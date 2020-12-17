package com.vaadin.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.model.MovieModel;
import com.vaadin.resource.MovieGoerResource;
import com.vaadin.resource.MovieResource;

@Route("admin/movies")
public class MoviesView extends VerticalLayout {

    private MovieGoerResource movieGoerResource;
    private MovieResource movieResource;
    private Grid<MovieModel> grid = new Grid<>(MovieModel.class);

    public MoviesView(MovieGoerResource movieGoerResource, MovieResource movieResource){
        this.movieGoerResource = movieGoerResource;
        this.movieResource = movieResource;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(grid);
        updateList();
    }

    private void configureGrid(){
        grid.addClassName("movies-grid");
        grid.setSizeFull();
        grid.setColumns("name", "director", "minutes", "rating");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList(){
        grid.setItems(movieResource.findAll());
    }
}

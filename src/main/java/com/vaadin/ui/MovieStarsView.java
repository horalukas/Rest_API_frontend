package com.vaadin.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.model.MovieStarModel;
import com.vaadin.resource.MovieGoerResource;
import com.vaadin.resource.MovieStarResource;

@Route("admin/stars")
public class MovieStarsView extends VerticalLayout {

    private MovieStarResource movieStarResource;
    private MovieGoerResource movieGoerResource;
    private Grid<MovieStarModel> grid= new Grid<>(MovieStarModel.class);

    public MovieStarsView(MovieStarResource movieStarResource, MovieGoerResource movieGoerResource){
        this.movieGoerResource = movieGoerResource;
        this.movieStarResource = movieStarResource;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(grid);
        updateList();
    }

    private void configureGrid(){
        grid.addClassName("stars-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList(){
        grid.setItems(movieStarResource.findAll());
    }
}

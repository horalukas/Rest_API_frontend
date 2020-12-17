package com.vaadin.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.model.MovieModel;
import com.vaadin.model.ScreeningModel;
import com.vaadin.resource.MovieGoerResource;
import com.vaadin.resource.MovieResource;
import com.vaadin.resource.ScreeningResource;

@Route("mainpage")
public class CustomerView extends VerticalLayout {
    private MovieGoerResource movieGoerResource;
    private ScreeningResource screeningResource;
    private MovieResource movieResource;
    private Grid<ScreeningModel> grid = new Grid<>(ScreeningModel.class);

    public CustomerView(MovieGoerResource movieGoerResource, ScreeningResource screeningResource, MovieResource movieResource){
        this.movieGoerResource = movieGoerResource;
        this.screeningResource = screeningResource;
        this.movieResource = movieResource;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(grid);
        updateList();
    }

    private void configureGrid(){
        grid.addClassName("screenings-grid");
        grid.setSizeFull();
        grid.setColumns("time");
        grid.addColumn(screeningModel -> {
            MovieModel movieModel = movieResource.findById(screeningModel.getMovieId());
            return movieModel.getName();
        }).setHeader("Movie");
        grid.addColumn(ScreeningModel::getAuditoriumId).setHeader("Auditorium");
        grid.addColumn(screeningModel -> {
            return screeningModel.is_3D() ? "3D" : "2D";
        }).setHeader("3D");
        grid.getColumns().forEach(col-> col.setAutoWidth(true));
    }

    private void updateList(){
        grid.setItems(screeningResource.findAll());
    }
}

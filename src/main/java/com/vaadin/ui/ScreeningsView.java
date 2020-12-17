package com.vaadin.ui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.form.ScreeningForm;
import com.vaadin.model.MovieModel;
import com.vaadin.model.ScreeningDTO;
import com.vaadin.model.ScreeningModel;
import com.vaadin.resource.MovieGoerResource;
import com.vaadin.resource.MovieResource;
import com.vaadin.resource.ScreeningResource;

@Route(value = "admin/screenings", layout = AdminLayout.class)
@CssImport("./styles/shared-styles.css")
public class ScreeningsView extends VerticalLayout {

    private MovieGoerResource movieGoerResource;
    private ScreeningResource screeningResource;
    private MovieResource movieResource;
    private Grid<ScreeningDTO> grid = new Grid<>(ScreeningDTO.class);
    //private ScreeningForm screeningForm;

    public ScreeningsView(MovieGoerResource movieGoerResource, ScreeningResource screeningResource, MovieResource movieResource){
        this.movieGoerResource = movieGoerResource;
        this.screeningResource = screeningResource;
        this.movieResource = movieResource;
        addClassName("form-view");
        setSizeFull();
        configureGrid();
        //screeningForm = new ScreeningForm();
        Div content = new Div(grid);
        content.addClassName("content");
        content.setSizeFull();
        add(content);
        updateList();
    }

    private void configureGrid(){
        grid.addClassName("form-grid");
        grid.setSizeFull();
        grid.setColumns("time");
        grid.addColumn(screeningModel -> {
            MovieModel movieModel = movieResource.findById(screeningModel.getMovieId());
            return movieModel.getName();
        }).setHeader("Movie");
        grid.addColumn(ScreeningDTO::getAuditoriumId).setHeader("Auditorium");
        grid.addColumn(screeningModel -> {
            return screeningModel.is_3D() ? "3D" : "2D";
        }).setHeader("3D");
        grid.getColumns().forEach(col-> col.setAutoWidth(true));
    }

    private void updateList(){
        grid.setItems(screeningResource.findAll());
    }
}

package com.vaadin.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.model.AuditoriumModel;
import com.vaadin.resource.AuditoriumResource;
import com.vaadin.resource.MovieGoerResource;

@Route("admin/auditoriums")
public class AuditoriumsView extends VerticalLayout {
    private MovieGoerResource movieGoerResource;
    private AuditoriumResource auditoriumResource;
    private Grid<AuditoriumModel> grid = new Grid<>(AuditoriumModel.class);

    public AuditoriumsView(MovieGoerResource movieGoerResource, AuditoriumResource auditoriumResource){
        this.auditoriumResource = auditoriumResource;
        this.movieGoerResource = movieGoerResource;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(grid);
        updateList();
    }

    private void configureGrid(){
        grid.addClassName("auditoriums-grid");
        grid.setSizeFull();
        grid.setColumns("capacity");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList(){
        grid.setItems(auditoriumResource.findAll());
    }
}

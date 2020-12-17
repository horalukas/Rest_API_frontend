package com.vaadin.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.model.MovieModel;
import com.vaadin.model.MovieStarModel;
import com.vaadin.model.ScreeningModel;
import com.vaadin.model.TicketSeatModel;
import com.vaadin.resource.MovieGoerResource;
import com.vaadin.resource.MovieResource;
import com.vaadin.resource.ScreeningResource;
import com.vaadin.resource.TicketSeatResource;

import java.util.ArrayList;
import java.util.List;

@Route(value = "my-tickets", layout = UserLayout.class)
public class MyTicketsView extends VerticalLayout {

    private MovieGoerResource movieGoerResource;
    private TicketSeatResource ticketSeatResource;
    private ScreeningResource screeningResource;
    private MovieResource movieResource;
    private Grid<TicketSeatModel> grid= new Grid<>(TicketSeatModel.class);

    public MyTicketsView(MovieGoerResource movieGoerResource, TicketSeatResource ticketSeatResource, ScreeningResource screeningResource, MovieResource movieResource){
        this.movieGoerResource = movieGoerResource;
        this.ticketSeatResource = ticketSeatResource;
        this.screeningResource = screeningResource;
        this.movieResource = movieResource;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(grid);
        updateList();
    }

    private void configureGrid(){
        grid.addClassName("my-tickets-grid");
        grid.setSizeFull();
        grid.setColumns("number");
        grid.addColumn(ticketSeatModel -> {
            ScreeningModel screeningModel = screeningResource.findById(ticketSeatModel.getScreeningId());
            MovieModel movieModel = movieResource.findById(screeningModel.getMovieId());
            return movieModel.getName();
        }).setHeader("Movie");
        grid.addColumn(ticketSeatModel -> {
            ScreeningModel screeningModel = screeningResource.findById(ticketSeatModel.getScreeningId());
            return screeningModel.getTime();
        }).setHeader("DateTime");
        grid.addColumn(ticketSeatModel -> {
            ScreeningModel screeningModel = screeningResource.findById(ticketSeatModel.getScreeningId());
            return screeningModel.getAuditoriumId();
        }).setHeader("Auditorium");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList(){
        grid.setItems(ticketSeatResource.findAllByOwnerEmail(MovieGoerResource.currentUser));
    }
}

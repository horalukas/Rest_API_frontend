package com.vaadin.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.form.BuyTicketsForm;
import com.vaadin.resource.MovieGoerResource;
import com.vaadin.resource.TicketSeatResource;

@Route(value = "seating", layout = UserLayout.class)
public class SeatingView  extends VerticalLayout implements HasUrlParameter<Integer> {
    private Integer id;
    private TicketSeatResource ticketSeatResource;
    private MovieGoerResource movieGoerResource;
    private BuyTicketsForm buyTicketsForm;

    public SeatingView(MovieGoerResource movieGoerResource, TicketSeatResource ticketSeatResource){
        this.movieGoerResource = movieGoerResource;
        this.ticketSeatResource = ticketSeatResource;
        addClassName("login-view");
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        id = integer;
        buyTicketsForm = new BuyTicketsForm(id, ticketSeatResource, movieGoerResource);
        add(buyTicketsForm);
    }
}

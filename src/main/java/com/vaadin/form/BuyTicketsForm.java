package com.vaadin.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.model.TicketSeatDTO;
import com.vaadin.model.TicketSeatModel;
import com.vaadin.resource.MovieGoerResource;
import com.vaadin.resource.TicketSeatResource;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BuyTicketsForm extends FormLayout {
    MultiselectComboBox<Long> seatIds = new MultiselectComboBox<>("Available Seats");
    Button buy = new Button("Buy");
    TicketSeatResource ticketSeatResource;
    MovieGoerResource movieGoerResource;

    public BuyTicketsForm(int id, TicketSeatResource ticketSeatResource, MovieGoerResource movieGoerResource){
        this.ticketSeatResource = ticketSeatResource;
        this.movieGoerResource = movieGoerResource;
        List<TicketSeatDTO> seats = ticketSeatResource.findAllByScreeningId(id);
        List<TicketSeatDTO> freeSeats = new ArrayList<>();
        for (TicketSeatDTO seat:seats) {
            if(!seat.isTaken()){
                freeSeats.add(seat);
            }
        }
        seatIds.setItems(freeSeats.stream().map(TicketSeatDTO::getId));
        buy.addClickListener(click->validateAndBuy());
        add(seatIds, buy);
    }

    private void validateAndBuy() {
        Set<Long> ids = seatIds.getValue();
        for (Long id: ids) {
            TicketSeatModel ticketSeatModel = ticketSeatResource.findById(id);
            TicketSeatModel updated = new TicketSeatModel(ticketSeatModel.getNumber(),
                    true, movieGoerResource.findByEmail(MovieGoerResource.currentUser).getId(), ticketSeatModel.getScreeningId());
            ticketSeatResource.update(updated, id);
        }
        Notification notification = new Notification("Successfully bought", 3000);
        notification.open();
    }
}

package com.vaadin.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class AdminLayout extends AppLayout {
    Button logout = new Button("Log Out");
    public AdminLayout(){
        createHeader();
        createDrawer();
    }

    private void createHeader(){
        logout.addClickListener(click->userLogOut());
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }


    private void createDrawer(){
        RouterLink auditoriumsLink = new RouterLink("Auditoriums", AuditoriumsView.class);
        auditoriumsLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink moviesLink = new RouterLink("Movies", MoviesView.class);
        auditoriumsLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink starsLink = new RouterLink("Stars", MovieStarsView.class);
        auditoriumsLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink screeningsLink = new RouterLink("Screenings", ScreeningsView.class);
        auditoriumsLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink addLink = new RouterLink("Add", AddView.class);
        auditoriumsLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(auditoriumsLink, moviesLink, starsLink, screeningsLink, addLink));
    }

    private void userLogOut(){
        logout.getUI().ifPresent(ui -> ui.navigate(""));
    }
}

package com.vaadin.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.resource.MovieGoerResource;

public class UserLayout extends AppLayout {
    Button logout = new Button("Log Out");
    Button deleteAcc = new Button("Delete Account");
    private MovieGoerResource movieGoerResource;
    public UserLayout(MovieGoerResource movieGoerResource){
        this.movieGoerResource = movieGoerResource;
        createHeader();
        createDrawer();
    }
    private void createHeader(){
        logout.addClickListener(click->userLogOut());
        deleteAcc.addClickListener(click->deleteAccount());
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), deleteAcc, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }


    private void createDrawer(){
        RouterLink mainPageLink = new RouterLink("Main Page", CustomerView.class);
        mainPageLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink myTicketsLink = new RouterLink("My Tickets", MyTicketsView.class);
        myTicketsLink.setHighlightCondition(HighlightConditions.sameLocation());


        addToDrawer(new VerticalLayout(mainPageLink, myTicketsLink));
    }

    private void userLogOut(){
        logout.getUI().ifPresent(ui -> ui.navigate(""));
    }

    private void deleteAccount(){
        if(MovieGoerResource.currentUser!="admin"){
            movieGoerResource.deleteByEmail(MovieGoerResource.currentUser);
            MovieGoerResource.currentUser = "";
            deleteAcc.getUI().ifPresent(ui -> ui.navigate(""));
        }
    }

}

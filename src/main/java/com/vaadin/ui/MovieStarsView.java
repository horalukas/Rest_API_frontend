package com.vaadin.ui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.form.MovieStarForm;
import com.vaadin.model.MovieStarModel;
import com.vaadin.resource.MovieGoerResource;
import com.vaadin.resource.MovieStarResource;

@Route("admin/stars")
@CssImport("./styles/shared-styles.css")
public class MovieStarsView extends VerticalLayout {

    private MovieStarResource movieStarResource;
    private MovieGoerResource movieGoerResource;
    private Grid<MovieStarModel> grid= new Grid<>(MovieStarModel.class);
    private MovieStarForm form;

    public MovieStarsView(MovieStarResource movieStarResource, MovieGoerResource movieGoerResource){
        this.movieGoerResource = movieGoerResource;
        this.movieStarResource = movieStarResource;
        addClassName("form-view");
        setSizeFull();
        configureGrid();
        form = new MovieStarForm();
        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();
        add(content);
        updateList();
    }

    private void configureGrid(){
        grid.addClassName("form-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList(){
        grid.setItems(movieStarResource.findAll());
    }
}

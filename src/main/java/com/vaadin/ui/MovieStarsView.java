package com.vaadin.ui;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.form.MovieStarForm;
import com.vaadin.model.MovieStarDTO;
import com.vaadin.model.MovieStarModel;
import com.vaadin.resource.MovieGoerResource;
import com.vaadin.resource.MovieStarResource;

@Route(value = "admin/stars", layout = AdminLayout.class)
@CssImport("./styles/shared-styles.css")
public class MovieStarsView extends VerticalLayout {

    private MovieStarResource movieStarResource;
    private MovieGoerResource movieGoerResource;
    private Grid<MovieStarDTO> grid= new Grid<>(MovieStarDTO.class);
    private MovieStarForm form;

    public MovieStarsView(MovieStarResource movieStarResource, MovieGoerResource movieGoerResource){
        this.movieGoerResource = movieGoerResource;
        this.movieStarResource = movieStarResource;
        addClassName("form-view");
        setSizeFull();
        configureGrid();
        form = new MovieStarForm(movieStarResource);
        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();
        add(content);
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setVisible(false);
    }


    private void configureGrid(){
        grid.addClassName("form-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt-> editMovieStar(evt.getValue()));
    }

    private void editMovieStar(MovieStarDTO movieStarDTO){
        if(movieStarDTO==null){
            closeEditor();
            updateList();
        }else{
            form.setVisible(true);
            form.setStar(movieStarDTO);
        }
    }

    private void updateList(){
        grid.setItems(movieStarResource.findAll());
    }
}

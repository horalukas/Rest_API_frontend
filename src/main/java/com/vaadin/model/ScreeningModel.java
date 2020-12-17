package com.vaadin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Component
public class ScreeningModel {
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-hh-mm")
    @NotNull
    private Date time;
    @NotNull
    private boolean _3D;
    @NotNull
    private Integer auditoriumId;
    @NotNull
    private Integer movieId;

    public ScreeningModel() {
    }

    public ScreeningModel(Date time, boolean is3D, Integer auditoriumId, Integer movieId) {
        this.time = time;
        this._3D = is3D;
        this.auditoriumId = auditoriumId;
        this.movieId = movieId;
    }

    public Date getTime() {
        return time;
    }

    public boolean is_3D() { return _3D; }

    public Integer getAuditoriumId() { return auditoriumId; }

    public Integer getMovieId() { return movieId; }

    public void setTime(Date time) {
        this.time = time;
    }

    public void set_3D(boolean is3D) { this._3D = is3D; }

    public void setAuditoriumId(Integer auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
}

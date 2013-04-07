package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.jpa.Model;

@Entity
public class World extends Model {

    @Id
    public Long id;

    @Column(name = "randomNumber")
    public Long randomNumber;

}
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import com.google.gson.annotations.SerializedName;

/**
 * use a generic model as we want to explicitly define the id
 * 
 * @author tom
 * 
 */
@Entity
public class World extends GenericModel {

	public World(long i) {
		id = i;
	}

	@Id
	public Long id;

}
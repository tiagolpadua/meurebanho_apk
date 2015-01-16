package com.timsoft.meurebanho.animal.model;

import java.util.Date;
import java.util.List;

import android.graphics.Bitmap;

import com.timsoft.meurebanho.event.model.Evento;

public class Animal {
	private int id;
	private int specieId;
	private int raceId;
	private String name;
	private String earTag;
	private Date birthDate;
	private Date aquisitionDate;
	private Date sellDate;
	
	private double aquisitionValue;
	private double sellValue;
	
	private List<Bitmap> pictures;
	
	private List<Evento> events;
	
	public Animal() {
	}
	
	public Animal(int id, int specieId, int raceId, String name, String earTag, Date birthDate, 
			Date aquisitionDate, Date sellDate, double aquisitionValue, double sellValue) {
		this.id = id;
		this.specieId = specieId;
		this.raceId = raceId;
		this.name = name;
		this.earTag = earTag;
		this.birthDate = birthDate;
		this.aquisitionDate = aquisitionDate;
		this.sellDate = sellDate;
		this.aquisitionValue = aquisitionValue;
		this.sellValue = sellValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}

	public int getSpecieId() {
		return specieId;
	}

	public void setSpecieId(int specieId) {
		this.specieId = specieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEarTag() {
		return earTag;
	}

	public void setEarTag(String earTag) {
		this.earTag = earTag;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getAquisitionDate() {
		return aquisitionDate;
	}

	public void setAquisitionDate(Date aquisitionDate) {
		this.aquisitionDate = aquisitionDate;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public double getAquisitionValue() {
		return aquisitionValue;
	}

	public void setAquisitionValue(double aquisitionValue) {
		this.aquisitionValue = aquisitionValue;
	}

	public double getSellValue() {
		return sellValue;
	}

	public void setSellValue(double sellValue) {
		this.sellValue = sellValue;
	}

	public List<Bitmap> getPictures() {
		return pictures;
	}

	public void setPictures(List<Bitmap> pictures) {
		this.pictures = pictures;
	}

	public List<Evento> getEvents() {
		return events;
	}

	public void setEvents(List<Evento> events) {
		this.events = events;
	}
	
	@Override
	public String toString() {
		return "Animal [id=" + id + ", raceId=" + raceId + ", specieId="
				+ specieId + ", name=" + name + ", earTag=" + earTag
				+ ", birthDate=" + birthDate + ", aquisitionDate="
				+ aquisitionDate + ", sellDate=" + sellDate
				+ ", aquisitionValue=" + aquisitionValue + ", sellValue="
				+ sellValue + ", pictures=" + pictures + ", events=" + events
				+ "]";
	}

}

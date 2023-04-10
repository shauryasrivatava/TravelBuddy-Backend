package com.travel.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="trip")
@NoArgsConstructor
@Getter
@Setter
public class Trip {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="trip_id")
	private Integer tripId;
	
	@Column(name="title", length=100, nullable =false)
	private String title;
	
	@Column(length=1000)
	private String content;
	
	private String location;
	
	private String imageName;

	private String invoiceName;
	
	@Column(name="trip_expense")
	private double tripExpense;
	
	@ManyToOne
	private UserProfile user;
	
	@OneToMany(mappedBy="trip", cascade= CascadeType.ALL )
	private List<Comment> comments;

}

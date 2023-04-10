package com.travel.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "userProfile")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="email")
	private String username;
	
	@Column
	@JsonIgnore
	private String password;
	
    @Column(name = "firstName", length = 100)
    private String firstName;

    @Column(name = "lastName", length = 100)
    private String lastName;

    @Column(name = "aboutMe", length = 10000)
    private String aboutMe;

    @Column(name = "contact")
    private Integer contact;

    @Column(name = "gender")
    private String gender;

    @Column(name = "location")
    private String location;

    @Column(name = "totalExpenses")
    private Double totalExpenses;

    @OneToMany(mappedBy= "user",cascade= CascadeType.ALL)
    private List<Trip> posts=new ArrayList<>();

}
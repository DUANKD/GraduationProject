package com.haibusiness.szweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Basic(optional = false)
	private String name;
 	private String icon;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "parent")
	@JsonIgnore
	private Menu parent;
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY,mappedBy="parent")
	private List<Menu> items;
	@ManyToMany
	@JoinTable(name = "menu_Authority", joinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "authority_id",referencedColumnName = "id")})
	private Set<Authority> roles;
	@Override
	public String toString() {
		return name;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Menu menu = (Menu) o;
		return Objects.equals(name, menu.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}

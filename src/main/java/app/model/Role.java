package app.model;

import app.dto.RoleDto;
import com.fasterxml.jackson.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles4")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role implements GrantedAuthority {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 20, nullable = false, unique = true)
	private String name;

	@JsonBackReference("users4")
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinTable(name = "users_roles4",
			joinColumns = {@JoinColumn(name = "role_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")})
	private List<User> users = new ArrayList<User>();

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public Role(long id) {
		this.id = id;
	}


	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*@Override
	public String toString() {
		if (name.contains("ADMIN") && name.contains("USER")) {
			return "admin, user";
		} else if (name.contains("ADMIN")) {
			return "admin";
		}
		return "user";
	}*/

	@JsonIgnore
	public String getAuthority() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Role role = (Role) o;
		return name.equals(role.name);
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + name.hashCode();
		return result;
	}

}

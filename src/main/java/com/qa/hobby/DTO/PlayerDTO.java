package com.qa.hobby.DTO;

import java.util.List;

public class PlayerDTO {
	
	private Long id;
	
	private String name;
	
	private List<CharacterDTO> character;

	public PlayerDTO() {
		super();
	}
	
	

	public PlayerDTO(Long id, String name, List<CharacterDTO> character) {
		super();
		this.id = id;
		this.name = name;
		this.character = character;
	}

	public PlayerDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public PlayerDTO(String name) {
		super();
		this.name = name;
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

	public List<CharacterDTO> getCharacter() {
		return character;
	}

	public void setCharacter(List<CharacterDTO> character) {
		this.character = character;
	}

	@Override
	public String toString() {
		return "PlayerDTO [id=" + id + ", name=" + name + ", character=" + character + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((character == null) ? 0 : character.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerDTO other = (PlayerDTO) obj;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

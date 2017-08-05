package guru.springframework.domain;

import javax.persistence.*;

@Entity
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String recipeNotes;

	@OneToOne
	private Recipe recipe;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getRecipeNotes() {
		return recipeNotes;
	}

	public void setRecipeNotes(final String recipeNotes) {
		this.recipeNotes = recipeNotes;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(final Recipe recipe) {
		this.recipe = recipe;
	}
}

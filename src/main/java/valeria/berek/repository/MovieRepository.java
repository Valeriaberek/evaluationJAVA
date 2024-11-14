package valeria.berek.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import valeria.berek.model.MovieModel;

public interface MovieRepository extends CrudRepository<MovieModel,Long> {
	
	public List<MovieModel> findByTitle(String title);

}

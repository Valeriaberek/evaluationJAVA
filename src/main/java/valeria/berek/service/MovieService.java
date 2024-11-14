package valeria.berek.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import valeria.berek.dto.MovieDto;
import valeria.berek.exception.CreateMovieIdException;
import valeria.berek.model.MovieModel;
import valeria.berek.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public Collection<MovieDto> getAllMovies() {
		return modelsToDtos(movieRepository.findAll());
	}

	public MovieDto createMovie(MovieDto dto) throws CreateMovieIdException {
		if(dto.getId()!=null) {
			throw new CreateMovieIdException("Il est interdit de mettre un Id afin de créer un utilisateur");
		}
		return modelToDto(movieRepository.save(dtoToModel(dto)));
	}

	public MovieDto updateMovie(MovieDto dto) {
		return modelToDto(movieRepository.save(dtoToModel(dto)));
	}

	private Collection<MovieDto> modelsToDtos(Iterable<MovieModel> movieModels) {
		Collection<MovieDto> movieDtos = new ArrayList<>();
		movieModels.forEach(movieModel -> {
			movieDtos.add(modelToDto(movieModel));
		});
		return movieDtos;
	}

	private MovieDto modelToDto(MovieModel movieModel) {
		MovieDto movieDto = new MovieDto();
		movieDto.setDirector(movieModel.getDirector());
		movieDto.setId(movieModel.getId());
		movieDto.setTitle(movieModel.getTitle());
		return movieDto;
	}

	private MovieModel dtoToModel(MovieDto movieDto) {
	MovieModel movieModel = new MovieModel();
		movieModel.setDirector(movieDto.getDirector());
		movieModel.setId(movieDto.getId());
		movieModel.setTitle(movieDto.getTitle());
		return movieModel;
	}

	public MovieDto getMovie(long id) {
		return modelToDto(movieRepository.findById(id).get());
	}

	public Collection<MovieDto> getMovieByTitle(String title) {
		return modelsToDtos(movieRepository.findByTitle(title));
	}

	public boolean deleteMovie(long id) {
		movieRepository.deleteById(id);
		return true;
	}

}
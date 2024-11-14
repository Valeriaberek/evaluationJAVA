package valeria.berek.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import valeria.berek.dto.MovieDto;
import valeria.berek.exception.CreateMovieIdException;
import valeria.berek.service.MovieService;

@Controller  
@RequestMapping("movie")
public class MovieController {
    
    @Autowired
    private MovieService movieService;
    
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    @ResponseBody
    public Collection<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }
    

    @GetMapping("/{id}")
    @ResponseBody
    public MovieDto getMovie(@PathVariable("id") long id) {
        return movieService.getMovie(id);
    }
   
    @GetMapping("/title/{title}")
    @ResponseBody
    public Collection<MovieDto> getMovieByName(@PathVariable("title") String title) {
        return movieService.getMovieByTitle(title);
    }
    
 
    @DeleteMapping("/{id}")
    @ResponseBody
    public boolean deleteMovie(@PathVariable("id") long id) {
        return movieService.deleteMovie(id);
    }
    
  
    @PostMapping()
    @ResponseBody
    public MovieDto createMovie(@RequestBody MovieDto movieDto) throws CreateMovieIdException {
        return movieService.createMovie(movieDto);
    }
    
  
    @PutMapping()
    @ResponseBody
    public MovieDto updateMovie(@RequestBody MovieDto movieDto) {
        return movieService.updateMovie(movieDto);
    }
    
   
    
    @GetMapping("/")
    public String getMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "home";  
    }

}
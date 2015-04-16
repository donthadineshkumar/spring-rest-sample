package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	/*
	 * RequestMapping - ensures that the requests to /greeting are mapped 
	 * to greeting() method
	 * 
	 * This method gets all the request - GET n POST
	 * to narrow down use @RequestMapping(value="/greeting", method=GET)
	 * 
	 * A key difference between a traditional MVC controller and the 
	 * RESTful web service controller above is the way that
	 * the HTTP response bofy is created.
	 * 
	 * Rather than relying on a view technology to perform server-side 
	 * rendering of the greeting data to HTML, this RESTful web service 
	 * controller simply populates and returns a Greeting object.
	 * 
	 * The object data will be written directly to the HTTP response 
	 * as JSON.
	 * 
	 * @RestController is spring 4's new annotation - Its a shorthand for
	 * @Controller and @ResponseBody rolled together.
	 * 
	 * @RestController = @Controller + @ResponseBody
	 * 
	 * This @RestController returns a Greeting object that must be converted to 
	 * JSON. You don;t need to do this conversion manually - so Spring HTTP message
	 * converter support does this.
	 * 
	 * Bacause Jackson is on classpath , spirng's MappingJackson2HttpMessageConverter
	 * is automatically choosen to convert the Greeting instance to JSON.
	 * 
	 */
	
	
	@RequestMapping(value="/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name){
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}

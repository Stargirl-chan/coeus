package com.star.ttc.coeus.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.star.ttc.coeus.interfaces.IBadWordService;

@Controller
public class BadWordController {

	private static final Logger logger = LoggerFactory.getLogger(BadWordController.class);
	
	@Autowired
	private IBadWordService badWordService;
	
	@RequestMapping("/bad-words-old")
	public ModelAndView index() {
		// logger.info(badWordService.findAll().toString());
		
		ModelAndView mav = new ModelAndView("bad-word-old");
		
		mav.addObject("words", badWordService.findAll());
		return mav;
	}
	
	@RequestMapping(value = "/bad-words", method = RequestMethod.GET)
    public String indexPaginated(
      Model model,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        
        // TODO: write header on class fields
        List<String> tableHeaders = Arrays.asList("ID", "Word");
        
        model.addAttribute("tableHeaders", tableHeaders);
        
        String requestMappingUrl = "/bad-words";
        
        model.addAttribute("requestMappingUrl", requestMappingUrl);
        
        Page<Map<String, Object>> badWordPage = badWordService.findPaginated(PageRequest.of(currentPage - 1, pageSize));  

        model.addAttribute("tablePage", badWordPage);

        int totalPages = badWordPage.getTotalPages();
        
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "bad-word";
    }
}
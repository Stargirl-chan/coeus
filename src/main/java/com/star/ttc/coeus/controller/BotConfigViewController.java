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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.star.ttc.coeus.interfaces.IBotConfigViewService;

@Controller
public class BotConfigViewController {

	private static final Logger logger = LoggerFactory.getLogger(BotConfigViewController.class);

	@Autowired
	private IBotConfigViewService botConfigViewService;

	@RequestMapping(value = "/view-config", method = RequestMethod.GET)
    public String indexPaginated(
      Model model,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        // TODO: write header on class fields
        List<String> tableHeaders = Arrays.asList("Config ID", "Config Properties ID", "Support Channel", "Welcome Channel", "Verified Role", "Moderator Role", "Conveyance Blacklist Channel", "Conveyance Channel", "Harold Emoji", "Welcome Message");

        model.addAttribute("tableHeaders", tableHeaders);

        // TODO: get request mapping directly from annotation
        String requestMappingUrl = "/view-config";

        model.addAttribute("requestMappingUrl", requestMappingUrl);


        Page<Map<String, Object>> botConfigViewPage = botConfigViewService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("tablePage", botConfigViewPage);

        int totalPages = botConfigViewPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "info-page";
    }

	@GetMapping("/view-config/edit/{id}")
	public ModelAndView editPage(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("edit-page");

		List<String> labels = Arrays.asList("Config ID", "Config Properties ID", "Support Channel", "Welcome Channel", "Verified Role", "Moderator Role", "Conveyance Blacklist Channel", "Conveyance Channel", "Harold Emoji", "Welcome Message");

		mav.addObject("rowEntry", botConfigViewService.findById(id));
		mav.addObject("labels", labels);
		return mav;
	}

	@PutMapping("/view-config/edit/{id}")
	public ModelAndView updateEntry(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("edit-page");

		mav.addObject("viewConfigEntry", botConfigViewService.findById(id));
		return mav;
	}
}
package com.star.ttc.coeus.controller;

import java.util.Arrays;
import java.util.List;
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

import com.star.ttc.coeus.interfaces.IConveyanceChannelService;
import com.star.ttc.coeus.models.ConveyanceChannel;

@Controller
public class ConveyanceChannelController {

	private static final Logger logger = LoggerFactory.getLogger(ConveyanceChannelController.class);
	
	@Autowired
	private IConveyanceChannelService conveyanceChannelService;
	
	@RequestMapping("/conveyance-channel-old")
	public ModelAndView index() {
		logger.info(conveyanceChannelService.findAll().toString());
		
		ModelAndView mav = new ModelAndView("conveyance-channel-old");
		
		mav.addObject("channels", conveyanceChannelService.findAll());
		return mav;
	}
	
	@RequestMapping(value = "/conveyance-channel", method = RequestMethod.GET)
    public String indexPaginated(
      Model model, 
      @RequestParam("page") Optional<Integer> page, 
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        
        // TODO: write header on class fields
        List<String> tableHeaders = Arrays.asList("ID", "Channel ID");
        
        model.addAttribute("tableHeaders", tableHeaders);
        
        // TODO: get request mapping directly from annotation
        String requestMappingUrl = "/conveyance-channel";
        
        model.addAttribute("requestMappingUrl", requestMappingUrl);
        
        
        Page<ConveyanceChannel> conveyanceChannelPage = conveyanceChannelService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("tablePage", conveyanceChannelPage);

        int totalPages = conveyanceChannelPage.getTotalPages();
        
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "conveyance-channel";
    }
}
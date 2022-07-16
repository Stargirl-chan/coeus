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

import com.star.ttc.coeus.interfaces.IEmojiCacheChannelsService;
import com.star.ttc.coeus.models.EmojiCacheChannels;

@Controller
public class EmojiCacheChannelsController {

	private static final Logger logger = LoggerFactory.getLogger(EmojiCacheChannelsController.class);
	
	@Autowired
	private IEmojiCacheChannelsService emojiCacheChannelsService;
	
	@RequestMapping("/emoji-cache-channels-old")
	public ModelAndView index() {
		logger.info(emojiCacheChannelsService.findAll().toString());
		
		ModelAndView mav = new ModelAndView("emoji-cache-channels-old");
		
		mav.addObject("items", emojiCacheChannelsService.findAll());
		return mav;
	}
	
	@RequestMapping(value = "/emoji-cache-channels", method = RequestMethod.GET)
    public String indexPaginated(
      Model model, 
      @RequestParam("page") Optional<Integer> page, 
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        
        // TODO: write header on class fields
        List<String> tableHeaders = Arrays.asList("Channel ID", "Message ID", "Unix Timestamp");
        
        model.addAttribute("tableHeaders", tableHeaders);
        
        // TODO: get request mapping directly from annotation
        String requestMappingUrl = "/emoji-cache-channels";
        
        model.addAttribute("requestMappingUrl", requestMappingUrl);
        
        
        Page<EmojiCacheChannels> emojiCacheChannelsPage = emojiCacheChannelsService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("tablePage", emojiCacheChannelsPage);

        int totalPages = emojiCacheChannelsPage.getTotalPages();
        
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "emoji-cache-channels";
    }
}
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

import com.star.ttc.coeus.interfaces.IEmojiCacheMessagesService;

@Controller
public class EmojiCacheMessagesController {

	private static final Logger logger = LoggerFactory.getLogger(EmojiCacheMessagesController.class);

	@Autowired
	private IEmojiCacheMessagesService emojiCacheMessagesService;

	@RequestMapping(value = "/emoji-cache-messages", method = RequestMethod.GET)
    public String indexPaginated(
      Model model,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        // TODO: write header on class fields
        List<String> tableHeaders = Arrays.asList("User ID", "Number of Messages");

        model.addAttribute("tableHeaders", tableHeaders);

        // TODO: get request mapping directly from annotation
        String requestMappingUrl = "/emoji-cache-messages";

        model.addAttribute("requestMappingUrl", requestMappingUrl);


        Page<Map<String, Object>> emojiCacheMessagesPage = emojiCacheMessagesService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("tablePage", emojiCacheMessagesPage);

        int totalPages = emojiCacheMessagesPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "info-page";
    }
}
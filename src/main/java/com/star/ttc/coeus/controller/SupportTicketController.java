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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.star.ttc.coeus.interfaces.ISupportTicketService;
import com.star.ttc.coeus.models.SelfRoles;
import com.star.ttc.coeus.models.SupportTicket;

@Controller
public class SupportTicketController {

	private static final Logger logger = LoggerFactory.getLogger(SupportTicketController.class);
	
	@Autowired
	private ISupportTicketService supportTicketService;
	
	@GetMapping("/tickets")
	public List<SupportTicket> findTickets() {
		logger.info(supportTicketService.findAll().toString());
		
		return (List<SupportTicket>) supportTicketService.findAll();
		/*List<SupportTicket> tickets = supportTicketService.findAll();
		
		List<String> strTickets = new ArrayList<>();
		for (SupportTicket ticket : tickets) {
			strTickets.add(ticket.toString());
		}
		return strTickets;*/
	}
	
	@RequestMapping("/support-tickets-old")
	public ModelAndView index() {
		logger.info(supportTicketService.findAll().toString());
		
		ModelAndView mav = new ModelAndView("support-ticket-old");
		
		mav.addObject("tickets", supportTicketService.findAll());
		return mav;
		/*List<SupportTicket> tickets = supportTicketService.findAll();
		
		List<String> strTickets = new ArrayList<>();
		for (SupportTicket ticket : tickets) {
			strTickets.add(ticket.toString());
		}
		return strTickets;*/
	}
	
	@RequestMapping(value = "/support-tickets", method = RequestMethod.GET)
    public String indexPaginated(
      Model model, 
      @RequestParam("page") Optional<Integer> page, 
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        
        // TODO: write header on class fields
        List<String> tableHeaders = Arrays.asList("Incident ID", "Thread ID", "User ID", "Incident Time", "Incident Title", "Incident Solved", "Unarchivals");
        
        model.addAttribute("tableHeaders", tableHeaders);
        
        // TODO: get request mapping directly from annotation
        String requestMappingUrl = "/support-tickets";
        
        model.addAttribute("requestMappingUrl", requestMappingUrl);
        
        
        Page<SupportTicket> supportTicketPage = supportTicketService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("tablePage", supportTicketPage);

        int totalPages = supportTicketPage.getTotalPages();
        
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "support-ticket";
    }
}
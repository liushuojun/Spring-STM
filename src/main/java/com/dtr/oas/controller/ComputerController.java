package com.dtr.oas.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dtr.oas.exception.ComputerPartsNotFoundException;
import com.dtr.oas.exception.DuplicateComputerException;
import com.dtr.oas.exception.ComputerNotFoundException;
import com.dtr.oas.model.Computer;
import com.dtr.oas.model.Display;
import com.dtr.oas.model.Memory;
import com.dtr.oas.model.System;
import com.dtr.oas.model.Processor;
import com.dtr.oas.service.DisplayService;
import com.dtr.oas.service.MemoryService;
import com.dtr.oas.service.SystemService;
import com.dtr.oas.service.ProcessorService;
import com.dtr.oas.service.ComputerService;
import com.dtr.oas.dto.ComputerDTO;

@Controller
@RequestMapping(value = "/computer")
//@PreAuthorize("denyAll")
public class ComputerController {
    static Logger logger = LoggerFactory.getLogger(ComputerController.class);
    static String businessObject = "computer"; //used in RedirectAttributes messages 

    @Autowired
    private ProcessorService processorService;
    
    @Autowired
    private SystemService systemService;
    
    @Autowired
    private MemoryService memoryService;

    @Autowired
    private ComputerService computerService;
    
    @Autowired
    private DisplayService displayService;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("allProcessors")
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public List<Processor> getAllProcessors() {
    	logger.info("IN: Computer/list-GETprocessor");
        return processorService.getProcessors();
    }
  
    @ModelAttribute("allSystems")
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public List<System> getAllOSs() {
    	logger.info("IN: Computer/list-GETSystems");
        return systemService.getSystems();
    }
    
    @ModelAttribute("allMemories")
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public List<Memory> getAllMemories() {
    	logger.info("IN: computer/list-GETMemories");
    	return memoryService.getMemorys();
    }
    
    @ModelAttribute("allDisplays")
    //@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
    public List<Display> getAllDisplays() {
    	logger.info("IN:computer/list-GETDisplays");
    	return displayService.getDisplays();
    }
    
/*    @RequestMapping(value = { "/computerlist"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
    public List<Computer> NameListComputers() {
        logger.info("IN: Computer/listname-GET");

        List<Computer> computers = computerService.getComputers();
        
        return computers;
    }*/
    
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    //@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
    public String listComputers(Model model) {
        logger.info("IN: Computer/list-GET");

        List<Computer> computers = computerService.getComputers();
        model.addAttribute("computers", computers);

        // if there was an error in /add, we do not want to overwrite
        // the existing computer object containing the errors.
        if (!model.containsAttribute("computerDTO")) {
            logger.info("Adding computerDTO object to model");
            ComputerDTO computerDTO = new ComputerDTO();
            model.addAttribute("computerDTO", computerDTO);
        }
        return "computer-list";
    }
    
    
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('CTRL_USER_ADD_POST')")
    public String addComputer(@Valid @ModelAttribute ComputerDTO computerDTO,
            BindingResult result, RedirectAttributes redirectAttrs) {
        
        logger.info("IN: /add-POST");

        if (result.hasErrors()) {
            logger.info("ComputerDTO add error: " + result.toString());
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.computerDTO", result);
            redirectAttrs.addFlashAttribute("computerDTO", computerDTO);
            return "redirect:/computer/list";
        } else {
            Computer computer = new Computer();

            try {
            	computerDTO.setPrice(getDefaultPrice(computerDTO));
            	logger.info("The default price is "+ computerDTO.getPrice());
                computer = getComputer(computerDTO);
                computerService.addComputer(computer);
                String message = messageSource.getMessage("ctrl.message.success.add", new Object[] {businessObject, computer.getComputername()}, Locale.US);
                redirectAttrs.addFlashAttribute("message", message);
                return "redirect:/computer/list";
            } catch (DuplicateComputerException e) {
                String message = messageSource.getMessage("ctrl.message.error.duplicate", new Object[] {businessObject, computerDTO.getComputername()}, Locale.US);
                redirectAttrs.addFlashAttribute("error", message);
                return "redirect:/computer/list";
            } catch (ComputerPartsNotFoundException rnf) {
                String message = messageSource.getMessage("ctrl.message.error.notfound", new Object[] {"role", computerDTO.getProcessorId()}, Locale.US);
                result.rejectValue("roleId", "0", message);
                redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.computerDTO", result);
                redirectAttrs.addFlashAttribute("computerDTO", computerDTO);
                return "redirect:/computer/list";
            }
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('CTRL_USER_EDIT_GET')")
    public String editComputerPage(@RequestParam(value = "id", required = true)
            Integer id, Model model, RedirectAttributes redirectAttrs) {

        logger.info("IN: Computer/edit-GET:  ID to query = " + id);

        try {
            if (!model.containsAttribute("computerDTO")) {
                logger.info("Adding computerDTO object to model");
                Computer computer = computerService.getComputer(id);
                ComputerDTO computerDTO = getComputerDTO(computer);
                logger.info("Computer/edit-GET:  " + computerDTO.toString());
                model.addAttribute("computerDTO", computerDTO);
            }
            return "computer-edit";
        } catch (ComputerNotFoundException e) {
            String message = messageSource.getMessage("ctrl.message.error.notfound", new Object[] {"computer id", id}, Locale.US);
            model.addAttribute("error", message);
            return "redirect:/computer/list";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @PreAuthorize("hasRole('CTRL_USER_EDIT_POST')")
    public String editComputer(@Valid @ModelAttribute ComputerDTO computerDTO,
            BindingResult result, RedirectAttributes redirectAttrs,
            @RequestParam(value = "action", required = true) String action) {

        logger.info("IN: Computer/edit-POST: " + action);

        if (action.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
            String message = messageSource.getMessage("ctrl.message.success.cancel", new Object[] {"Edit", businessObject, computerDTO.getComputername()}, Locale.US);
            redirectAttrs.addFlashAttribute("message", message);
        } else if (result.hasErrors()) {
            logger.info("Computer-edit error: " + result.toString());
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.computerDTO", result);
            redirectAttrs.addFlashAttribute("computerDTO", computerDTO);
            return "redirect:/computer/edit?id=" + computerDTO.getId();
        } else if (action.equals(messageSource.getMessage("button.action.save",  null, Locale.US))) {
            logger.info("Computer/edit-POST:  " + computerDTO.toString());
            try {
                Computer computer = getComputer(computerDTO);
                computerService.updateComputer(computer);
                String message = messageSource.getMessage("ctrl.message.success.update", new Object[] {businessObject, computerDTO.getComputername()}, Locale.US);
                redirectAttrs.addFlashAttribute("message", message);
            } catch (DuplicateComputerException unf) {
                String message = messageSource.getMessage("ctrl.message.error.duplicate", new Object[] {businessObject, computerDTO.getComputername()}, Locale.US);
                redirectAttrs.addFlashAttribute("error", message);
                return "redirect:/computer/list";
            } catch (ComputerNotFoundException unf) {
                String message = messageSource.getMessage("ctrl.message.error.notfound", new Object[] {businessObject, computerDTO.getComputername()}, Locale.US);
                redirectAttrs.addFlashAttribute("error", message);
                return "redirect:/computer/list";
            } catch (ComputerPartsNotFoundException rnf) {
                String message = messageSource.getMessage("ctrl.message.error.notfound", new Object[] {"role", computerDTO.getComputername()}, Locale.US);
                redirectAttrs.addFlashAttribute("error", message);
                return "redirect:/computer/list";
            }
        }
        return "redirect:/computer/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('CTRL_USER_DELETE_GET')")
    public String deleteComputer(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "phase", required = true) String phase,
            Model model, RedirectAttributes redirectAttrs) {

        Computer computer;
        try {
            computer = computerService.getComputer(id);
        } catch (ComputerNotFoundException e) {
            String message = "Computer number [" + id + "] does not exist in the system";
            redirectAttrs.addFlashAttribute("error", message);
            return "redirect:/computer/list";
        }

        logger.info("IN: Computer/delete-GET | id = " + id + " | phase = " + phase + " | " + computer.toString());

        if (phase.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
            String message = messageSource.getMessage("ctrl.message.success.cancel", new Object[] {"Delete", businessObject, computer.getComputername()}, Locale.US);
            redirectAttrs.addFlashAttribute("message", message);
            return "redirect:/computer/list";
        } else if (phase.equals(messageSource.getMessage("button.action.stage", null, Locale.US))) {
            logger.info("     adding computer : " + computer.toString());
            model.addAttribute("computer", computer);
            return "computer-delete";
        } else if (phase.equals(messageSource.getMessage("button.action.delete", null, Locale.US))) {
            try {
                computerService.deleteComputer(computer.getId());
                String message = messageSource.getMessage("ctrl.message.success.delete", new Object[] {businessObject, computer.getComputername()}, Locale.US);
                redirectAttrs.addFlashAttribute("message", message);
                return "redirect:/computer/list";
            } catch (ComputerNotFoundException e) {
                String message = messageSource.getMessage("ctrl.message.error.notfound", new Object[] {"computer id", id}, Locale.US);
               redirectAttrs.addFlashAttribute("error", message);
                return "redirect:/computer/list";
           }
        }

        return "redirect:/computer/list";
    }
    
    //@PreAuthorize("hasAnyRole('CTRL_USER_EDIT_GET','CTRL_USER_DELETE_GET')")
    public ComputerDTO getComputerDTO(Computer computer) {
    	ComputerDTO computerDTO = new ComputerDTO();
    	computerDTO.setId(computer.getId());
    	computerDTO.setComputername(computer.getComputername());
    	computerDTO.setPrice(computer.getPrice());
    	computerDTO.setSeries(computer.getSeries());
    	computerDTO.setProcessorId(computer.getProcessor().getId());
    	computerDTO.setSystemId(computer.getSystem().getId());
    	computerDTO.setMemoryId(computer.getMemory().getId());
    	computerDTO.setDisplayId(computer.getDisplay().getId());
        return computerDTO;
    }

    //@PreAuthorize("hasAnyRole('CTRL_USER_ADD_POST','CTRL_USER_EDIT_POST')")
    public Computer getComputer(ComputerDTO computerDTO) throws ComputerPartsNotFoundException{
    	Computer computer = new Computer();
    	computer.setId(computerDTO.getId());

        Processor processor = processorService.getProcessor(computerDTO.getProcessorId());
        computer.setProcessor(processor);
        
        System system= systemService.getSystem(computerDTO.getSystemId());
        computer.setSystem(system);
        
        Memory memory = memoryService.getMemory(computerDTO.getMemoryId());
        computer.setMemory(memory);
        
        Display display = displayService.getDisplay(computerDTO.getDisplayId());
        computer.setDisplay(display);

        computer.setComputername(computerDTO.getComputername());
        computer.setSeries(computerDTO.getSeries());
        computer.setPrice(computerDTO.getPrice());
        return computer;
    }
    
	public int getDefaultPrice(ComputerDTO computerDTO)
			throws ComputerPartsNotFoundException {
		int computerPrice = 0;

		int processorPrice = processorService.getProcessor(
				computerDTO.getProcessorId()).getPrice();
		computerPrice += processorPrice;

		int systemPrice = systemService.getSystem(computerDTO.getSystemId())
				.getPrice();
		computerPrice += systemPrice;
		
		int displayPrice = displayService
				.getDisplay(computerDTO.getDisplayId()).getPrice();
		computerPrice += displayPrice;
		
		int memoryPrice = memoryService.getMemory(computerDTO.getMemoryId())
				.getPrice();
		computerPrice += memoryPrice;

		return computerPrice;
	}
}

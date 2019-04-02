package com.koishman.stocks.web.controller;

import com.google.common.collect.Lists;
import com.koishman.stocks.auth.service.UserService;
import com.koishman.stocks.model.sotck.Stock;
import com.koishman.stocks.service.StockService;
import com.koishman.stocks.web.model.StockDTO;
import com.koishman.stocks.web.model.TimeLine;
import com.koishman.stocks.web.model.TimeLineEditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/addNew")
@SessionAttributes("stock")
public class StockController {

	private Validator validator;

	@Autowired
	StockService stockService;
	@Autowired
	UserService userService;

	public StockController() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		StockDTO stockDTO = new StockDTO();
		model.addAttribute("stock", stockDTO);
		return "stocks2";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("stock") StockDTO stockDTO, BindingResult result, SessionStatus status, Model model) {
		Set<ConstraintViolation<StockDTO>> violations = validator.validate(stockDTO);

		for (ConstraintViolation<StockDTO> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			result.addError(new FieldError("employee", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}

		if (result.hasErrors()) {
			return "stocks2";
		}
		Map<String, List<Stock>> dayHistory = new HashMap<>();
		if (stockDTO.getTimeLine().getName().equals("today")) {
			for (int i = 0; i < 5; i++) {
				dayHistory = stockService.getDayHistory(Lists.newArrayList(StringUtils.split(stockDTO.getSymbols(), ",")));
				System.out.println(dayHistory);
			}
		}
//		if (stockDTO.getTimeLine().getName().equals("schedualer started")) {
//				userService.addSymbolsToUser();
//				System.out.println(dayHistory);
//			}
//		}

		System.out.println(stockDTO);

		// Mark Session Complete
		List<Stock> collect = dayHistory.values().stream().flatMap(stocks -> stocks.stream()).collect(Collectors.toList());
		model.addAttribute("msg", "schedualer started");
		model.addAttribute("list", collect);
		return "stocks2";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(TimeLine.class, new TimeLineEditor());
	}

	@ModelAttribute("allTimelines")
	public List<TimeLine> populateDepartments() {
		ArrayList<TimeLine> departments = new ArrayList<TimeLine>();
		departments.add(new TimeLine(-1, "Select period"));
		departments.add(new TimeLine(1, "current"));
		departments.add(new TimeLine(2, "Add to schedual"));
		return departments;
	}

}
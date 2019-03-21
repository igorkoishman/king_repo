package com.koishman.stocks.web.controller;

import com.google.common.collect.Lists;
import com.koishman.stocks.model.sotck.Stock;
import com.koishman.stocks.service.StockService;
import com.koishman.stocks.web.model.StockResponse;
import com.koishman.stocks.web.model.TimeLineEditor;
import com.koishman.stocks.web.model.TimeLine;
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
public class EmployeeController {

	private Validator validator;

	@Autowired
	StockService stockService;

	public EmployeeController() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		StockResponse employeeVO = new StockResponse();
		model.addAttribute("employee", employeeVO);
		return "stocks2";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("stock") StockResponse stockResponse, BindingResult result, SessionStatus status, Model model) {
		Set<ConstraintViolation<StockResponse>> violations = validator.validate(stockResponse);

		for (ConstraintViolation<StockResponse> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			result.addError(new FieldError("employee", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}

		if (result.hasErrors()) {
			return "stocks2";
		}
		Map<String, List<Stock>> dayHistory = new HashMap<>();
		if (stockResponse.getTimeLine().getName().equals("today")) {
			for (int i = 0; i < 5; i++) {
				dayHistory = stockService.getDayHistory(Lists.newArrayList(StringUtils.split(stockResponse.getSymbols(), ",")));
				System.out.println(dayHistory);
			}
		}

		System.out.println(stockResponse);

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
		departments.add(new TimeLine(1, "today"));
		departments.add(new TimeLine(2, "schedualed"));
		return departments;
	}

}
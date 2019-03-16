package com.hellokoding.auth.web;

import com.hellokoding.auth.EmployeeVO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/addNew")
@SessionAttributes("employee")
public class EmployeeController {

	private Validator validator;

	public EmployeeController() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		EmployeeVO employeeVO = new EmployeeVO();
		model.addAttribute("employee", employeeVO);
		return "stocks2";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("employee") EmployeeVO employeeVO, BindingResult result, SessionStatus status, Model model) {
		Set<ConstraintViolation<EmployeeVO>> violations = validator.validate(employeeVO);

		for (ConstraintViolation<EmployeeVO> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			result.addError(new FieldError("employee", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}

		if (result.hasErrors()) {
			return "stocks2";
		}
		// Store the employee information in database
		// manager.createNewRecord(employeeVO);

		System.out.println(employeeVO);

		// Mark Session Complete
		model.addAttribute("msg", "schedualer started");
		status.setComplete();
		return "stocks2";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(DepartmentVO.class, new DepartmentEditor());
	}

	@ModelAttribute("allDepartments")
	public List<DepartmentVO> populateDepartments() {
		ArrayList<DepartmentVO> departments = new ArrayList<DepartmentVO>();
		departments.add(new DepartmentVO(-1, "Select period"));
		departments.add(new DepartmentVO(1, "today"));
		departments.add(new DepartmentVO(2, "schedualed"));
		return departments;
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(Model model) {
		return "addSuccess";
	}
}
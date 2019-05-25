package com.projecty.projectyweb.validator;

import com.projecty.projectyweb.model.Task;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TaskValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target;
        ValidationUtils.rejectIfEmpty(errors, "startDate", "startDate.empty");
        ValidationUtils.rejectIfEmpty(errors, "endDate", "endDate.empty");

        if (task.getStartDate() != null && task.getEndDate() != null && task.getStartDate().after(task.getEndDate())) {
            errors.rejectValue("startDate", "startDate.greaterThanEndDate");
        }
    }
}
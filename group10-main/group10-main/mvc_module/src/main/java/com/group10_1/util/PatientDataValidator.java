package com.group10_1.util;


import CT_CONVERTER.Simple_CT_Converter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for {@link Simple_CT_Converter.Patient} objects.
 * Validates name, birth, weight, height properties of the patient data.
 */
@Component
public class PatientDataValidator implements Validator {

    /**
     * Regular expression for the date pattern in the format dd.mm.yyyy
     */
    private final String datePattern = "\\d{2}\\.\\d{2}\\.\\d{4}";

    /**
     * Returns whether the validator can validate instances of the specified class.
     *
     * @param clazz the class to check for support
     * @return true if this validator can validate instances of the specified class; false otherwise
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Simple_CT_Converter.Patient.class.equals(clazz);
    }

    /**
     * Validates the given object, casting it to a {@link Simple_CT_Converter.Patient}.
     *
     * @param target the object that should be validated
     * @param errors contextual state about the validation process
     */
    @Override
    public void validate(Object target, Errors errors) {
        Simple_CT_Converter.Patient patient = (Simple_CT_Converter.Patient) target;

        // Validate name length
        if (patient.getName().length() > 35) {
            errors.rejectValue("name", "", "The name is suspiciously long. " + patient.getName());
        }

        // Validate height range
        if (patient.getHeight() > 300 || patient.getHeight() < 0) {
            errors.rejectValue("height", "", "Please give the height in cm. " + patient.getHeight());
        }

        // Validate birth date format
        if (!patient.getBirth().matches(datePattern)) {
            errors.rejectValue("birth", "", "The birth date must be of type dd.mm.yyyy  " + patient.getBirth());
        }

        // Validate weight positivity
        if (patient.getWeight() < 0) {
            errors.rejectValue("weight", "", "The weight should be positive. " + patient.getWeight());
        }
    }
}
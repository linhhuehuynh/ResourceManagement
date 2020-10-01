import { ValidationErrors, FormGroup, ValidatorFn } from "@angular/forms";

export const passwordValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
    const password = control.value['password'];
    const confirmPassword = control.value['confirmPassword'];
    return password && confirmPassword && password === confirmPassword
        ? null
        : { passwordsNotEqual: true };
};